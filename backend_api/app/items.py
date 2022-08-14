from sqlalchemy.orm import Session
from .schema import ItemCreate, ItemUpdate
from .entity import Item
from datetime import datetime
from typing import Union


def get_items(
    db: Session,
    skip: int = 0,
    limit: int = 100
) -> list[Item]:
    return db.query(Item).order_by(Item.id.desc()).offset(skip).limit(limit).all()


def get_completed_items(
    db: Session,
    skip: int = 0,
    limit: int = 0
) -> list[Item]:
    return db.query(Item).filter(
        Item.is_completed == True
    ).order_by(Item.id.desc()).offset(skip).limit(limit).all()


def get_incomplted_items(
    db: Session,
    skip: int = 0,
    limit: int = 0
) -> list[Item]:
    return db.query(Item).filter(
        Item.is_completed == False
    ).order_by(Item.id.desc()).offset(skip).limit(limit).all()


def create_new_item(
    db: Session,
    item: ItemCreate,
    datetime: datetime = datetime.now()
) -> Item:
    db_item: Item = Item(**item.dict(), created_at=datetime)
    db.add(db_item)
    db.commit()
    db.refresh(db_item)
    return db_item


def update_old_item(
    db: Session,
    item: ItemUpdate,
) -> Union[Item, None]:
    old_item: Item = db.query(Item).get(item.id)
    if old_item:
        old_item.is_completed = item.is_completed
        db.commit()
        db.refresh(old_item)
        return old_item


def delete_item(
    db: Session,
    item_id: int
) -> Union[bool, None]:
    item_exits = db.query(Item).get(item_id)
    if item_exits:
        db.delete(item_exits)
        db.commit()
        return True
