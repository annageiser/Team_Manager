# Architecture Overview

## System Architecture

The TeamManager application follows a modern microservices architecture with the following components:

### Backend (Java Spring Boot)
- RESTful API layer
- Service layer for business logic
- Data access layer with JPA/Hibernate
- Google Calendar integration service
- Security layer with JWT authentication

### Frontend (Budibase)
- Low-code platform for rapid UI development
- Responsive design for all devices
- Integration with backend REST API
- Custom components for team management

## Technology Stack

### Backend
- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- H2 Database (Development)
- PostgreSQL (Production)
- Maven for dependency management

### Frontend
- Budibase Platform
- Custom JavaScript components
- REST API integration
- Responsive CSS

### DevOps
- GitHub Codespaces for development
- Docker containerization
- GitHub Actions for CI/CD

## Data Flow

1. Client requests are authenticated through JWT
2. Requests are processed by the REST API layer
3. Business logic is handled in the service layer
4. Data persistence is managed through JPA
5. Google Calendar integration is handled asynchronously
6. Responses are returned to the client

## Security Architecture

- JWT-based authentication
- Role-based access control (RBAC)
- Secure password hashing
- HTTPS encryption
- OAuth2 for Google Calendar integration

## Deployment Architecture

- Containerized deployment using Docker
- Scalable microservices architecture
- Separate development and production environments
- Automated deployment through CI/CD pipeline 