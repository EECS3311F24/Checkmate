# Chat rest api
Rest api for chats.

Endpoint: /api/v1/chats

Note: Anything surrounded by {{}} is to be replaced with
appropriate variable.
# ChatMessage
## GET
### Get Chat Messages
Gets the list of chat messages.

`GET /api/v1/chats`
#### Examples
> HTTP Request
```HTTP
GET /api/v1/chats HTTP/1.1
Host: {{HOST}}
```
> Response
```json
[
    {
        "id": "6722c1d538e9d1070bcf3735",
        "userId": "9723c1d538e9e1071bcg3739",
        "boardId": "7722c1d538e9e1070bcf3733",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "message:": "Hello!"
    },
    {
        "id": "6722c1d538e9d1070bcf3736",
        "userId": "2723c1d138l9e1071bcg3736",
        "boardId": "7722c1d538e9e1070bcf3733",
        "createdOn": "2024-10-30T23:36:55.737+00:00",
        "message:": "Bye!"
    }
]
```
> 200 Response
#### Responses

|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
### Get by id
Gets a chat message with given id.

`GET /api/v1/chats/{{id}}`
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
GET /api/v1/chats/validId HTTP/1.1
Host: {{HOST}}
```
> Response
```json
[
    {
        "id": "validId",
        "userId": "9723c1d538e9e1071bcg3739",
        "boardId": "7722c1d538e9e1070bcf3733",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "message:": "Hello!"
    }
]
```
> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
GET /api/v1/users/notValidId HTTP/1.1
Host: {{HOST}}
```
> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
### Get by username
Gets a list of chat messages by board id. Returns all chat message with that board id.

`GET /api/v1/chats?boardId={{boardId}}`

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|boardId|query|string| yes |boardId of the chat message|
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
GET /api/v1/chats?boardId=validBoardId HTTP/1.1
Host: {{HOST}}
```
> Response
```json
[
    {
        "id": "6722c1d538e9d1070bcf3735",
        "userId": "9723c1d538e9e1071bcg3739",
        "boardId": "validBoardId",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "message:": "Hello!"
    },
    {
        "id": "6722c1d538e9d1070bcf3736",
        "userId": "2723c1d138l9e1071bcg3736",
        "boardId": "validBoardId",
        "createdOn": "2024-10-30T23:36:55.737+00:00",
        "message:": "Bye!"
    }
]
```
> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
GET /api/v1/chats?boardId=invalidBoardId HTTP/1.1
Host: {{HOST}}
```
> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
## POST
### Create Chat Message
Create a chat message with given message by giving body parameters for a chat message.

`POST /api/v1/chats`
> Body Parameters
```json
{
    "userId": "{{userId}}",
    "boardId": "{{boardId}}",
    "message:": "{{message}}"
}
```
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
POST /api/v1/chats HTTP/1.1
Host: {{HOST}}
Content-Type: application/json
Content-Length: 66

{
        "userId": "2723c1d138l9e1071bcg3736",
        "boardId": "7722c1d538e9e1070bcf3733",
        "message:": "Bye!"
}
```
> Response
```json
[
    {
        "id": "6722c1d538e9d1070bcf3736",
        "userId": "2723c1d138l9e1071bcg3736",
        "boardId": "7722c1d538e9e1070bcf3733",
        "createdOn": "2024-10-30T23:36:55.737+00:00",
        "message:": "Bye!"
    }
]
```
> 201 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|201|[Created](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.2)|
|409|[Conflict](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.8)|
## PATCH
### PATCH Chat Message
PATCH a chat message by giving body parameters for a new message.
This message must be a non empty chat message.

`PATCH /api/v1/chats/{{id}}`
> Body Parameters (Plain text)
```
{{message}}
```
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
PATCH /api/v1/chats/validId HTTP/1.1
Host: {{HOST}}
Content-Type: text/plain

Hello world!
```
> Response
```json
[
    {
        "id": "validId",
        "userId": "9723c1d538e9e1071bcg3739",
        "boardId": "7722c1d538e9e1070bcf3733",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "message:": "Hello world!"
    }
]
```
> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
PATCH /api/v1/chats/notValidId HTTP/1.1
Host: {{HOST}}
Content-Type: text/plain

Goodbye world!
```
> Response

> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|401|[Unauthorized](https://datatracker.ietf.org/doc/html/rfc7235#section-3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
## DELETE 
### Delete Chat Message
Delete a chat message with given id.

`DELETE /api/v1/chats/{{id}}`
### Examples
Successful Response Example:
> HTTP Request
```HTTP
DELETE /api/v1/chats/validId HTTP/1.1
Host: {{HOST}}
```
> Response
```json
[
    {
        "id": "validId",
        "userId": "9723c1d538e9e1071bcg3739",
        "boardId": "7722c1d538e9e1070bcf3733",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "message:": "Hello world!"
    }
]
```
> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
DELETE /api/v1/chats/notValidId HTTP/1.1
Host: {{HOST}}
```
> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|