# User rest api
Rest api for users.

Endpoint: /api/v1/users

Note: Anything surrounded by {{}} is to be replaced with
appropriate variable.
# User
## GET
### Get Users
Gets the list of users.

`GET /api/v1/users`
#### Examples
> HTTP Request
```HTTP
GET /api/v1/users HTTP/1.1
Host: {{HOST}}
```
> Response
```json
[
    {
        "id": "6722c1d538e9d1070bcf3735",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "username": "user1",
        "email": "user1@example.org"
    },
    {
        "id": "6722e74398e9e1070bcf3737",
        "createdOn": "2024-10-30T23:54:43.763+00:00",
        "username": "user2",
        "email": "user2@example.org"
    }
]
```
> 200 Response
#### Responses

|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)
### Get by id
Gets a user with given id.

`GET /api/v1/users/{{id}}`
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
GET /api/v1/users/validId HTTP/1.1
Host: {{HOST}}
```
> Response
```json
[
    {
        "id": "validId",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "username": "user",
        "email": "user@example.org"
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
Gets a user by username. Returns all users with that username.

`GET /api/v1/users?username={{username}}`

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|username|query|string| yes |username of user|
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
GET /api/v1/users?username=user1 HTTP/1.1
Host: {{HOST}}
```
> Response
```json
[
    {
        "id": "6722c1d538e9d1070bcf3735",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "username": "user1",
        "email": "user1@example.org"
    },
    {
        "id": "6722c1d538e9d1070bcf3737",
        "createdOn": "2024-11-30T23:35:49.737+00:00",
        "username": "user1",
        "email": "user1alt@example.org"
    }
]
```
> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
GET /api/v1/users?username=notAUser HTTP/1.1
Host: {{HOST}}
```
> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
## POST
### Create User
Create a user with given id by giving body parameters for a user.
This action will store a cookie on client storing the userId of this client.

`POST /api/v1/users`
> Body Parameters
```json
{
  "username": "{{username}}",
  "email": "{{email}}"
}
```
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
POST /api/v1/users HTTP/1.1
Host: {{HOST}}
Content-Type: application/json
Content-Length: 66

{
    "username": "user1",
    "email": "user1@example.org"
}
```
> Response
```json
[
    {
        "id": "6722c1d538e9d1070bcf3735",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "username": "user1",
        "email": "user1@example.org"
    }
]
```
> 201 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|201|[Created](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.2)|
|409|[Conflict](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.8)|
## PUT
### Update User
Update a user by giving body parameters for a user.

`PUT /api/v1/users/{{id}}`
> Body Parameters
```json
{
  "username": "{{username}}",
  "email": "{{email}}"
}
```
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
PUT /api/v1/users/validId HTTP/1.1
Host: {{HOST}}
Content-Type: application/json
Content-Length: 66

{
    "username": "newusername",
    "email": "newuseremail@example.org"
}
```
> Response
```json
[
    {
        "id": "validId",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "username": "newusername",
        "email": "newuseremail@example.org"
    }
]
```
> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
PUT /api/v1/users/notValidId HTTP/1.1
Host: {{HOST}}
Content-Type: application/json
Content-Length: 66

{
    "username": "newusername",
    "email": "newuseremail@example.org"
}
```
> Response

> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
## DELETE 
### Delete User
Delete a user with given id.

`DELETE /api/v1/users/{{id}}`

### Examples
Successful Response Example:
> HTTP Request
```HTTP
DELETE /api/v1/users/validId HTTP/1.1
Host: {{HOST}}
```
> Response
```json
[
    {
        "id": "validId",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "username": "user",
        "email": "user@example.org"
    }
]
```
> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
DELETE /api/v1/users/notValidId HTTP/1.1
Host: {{HOST}}
```
> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
