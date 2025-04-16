# Java homework task for Visma

Consumer application was extended with [ReactiveProcessingService](src/main/java/com/visma/task/consumer/service/ReactiveProcessingService.java)

This new service contains methods to wait for the legacy server (thirdpartyservice) to create items and return status OK 
asynchronously without blocking the service and with backpressure handling.

To expose Item creation to the client [ClientAPIController](src/main/java/com/visma/task/consumer/controller/ClientAPIController.java)
was created. The API should be reachable on http://127.0.0.1:8038/api/v1/items once the application is started.

To ensure hundreds of requests can be sent to this Consumer application, load tests were performed.  
Load tests were performed using JMeter test case with 250 users sending requests at the same time.  
You can find the test case and results [here](load_test)
