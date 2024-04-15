
from pydantic import BaseModel, Field
from bson import ObjectId

class Conversation (BaseModel) : 
    userId : int
    title : str

    class Config:
        arbitrary_types_allowed = True
        json_encoders = {ObjectId: str}
        schema_extra = {
            "example" : {
                "messages" : []
        }
    }

