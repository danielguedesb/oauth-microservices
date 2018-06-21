
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

Or, if you're authenticating with an admin: 
`http://localhost:8765/resource-server-mvc-1/secret`

You'll be redirected to the Authorization Server to authenticate. 
Use the following credentials: `user`/`password` or `admin`/`admin`. 
And approve the authorization for the `fooScope` OAuth scope. 



## High-Level Flow

Here's what the typical flow looks like: 

```
Browser                             API Gateway (APIG)              Authorization Server (AS)
   │ APIG/resource-server-new                │                                  │
   ├────────────────────────────────────────>│                                  │
   │ Location:http://APIG/login              │                                  │
   │<────────────────────────────────────────│                                  │
   │ http://APIG/login                       │                                  │
   ├────────────────────────────────────────>│                                  │
   │ Location:http://APIG/AS/oauth/authorize │                                  │
   │<────────────────────────────────────────│                                  │
   │ http://APIG/AS/oauth/authorize          │                                  │
   ├────────────────────────────────────────>│                                  │
   │                                         │      /AS/oauth/authorize         │
   │                                         ├─────────────────────────────────>│
   │                                         │                                  ├──┐
   │                                         │                                  │  │ Not authorized
   │                                         │                                  │<─┘
   │                                         │  Location:http://APIG/AS/login   │
   │                                         │<─────────────────────────────────┤
   │ Location:http://APIG/AS/login           │                                  │
   │<────────────────────────────────────────│                                  │
   │ http://APIGAS/AS/login                  │                                  │
   ├────────────────────────────────────────>│                                  │
   │                                         │            /AS/login             │
   │                                         ├─────────────────────────────────>│
   │                                         │           LOGIN FORM             │
   │                                         │<─────────────────────────────────┤
   │           LOGIN FORM                    │                                  │
   │<────────────────────────────────────────┤                                  │
```

- note that the Authorization Server is internal and never communicates with the outside world



## Implementation Notes

### Oauth2ClientContextFilterWithPath
The custom OAuth2ClientContextFilter now supports URIs (paths), beyond just full URLs.
We need this path support so that we're able to use it via `security.oauth2.client.userAuthorizationUri` in Zuul, to redirect to Zuul itself.
Alternatively, we could hardcode this to: `http://localhost:${server.port}/authorization-server-1/oauth/authorize` (not ideal)


### `zuul.authorization-server-1.sensitiveHeaders`
We need the Cookie to be passed through from the Authorization Server