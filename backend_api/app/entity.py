from sqlalchemy import Column, Integer, String, Boolean, DateTime
from .database import Base


class Item(Base):

    __tablename__ = 'Item'

    id = Column(Integer, primary_key=True, index=True)
    title = Column(String, index=True)
    description = Column(String, index=True, nullable=True)
    created_at = Column(DateTime, index=True)
    is_completed = Column(Boolean, default=False)

    def __repr__(self) -> str:
        return f"{self.id}: {self.title} -> {self.is_completed}"
