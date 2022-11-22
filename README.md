# Stocks Rest API

## About the Project
This is a basic REST API project built with Spring Boot to perform certain CRUD operations.
It uses PostgreSQL to store data
If your database connection properties work, you can call some REST endpoints defined in com.khoubyari.example.api.rest.hotelController on port 9090.

Here are some endpoints you can call:
* POST http://localhost:9090/api/stocks
* GET http://localhost:9090/api/stocks
* GET http://localhost:9090/api/stocks/{{stockId}}
* PUT http://localhost:9090/api/stocks/{{stockId}}

## Create new Stock
* Endpoint: http://localhost:9090/api/stocks
### PayLoad
curl --location --request POST 'http://localhost:9090/api/stocks/'


### Response

json
{
"message": "Request Successful",
"success": true,
"payload": {
"name": "Phillips Electric Iron",
"currentPrice": 8000.0,
"createDate": "2022-11-21T22:17:09.086+00:00",
"lastUpdate": "2022-11-21T22:17:09.086+00:00",
"id": 4
}
}


## Get A List of Stocks
* Endpoint: http://localhost:9090/api/stocks
### PayLoad
curl --location --request GET 'http://localhost:9090/api/stocks/'


### Response
{
"message": "Request Successful",
"success": true,
"payload": [
{
"name": "Ox Ceiling Fan",
"currentPrice": 10000.0,
"createDate": "2022-11-21T22:06:51.659+00:00",
"lastUpdate": "2022-11-21T22:06:51.659+00:00",
"id": 3
},
{
"name": "Binatone 10.0 Standing Fan",
"currentPrice": 25000.0,
"createDate": "2022-11-21T22:06:51.657+00:00",
"lastUpdate": "2022-11-21T22:06:51.657+00:00",
"id": 2
},
{
"name": "Ox 18.0 Standing Fan",
"currentPrice": 55550.0,
"createDate": "2022-11-21T22:06:51.572+00:00",
"lastUpdate": "2022-11-21T22:06:51.572+00:00",
"id": 1
}
]
}

---

## Get One Stock from the List
* Endpoint: http://localhost:9090/api/stocks/3
### PayLoad
curl --location --request PUT 'http://localhost:9090/api/stocks/3'

### Response

json
{
"message": "Request Successful",
"success": true,
"payload": {
"name": "Ox Ceiling Fan",
"currentPrice": 10000.0,
"createDate": "2022-11-21T22:06:51.659+00:00",
"lastUpdate": "2022-11-21T22:06:51.659+00:00",
"id": 3
}
}

### Update Stock

* Endpoint: `http://localhost:8090/api/stocks/1`

### Payload

curl --location --request PUT 'http://localhost:8090/api/stocks/1' \
--header 'Content-Type: application/json' \
--data-raw '{
"currentPrice" : "8000"
}'

### Response
json
{
"message": "Request Successful",
"success": true,
"payload": {
"name": "Ox 18.0 Standing Fan",
"currentPrice": 8000.0,
"createDate": "2022-11-21T22:16:40.177+00:00",
"lastUpdate": "2022-11-21T22:17:27.711+00:00",
"id": 1
}
}
}


### Get Stock By Id

* Endpoint: `http://localhost:9090/api/stocks/3`

### Payload


curl --location --request GET 'http://localhost:8090/api/stocks/3' \


### Response

json
{
"message": "success",
"timeStamp": "2022-11-22T00:16:13.832056",
"stock": {
"id": 3,
"name": "BitCoin",
"currentPrice": 10000,
"createDate": "2022-11-22T00:15:23.555844",
"lastUpdate": "2022-11-22T00:15:23.556001"
}
}
## Getting Started
This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the java -jar command.
* Clone this repository
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running mvn clean package
* 

### Prerequisites

### Installing
TODO: Installing

### Built With 
* [Spring Boot 2](https://projects.spring.io/spring-boot/)
* [Spring FrameWork 5](https://projects.spring.io/spring-framework/)
