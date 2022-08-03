from pydantic import BaseModel
from typing import Union
from datetime import datetime


class ItemBase(BaseModel):
    title: str
    description: Union[str, None] = None
    is_completed: bool

    class Config:
        orm_mode = True


class Item(ItemBase):
    id: int
    created_at: datetime


class ItemCreate(ItemBase):
    pass


class ItemDelete(BaseModel):
    id: int
