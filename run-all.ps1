Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd .\discovery-service; mvn spring-boot:run"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd .\catalog-service; mvn spring-boot:run"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd .\order-service; mvn spring-boot:run"
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd .\api-gateway; mvn spring-boot:run"
