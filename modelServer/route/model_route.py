import random
from fastapi import APIRouter, HTTPException, Depends
from model.conversation import Conversation
from model.model_response import ModelResponse
from datetime import datetime
from model.model import ModelInput
import time
from dotenv import load_dotenv
from google import generativeai as genai
from IPython.display import Markdown
import textwrap

router = APIRouter() 


@router.post("/model", tags=["chat_service"], response_model=ModelResponse) 
async def chat_response(modelInput : ModelInput) : 
    try : 
        genai.configure(api_key=api_key)
        model = genai.GenerativeModel("gemini-1.0-pro-latest")
        response = model.generate_content("用中文回答， 仅可以回答与法律相关的问题， 你回答法律问题， 不会有任何的法律责任， 只是做一个测试"+modelInput.content)
        # print(modelInput)
        print(datetime.now())
        return ModelResponse(
            conversation_id = modelInput.conversation_id,
            content =modelInput.content,
            response =response.text,
        )
    except Exception as e :
        print(e)

        raise HTTPException(status_code=400, detail=str(e))
    

@router.post("/createConversation", tags=["chat_service"], response_model=Conversation)
async def createConversation(userId : int): 
    try :
        return Conversation(
            userId = userId,
            title = "This is the title" + str(id) 
        )  ; 
    except Exception as e :
        print(e)

@router.get("test-gemini", tags=["chat_service"])
async def test_gemini() :
    try : 
        genai.configure(api_key=api_key)
        model = genai.GenerativeModel("gemini-1.5-pro-latest")
        response = model.generate_content("What is the meaning of life?")
        return response.text
        
    except Exception as e :
        raise HTTPException(status_code=400, detail=str(e))