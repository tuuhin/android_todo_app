from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from settings import settings
from functools import lru_cache


@lru_cache
def get_env_vars():
    return settings.Settings()


def create_database_engine():
    settings = get_env_vars()

    # SQLALCHEMY_DATABASE_URL_SQLITE = "sqlite:///./sql_app.db"
    SQLALCHEMY_DATABASE_URL_POSTGRESS = f'postgresql://{settings.user}:{settings.password}@{settings.host}/{settings.database}'
    engine = create_engine(
        SQLALCHEMY_DATABASE_URL_POSTGRESS,
        connect_args={
            # if you plan to use sqlite here just uncomment the next line
            # "check_same_thread": False

        }
    )
    return engine


Base = declarative_base()

engine = create_database_engine()

LocalSession = sessionmaker(bind=engine, autocommit=False, autoflush=False,)
