from fastapi import FastAPI 
from fastapi.middleware.cors import CORSMiddleware
from route import model_route, test_eureka
import py_eureka_client.eureka_client as eureka_client
app = FastAPI()
origins = ["*"]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins, 
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.on_event("startup")
async def set_eureka():
    eureka_server_list = "http://localhost:8070/eureka"
    my_server_host  = "localhost"
    my_server_port = 3001
    await eureka_client.init_async(eureka_server = eureka_server_list, app_name = "modelservice",instance_host  = my_server_host,  instance_port = my_server_port)

app.include_router(model_route.router, prefix="/api/v1")
app.include_router(test_eureka.router, prefix="/api/v1")
