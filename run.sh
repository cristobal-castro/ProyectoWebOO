docker stop BACKEND-SPRING-GO;
docker rm BACKEND-SPRING-GO;
docker rmi backend_spring_go;
docker build -t backend_spring_go .;
docker run --name=BACKEND-SPRING-GO -d -p 8081:8080 backend_spring_go
