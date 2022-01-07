![all](https://user-images.githubusercontent.com/22521386/148586261-4da9b1e7-75ad-425f-82d5-ada493332002.jpeg)
![db](https://user-images.githubusercontent.com/22521386/148586265-22829d4e-1cee-4e5f-b20e-191c9f241f9d.jpeg)


# Gateway Server
Spring cloud Gateway with round-robbin Load Balancer strategy

### For services:
* Article Server
* Stats Server
* Sightseeing Server

#### Port
```
5555
```

### Routes
| Service           | Route                                       |
|-------------------|---------------------------------------------|
| Articleserver     | http://localhost:5555/articleserver/....    |
| Sightseeingserver | http://localhost:5555/sightseeingserver/... |
| Statsserver       | http://localhost:5555/statsserver/...       |

> :warning: Gateway is forwarding to http://localhost:<PortOfService>/api/<RouteOfService>

### Example for loadbalancing
*When starting for example multiple Article Server (Ports:  [8080, 8765]) and then do a request to the gateway on port 5555, than you will see that every call to the gateway will forward to the 2 server instances. 1. Request is on 8080, 2. request is on 8765, 3. on 8080 again, ....*

> :warning: For Testing the loadbalancer, simply start the second instance of article server in intellij 

# Eureka Server
Service registry
### For services:
* Article Server
* Stats Server
* Sightseeing Server

### Port
```
8761
```

# Config Server
*Manages all configs for all the microservices*

### Features
* Article Server Config
* Stats Server Config
* Sightseeing Server Config
* Money Server Config
### Port
```
8888
```


# Article Server
*Manages all articles*

### Features
* Adding Article
* Get Article (all, byID)
### API
After starting the Server, you can view the API in [Swagger - Article Server](http://localhost:8080/swagger-ui.html)
### Port
```
8080
```

# Stats Server
*Manages all the stats*
### Features
* Adding Article
* Get Stats (month, all, byID)
* Count stats +1
### API
After starting the Server, you can view the API in [Swagger - Stats Server](http://localhost:8071/swagger-ui.html)
### Port
```
8071
```

# Sightseeing Server
*Manages all the sightseeing entrys*
### Features
* Get Sightseeing Entry (all, byID)
### API
After starting the Server, you can view the API in [Swagger - Sightseeing Server](http://localhost:8061/swagger-ui.html)

### Port
```
8061
```
# Money Server
*Manages the monthly payment*
*Scheduling every Month once*
*Cron: 1 48 13 19 * ?*
#### Port
```
8051
```

# Web App


## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Port
```
8081
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
