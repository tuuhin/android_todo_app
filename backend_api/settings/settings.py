from pydantic import BaseSettings


class Settings(BaseSettings):
    host: str
    database: str
    user: str
    port: int
    password: str

    class Config:
        env_file = '.env'
