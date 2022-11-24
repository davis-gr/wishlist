# Wishlist application

Configuration parameters for defining a database connection are available in `application.properties`

## Run the app

    ./gradlew bootRun

## Run the tests

    ./gradlew clean test

# REST API

The REST API to the wishlist app is described below.

## Get list of Wishes

### Request

`GET /wish/`

    curl -i http://localhost:8080/wish/

### Response

    HTTP/1.1 200 OK
    Content-Type: application/json

    []

## Create a new Wish

### Request

`POST /wish/add/`

    curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST -d "{\"wishName\":\"test wish\",\"wishDescription\":\"test wish description\"}" http://localhost:8080/wish/add/


### Response

    HTTP/1.1 201 Created
    Content-Type: application/json

    {"id":1,"wishName":"test wish","wishDescription":"test wish description"}

## Get a specific Wish

### Request

`GET /wish/id`

    curl -i http://localhost:8080/wish/1

### Response

    HTTP/1.1 200 OK
    Content-Type: application/json

    {"id":1,"wishName":"test wish","wishDescription":"test wish description"}

## Get a non-existent Wish

### Request

`GET /wish/id`

    curl -i http://localhost:8080/wish/9999

### Response

    HTTP/1.1 404 Not Found
    Content-Type: application/json

    {"status":404,"reason":"Not found"}

## Create another new Wish

### Request

`POST /wish/add/`

    curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST -d "{\"wishName\":\"another wish\",\"wishDescription\":\"another wish description\"}" http://localhost:8080/wish/add/

### Response

    HTTP/1.1 201 Created
    Content-Type: application/json

    {"id":2,"wishName":"another wish","wishDescription":"another wish description"}

## Get list of Wishes again

### Request

`GET /wish/`

    curl -i http://localhost:8080/wish

### Response

    HTTP/1.1 200 OK
    Content-Type: application/json

    [{"id":1,"wishName":"test wish","wishDescription":"test wish description"},{"id":2,"wishName":"another wish","wishDescription":"another wish description"}]

## Change a Wish

### Request

`PUT /wish/:id`

    curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X PUT -d "{\"wishName\":\"changed test wish\",\"wishDescription\":\"changed test wish description\"}" http://localhost:8080/wish/1

### Response

    HTTP/1.1 200 OK
    Content-Type: application/json

    {"id":1,"wishName":"changed test wish","wishDescription":"changed test wish description"}

## Get changed Wish

### Request

`GET /wish/id`

    curl -i http://localhost:8080/wish/1

### Response

    HTTP/1.1 200 OK
    Content-Type: application/json

    {"id":1,"wishName":"changed test wish","wishDescription":"changed test wish description"}

## Delete a Wish

### Request

`DELETE /wish/id`

    curl -i -X DELETE http://localhost:8080/wish/1

### Response

    HTTP/1.1 204 No Content

## Try to delete same Wish again

### Request

`DELETE /wish/id`

    curl -i -X DELETE http://localhost:8080/wish/1

### Response

    HTTP/1.1 404 Not Found
    Content-Type: application/json

    {"status":404,"reason":"Not found"}

## Get deleted Wish

### Request

`GET /wish/1`

    curl -i http://localhost:8080/wish/1

### Response

    HTTP/1.1 404 Not Found
    Content-Type: application/json

    {"status":404,"reason":"Not found"}

## Transform Users endpoint

To extract and combine names of people from a JSON request 

### Request

`POST /users/`

    curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X POST -d "{\"users\":[{\"type\": \"user\",\"id\": 26,\"name\": \"christianbale\",\"email\": \"cbale@batman.com\"},{\"type\": \"user\",\"id\": 42,\"name\": \"michaelkeaton\",\"email\": \"mkeaton@batman.com\"},{\"type\": \"user\",\"id\": 98,\"name\": \"benaffleck\",\"email\": \"baffleck@another.bat\"}]}" http://localhost:8080/users/

### Response

    HTTP/1.1 200 OK
    Content-Type: application/json

    christianbale, michaelkeaton, benaffleck
