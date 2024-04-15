
import uuid
from pydantic import BaseModel, Field
from bson import ObjectId
from uuid import UUID

class ModelResponse (BaseModel) : 
    conversation_id : uuid.UUID
    content : str 
    response : str

    class Config:
        arbitrary_types_allowed = True
        json_encoders = {ObjectId: str}
        schema_extra = {
            "example" : {
                "messages" : []
        }
    }

