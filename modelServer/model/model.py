
from pydantic import BaseModel, Field
from bson import ObjectId
from uuid import UUID 

class ModelInput (BaseModel): 
    conversation_id : UUID
    content : str

    class Config:
        arbitrary_types_allowed = True
        json_encoders = {ObjectId: str}
        schema_extra = {
            "example" : {
                "messages" : []
        }
        }
