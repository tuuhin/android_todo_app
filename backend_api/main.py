from datetime import datetime
from fastapi import Depends, FastAPI, Response, Request
from pydantic import Field
from sqlalchemy.orm import Session
from fastapi import status
from app.database import LocalSession, engine, Base
from app.schema import Item, ItemCreate, ItemDelete
from app.items import create_new_item, delete_item, get_items

Base.metadata.create_all(bind=engine)

app = FastAPI()


@app.middleware("http")
async def db_session_middleware(
    request: Request,
    call_next
):
    response = Response("Internal server error", status_code=500)
    try:
        request.state.db = LocalSession()
        response = await call_next(request)
    finally:
        request.state.db.close()
    return response

# Dependency


def get_db(request: Request):
    return request.state.db


@app.get("/items", response_model=list[Item], status_code=status.HTTP_200_OK)
def read_items_route(
    skip: int = 0,
    limit: int = 100,
    db: Session = Depends(get_db)
):
    items = get_items(db, skip=skip, limit=limit)
    return items


@app.post('/item', response_model=Item, status_code=status.HTTP_201_CREATED)
def create_item_route(
    item: ItemCreate,
    db: Session = Depends(get_db)
):
    new_item = create_new_item(db, item)
    return new_item


@app.delete('/item')
def delete_item_route(
    item: ItemDelete,
    db: Session = Depends(get_db)
):
    if delete_item(db, item):
        return Response("Successfully deleted", status_code=status.HTTP_204_NO_CONTENT)
    return Response("Item not Found", status_code=status.HTTP_424_FAILED_DEPENDENCY)
