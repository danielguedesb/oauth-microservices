
## Ops
When starting up the services, the sequence should be - service-registry-1 first, everything else after. 

Once everything starts up, try to access the Resource Server, through the Gateway: 
`http://localhost:8765/resource-server-mvc-1`

You'll be redirected to the Authorization Server to authenticate. 
Use the following credentials: `user`/`password` or `admin`/`admin`. 
And approve the authorization for the `fooScope` OAuth scope. 
