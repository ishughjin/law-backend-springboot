from fastapi import APIRouter, HTTPException, Depends
from py_eureka_client import eureka_client

router = APIRouter() 


@router.get("/testEurekaService")
async def test_eureka_service() : 
    try : 
        res = eureka_client.get_service_url("model-service")
        return "FastAPI result: " + res 
    except Exception as e :
        print(e)