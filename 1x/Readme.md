
# POC for an OAuth2-based Architecture running behind an API Gateway

## Overview
Here are all the individual services: 
- `service-registry-1` - the Eureka-based service registry
- `api-gateway-zuul-1` - the Zuul-based API Gateway
- `authorization-server-1` - the Authoization Server
- `resource-server-mvc-1` - the Resource Server

Note that these are all implemented using Spring Boot 1.x


## Ops
When starting up the services, the sequence should be - `service-registry-1` first, everything else after. 


## Run
Once everything starts up, try to access the Resource Server, through the Gateway: 
`http://localhost:8765/resource-server-mvc-1`

You'll be redirected to the Authorization Server to authenticate. 
Use the following credentials: `user`/`password` or `admin`/`admin`. 
And approve the authorization for the `fooScope` OAuth scope. 
