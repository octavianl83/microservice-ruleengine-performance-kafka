# microservice-ruleengine-performance-kafka

The application is intended to test the performance of microservice communication using kafka request response pattern

## Configuration

There is no special configuration to be done. Just download the repo and start the app.

The following spring boot services are present:
* monolit app that is sending 4 request to drool
* microservices app:
  - service processor 1
  - service processor 2
  - service processor 3
  - service processor 4
* eureka naming server
* drool microservice app

## Microservice flow
* each microservice is communication with each other using ReplyingKafkaTemplate
* after receiving the response from drool it sends the kafka request to the next microservice 
* the last one is sending the last response that will be forwarded to the first microservice

## Starting the app

* first start the eureka naming server
* then start all the other apps in random order

## Sending the first request
* monolit arhitecture: HTTP POST -> localhost:7000/service-process
* microservice arhitecture: HTTP POST -> localhost:7100/service-process1

## JSON body

{
   "tenant":{
      "tenantType":"tenantType1",
      "tenantId":"tenant1",
      "tenantFlow":"tenantFlow1",
      "tenantDB":"tenantDB1",
      "tenantDbSchema":"tenantDbSchema1"
   },
   "messageType":"CreditCard",
   "messageContent":{
      "cardNumber":"1234123412341234",
      "ownerName":"John Doe",
      "expireMonth":"07",
      "expireYear":"2025",
      "securityCode":"123",
      "currency":"EUR",
      "amount":"205.73"
   }
}

