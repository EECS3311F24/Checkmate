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
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
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
### Get user data
Gets userdata by users id. Returns the userdata associated with id if it exists.

`GET /api/v1/users/{{id}}/userdata`
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
GET /api/v1/users/validId/userdata HTTP/1.1
Host: {{HOST}}
```
> Response
```json
[
    {
        "id": "6722c1d538e9d1070bcf3735",
        "language": "english",
        "theme": "light",
        "wins": "0",
        "loses": "1000",
        "gamesPlayed": "1053"
    }
]
```
> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
GET /api/v1/users/notValidId/userdata HTTP/1.1
Host: {{HOST}}
```
> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
### Get Authentication
Gets the user you are authenticated to. Requires a user cookie of userId.

`GET /api/v1/users/{{id}}/userdata`
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
GET /api/v1/users/authenticate HTTP/1.1
Host: {{HOST}}
```
> Response
```json
[
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
GET /api/v1/users/authenticate HTTP/1.1
Host: {{HOST}}
```
> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|401|[Unauthorized](https://datatracker.ietf.org/doc/html/rfc7235#section-3.1)|
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
This user must provide a correct password to update.

`PUT /api/v1/users/{{id}}`
> Body Parameters
```json
{
  "username": "{{username}}",
  "email": "{{email}}",
  "passwordHash": "{{passwordHash}}"
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
    "username": "username",
    "email": "useremail@example.org"
    "passwordHash": "********"
}
```
> Response
```json
[
    {
        "id": "validId",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "username": "username",
        "email": "useremail@example.org"
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
    "passwordHash": "********"
}
```
> Response

> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|401|[Unauthorized](https://datatracker.ietf.org/doc/html/rfc7235#section-3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
### Update UserData
Update userdata by giving body parameters for the new userdata.
This user must provide a cookie with the userid to authenticate changes.

`PUT /api/v1/users/{{id}}/userdata`
> Body Parameters
```json
[
    {
        "language": "{{language}}",
        "theme": "{{theme}}",
        "wins": "{{wins}}",
        "loses": "{{loses}}",
        "gamesPlayed": "{{gamesPlayed}}"
    }
]
```
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
    "language": "english",
    "theme": "dark",
    "wins": "14",
    "loses": "1000",
    "gamesPlayed": "1076"
}
```
> Response
```json
[
    {
        "language": "english",
        "theme": "dark",
        "wins": "14",
        "loses": "1000",
        "gamesPlayed": "1076"
}
]
```
> 200 Response
____
Not Found Response Example:
> HTTP Request
```HTTP
PUT /api/v1/users/notValidId/userdata HTTP/1.1
Host: {{HOST}}
Content-Type: application/json
Content-Length: 66

{
    "language": "english",
    "theme": "dark",
    "wins": "14",
    "loses": "1000",
    "gamesPlayed": "1076"
}
```
> Response

> 404 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|401|[Unauthorized](https://datatracker.ietf.org/doc/html/rfc7235#section-3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
### Authenticate
Authenticate a user by providing proper user login username or email and
a correct password. The user login can either be username or email.
A cookie may also be used to authenticate.

`PUT /api/v1/users/authenticate`
> Body Parameters
```json
{
  "username": "{{usernameLogin}}",
  "passwordHash": "{{passwordHash}}"
}
```
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
PUT /api/v1/users/authenticate HTTP/1.1
Host: {{HOST}}
Content-Type: application/json
Content-Length: 66

{
    "username": "username",
    "passwordHash": "********"
}
```
> Response
```json
[
    {
        "id": "validId",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "username": "username",
        "email": "useremail@example.org"
    }
]
```
> 200 Response
____
Wrong Password Response Example:
> HTTP Request
```HTTP
PUT /api/v1/users/authenticate HTTP/1.1
Host: {{HOST}}
Content-Type: application/json
Content-Length: 66

{
    "username": "username",
    "passwordHash": "A wrong password"
}
```
> Response

> 401 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|401|[Unauthorized](https://datatracker.ietf.org/doc/html/rfc7235#section-3.1)|
|404|[Not Found](https://datatracker.ietf.org/doc/html/rfc7231#section-6.5.4)|
### Logout
Logout a user by providing a userId cookie.

`PUT /api/v1/users/logout`
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
PUT /api/v1/users/authenticate HTTP/1.1
Host: {{HOST}}
Content-Type: application/json
Content-Length: 66
```
> Response

> 200 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
## PATCH
### Set Password
Set a users password by providing the old password and the new password.

`PATCH /api/v1/users/{{id}}?oldPassword={{oldPassword}}&password={{password}}`

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|oldPassword|query|string| no |The old password of the user|
|password|query|string| yes |The new password of the user|
#### Examples
Successful Response Example:
> HTTP Request
```HTTP
PATCH /api/v1/users/vailidId?oldPassword=******&password=***** HTTP/1.1
Host: {{HOST}}
```
> Response
```json
[
    {
        "id": "6722c1d538e9d1070bcf3735",
        "createdOn": "2024-10-30T23:35:49.737+00:00",
        "username": "username",
        "email": "username@example.org"
    }
]
```
> 200 Response
____
Wrong Password Response Example:
> HTTP Request
```HTTP
PATCH /api/v1/users/vailidId?oldPassword=wrongPassword&password=***** HTTP/1.1
Host: {{HOST}}
```
> 401 Response
#### Responses
|HTTP Status Code |Meaning|
|---|---|
|200|[OK](https://datatracker.ietf.org/doc/html/rfc7231#section-6.3.1)|
|401|[Unauthorized](https://datatracker.ietf.org/doc/html/rfc7235#section-3.1)|
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
