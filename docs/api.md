# API Documentation

## Base URL
All API endpoints are prefixed with `/api`

## Authentication

### Login
```http
POST /api/auth/login
Content-Type: application/json

{
    "username": "string",
    "password": "string"
}
```

Response:
```json
{
    "token": "string",
    "user": {
        "id": "string",
        "username": "string",
        "role": "string"
    }
}
```

### Logout
```http
POST /api/auth/logout
Authorization: Bearer {token}
```

## User Management

### List Users
```http
GET /api/users
Authorization: Bearer {token}
```

Response:
```json
[
    {
        "id": "string",
        "username": "string",
        "email": "string",
        "role": "string"
    }
]
```

### Create User
```http
POST /api/users
Authorization: Bearer {token}
Content-Type: application/json

{
    "username": "string",
    "email": "string",
    "password": "string",
    "role": "string"
}
```

### Update User
```http
PUT /api/users/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
    "username": "string",
    "email": "string",
    "role": "string"
}
```

### Delete User
```http
DELETE /api/users/{id}
Authorization: Bearer {token}
```

## Team Management

### List Teams
```http
GET /api/teams
Authorization: Bearer {token}
```

Response:
```json
[
    {
        "id": "string",
        "name": "string",
        "description": "string",
        "members": [
            {
                "id": "string",
                "name": "string"
            }
        ]
    }
]
```

### Create Team
```http
POST /api/teams
Authorization: Bearer {token}
Content-Type: application/json

{
    "name": "string",
    "description": "string"
}
```

### Update Team
```http
PUT /api/teams/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
    "name": "string",
    "description": "string"
}
```

## Event Management

### List Events
```http
GET /api/events
Authorization: Bearer {token}
```

Response:
```json
[
    {
        "id": "string",
        "title": "string",
        "description": "string",
        "startTime": "datetime",
        "endTime": "datetime",
        "location": "string",
        "teamId": "string"
    }
]
```

### Create Event
```http
POST /api/events
Authorization: Bearer {token}
Content-Type: application/json

{
    "title": "string",
    "description": "string",
    "startTime": "datetime",
    "endTime": "datetime",
    "location": "string",
    "teamId": "string"
}
```

### Update Event
```http
PUT /api/events/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
    "title": "string",
    "description": "string",
    "startTime": "datetime",
    "endTime": "datetime",
    "location": "string",
    "teamId": "string"
}
```

### Delete Event
```http
DELETE /api/events/{id}
Authorization: Bearer {token}
```

## Error Responses

All endpoints may return the following error responses:

### 400 Bad Request
```json
{
    "error": "string",
    "message": "string"
}
```

### 401 Unauthorized
```json
{
    "error": "Unauthorized",
    "message": "Invalid or expired token"
}
```

### 403 Forbidden
```json
{
    "error": "Forbidden",
    "message": "Insufficient permissions"
}
```

### 404 Not Found
```json
{
    "error": "Not Found",
    "message": "Resource not found"
}
```

### 500 Internal Server Error
```json
{
    "error": "Internal Server Error",
    "message": "An unexpected error occurred"
}
``` 