from typing import Union
from fastapi import (Depends, FastAPI, Response, Request,
                     Path, Query, Body, status, HTTPException)
from sqlalchemy.orm import Session
from fastapi.responses import ORJSONResponse
from app.database import LocalSession, engine, Base
from app.schema import Item, ItemCreate, ItemUpdate
from app.items import (create_new_item, delete_item, get_completed_items,
                       get_incomplted_items, get_items, update_old_item)

Base.metadata.create_all(bind=engine)

app = FastAPI()


@app.middleware("http")
async def db_session_middleware(
    request: Request,
    call_next
):
    response = Response("Internal server error",
                        status_code=status.HTTP_500_INTERNAL_SERVER_ERROR)
    try:
        request.state.db: Session = LocalSession()
        response = await call_next(request)
    finally:
        request.state.db.close()
    return response


# Dependency
def get_db(request: Request): return request.state.db


@app.get("/items", response_model=list[Item], status_code=status.HTTP_200_OK)
def read_items_route(
    completed: Union[bool, None] = Query(
        default=None,
        alias="checked",
        title="Completed",
        description="Get completed items"
    ),
    skip: Union[int, None] = Query(
        default=None,
        alias='items-offset',
        title="Skip",
        description="Change the offset of the data",
        ge=0),
    limit: Union[int, None] = Query(
        default=None,
        alias="items-limit",
        title="Limit",
        description="Limit the number of items",
        ge=0),
    db: Session = Depends(get_db)
):
    if completed is True:
        return get_completed_items(db, skip=skip, limit=limit)
    if completed is False:
        return get_incomplted_items(db, skip=skip, limit=limit)
    return get_items(db, skip=skip, limit=limit)


@app.post('/item', response_model=Item, status_code=status.HTTP_201_CREATED)
def create_item_route(
    item: ItemCreate = Body(
        alias="create-new-item",
        title="New Item",
        description="Create a new item"),
    db: Session = Depends(get_db)
):
    return create_new_item(db, item)


@app.put('/item', response_model=Union[Item, None], status_code=status.HTTP_205_RESET_CONTENT)
def update_item_route(
    item: ItemUpdate = Body(
        alias="update-item",
        title="Update an item",
        description="Update an item which is previously created "),
    db: Session = Depends(get_db)
):
    updated = update_old_item(db, item)
    if updated:
        return updated
    raise HTTPException(status_code=status.HTTP_404_NOT_FOUND,
                        detail="There is no item of this id")


@app.delete("/item/{id}", response_class=ORJSONResponse)
def delete_item_route(
    id: int = Path(
        default=...,
        title="Id of the item to be deleted ",
        description="Takes an id of an item and delete it from the database",
        ge=0),
    db: Session = Depends(get_db)
):
    if delete_item(db, id):
        return ORJSONResponse({"detail": "Successfully removed item"})
    raise HTTPException(detail="There is no item of this id",
                        status_code=status.HTTP_424_FAILED_DEPENDENCY)
