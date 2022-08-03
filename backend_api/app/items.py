from xmlrpc.client import Boolean
from sqlalchemy.orm import Session
from .schema import ItemCreate, ItemDelete
from .entity import Item
from datetime import datetime


def get_items(
    db: Session,
    skip: int = 0,
    limit: int = 100
):
    return db.query(Item).order_by(Item.id.desc()).offset(skip).limit(limit).all()


def create_new_item(
    db: Session, item: ItemCreate,
    datetime: datetime = datetime.now
):
    db_item = Item(**item.dict(), created_at=datetime)
    db.add(db_item)
    db.commit()
    db.refresh(db_item)
    return db_item


def delete_item(
    db: Session,
    item: ItemDelete
) -> Boolean:
    item_exits = db.query(Item).filter_by(**item.dict()).first()
    if item_exits:
        db.delete(item_exits)
        return True
    return False
