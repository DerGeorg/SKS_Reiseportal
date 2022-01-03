# Config Server
*Manages all configs for all the microservices*

### Features
* Article Server Config
* Stats Server Config
* Sightseeing Server Config
* Money Server Config
#### Port
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
#### Port
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
#### Port
```
8071
```

# Sightseeing Server
*Manages all the sightseeing entrys*
### Features
* Get Sightseeing Entry (all, byID)
### API
After starting the Server, you can view the API in [Swagger - Sightseeing Server](http://localhost:8061/swagger-ui.html)

#### Port
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

#### Port
```
8081
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
