# sms-service
As a User I want a mechanism capable to examine whether data and depending on the temperature send an sms message to a specified number.

This project contains Three main services
1. Authentication service
2. WeatherService to get temperature
3. SMSService to send sms messages
4. Client class which responsible to invoke all services internally
5. Base64Encoder - to endode applicationID: secrete.
6. SMSScheduler - which runs 10 times per each run every 10 minutes.

Starting point or Client Code:
------------------------
SMSServiceClient
    - It is the starting of logic the below logic will be executed sequentially
    - 1. Call AuthenticationService to authentication the consumer.(https://auth.routee.net/oauth/token)
     -2. Call WeatherService (Weather API) to get temperature from api (https://openweathermap.org/api)
     -3. call SendSMSService (sendSMS) to send sms by calling the service (https://connect.routee.net/sms)

Scheduler
---------------------------------
SMSScheduler.java
   - It is scheduler for our implemented logic such that it will execute 10 times for each run 10 minutes.

TODOs:
1. Logger could have improved
2. There are few TODOs. which can be refactored if get sufficient time.

How to run
---------------
import into the project intelliJ idea
run this SMSServiceClient to verify end to end flow
run this SMSScheduler.java to verify scheduler.


