# What it is

This is a service that accepts the necessary information and sends emails.

# Design

There are several modules:

- Message Receiver : receive user request, validation, then put request in queue
- Message Consumer : consumer message in queue, then send to provider adapter
- Provider Adapter : send message to third party email service like mailgun

# How it works

Please look at this component diagram I

![alt text](ComponentDiagram.png "component diagram")

# Todo

- Restful API should return different status code instead of 200 if parameter is wrong, or JMS is done.
- 100% coverage for unit test/integration test
- Application Level Load Balancing
- Security settings to reduce attack risk
- NoSQL support for saving email delivery history
- Advanced 3rd party service provider switch strategy based on service health check and load balancing
- JMX support for ProviderHelper, so that we can add more service provider in the runtime, without stop server or deploy new build.

# Restful API 

Please check out the restful_api.md. 

