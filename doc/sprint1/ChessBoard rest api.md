# ChessBoard rest api
Rest api for chess boards.

Endpoint: /api/v1/boards

Note: Anything surrounded by {{}} is to be replaced with
appropriate variable.
# ChessBoard
## GET
### Get ChessBoards
Gets the list of chess boards.

`GET /api/v1/boards`
#### Examples
> HTTP Request
```HTTP
GET /api/v1/boards HTTP/1.1
Host: {{HOST}}
```
> Response

Responds with a json list of chess boards. Too long to display.

> 200 Response
#### Responses

|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)
### Get by id
Gets a chess board by id.

`GET /api/v1/boards/{{id}}`
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
GET /api/v1/boards/validId HTTP/1.1
Host: {{HOST}}
```
> Response

Responds with a json chess board. Too long to display.

> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
GET /api/v1/boards/notValidId HTTP/1.1
Host: {{HOST}}
```
> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
### Get Chess piece
Gets a chess piece at a request spot on the request board.
The repsponse body must contain a json move.

`GET /api/v1/boards/{{id}}/move`
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
GET /api/v1/boards/validId/move HTTP/1.1
Host: {{HOST}}
```
> Body
```json
{
    "row": 7,
    "col": 4
}
```
> Response

```json
{
    "color": "W",
    "movesHistory": [
        {
            "row": 7,
            "col": 4,
            "valid": true
        }
    ],
    "char": "K"
}
```
> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
GET /api/v1/boards/validId/move HTTP/1.1
Host: {{HOST}}
```
> Body
```json
{
    "row": 8,
    "col": 4
}
```
> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
### Get whose turn
Gets whose turn it is to move.

`GET /api/v1/boards/{{id}}/whosTurn`
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
GET /api/v1/boards/validId/whosTurn HTTP/1.1
Host: {{HOST}}
```
> Response

```json
{
    "playerColor": "W"
}
```
> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
GET /api/v1/boards/notValidId/whosTurn HTTP/1.1
Host: {{HOST}}
```
> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
## POST
### Create ChessBoard
Create a chess board with user id of player 1 and player 2.

`POST api/v1/boards?id1={{id1}}&id2={{id2}}`
```
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
POST api/v1/boards?id1=id1&id2=id2 HTTP/1.1
Host: {{HOST}}
```
> Response

Responds with a json chess board. Too long to display.

> 201 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|201|[Created](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.2)|
|409|[Conflict](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.8)|
## PUT
### Reset ChessBoard
Reset a chess board with given id to default state.

`PUT /api/v1/boards/{{id}}`
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
PUT /api/v1/boards/validId HTTP/1.1
Host: {{HOST}}
```
> Response

Responds with a json chess board. Too long to display.

> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
PUT /api/v1/boards/notValidId HTTP/1.1
Host: {{HOST}}
```
> Response

> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
### Join ChessBoard
Join a random chess board, and get given a guest user if needed.
This stores a cookie of the guest userId if no user is contained by
reqester. If no chess boards are available adds to a new chess board.

`PUT /api/v1/boards`
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
PUT /api/v1/boards HTTP/1.1
Host: {{HOST}}
```
> Response

Responds with a json chess board. Too long to display.

> 200 Response
____
Not Found Response Example (When userId cookie has no user):
> HTTP Request
```HTTP
PUT /api/v1/boards/notValidId HTTP/1.1
Host: {{HOST}}
```
> Response

> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
## PATCH
### Move a chess piece
Move a chess piece with give response body on given chess board with id.
A userId cookie is required that is obtained from creating a user.

`PUT /api/v1/boards/{{id}}/moves`
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
PATCH /api/v1/boards/validId/moves HTTP/1.1
Host: {{HOST}}
```
> Body
```json
{
    "start": {
        "row": 6,
        "col": 4
    },
    "end": {
        "row": 4,
        "col": 4
    }
}
```
> Response

Responds with a json chess board. Too long to display.

> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
PUT /api/v1/boards/notValidId/moves HTTP/1.1
Host: {{HOST}}
```
> Response

> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|400|[Bad Request](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
|409|[Conflict](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.8)|
## DELETE 
### Delete ChessBoard
Delete a chess board with given id.

`DELETE /api/v1/boards/{{id}}`

### Examples
Successful Response Example:
> HTTP Request
```HTTP
DELETE /api/v1/boards/validId HTTP/1.1
Host: {{HOST}}
```
> Response

Responds with a json chess board. Too long to display.

> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
DELETE /api/v1/boards/notValidId HTTP/1.1
Host: {{HOST}}
```
> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
