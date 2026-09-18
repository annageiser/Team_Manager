# ⚽️ Team Manager

A simplified team management application for sports clubs with Google Calendar integration. https://teammanager.budibase.app/app/teammanager#/dashboard

## Features

- Team Management
- Event Scheduling
- Player Management
- Google Calendar Integration
- Responsive Design

## Documentation

- [Architecture Overview](docs/architecture.md) - System architecture and technical details
- [API Documentation](docs/api.md) - Detailed API endpoint documentation
- [Development Guide](docs/development.md) - Setup and contribution guidelines
- [User Guide](docs/user-guide.md) - End-user documentation and instructions

## Prerequisites

- Java 17
- Maven
- Google Calendar API Credentials

## Setup

1. Clone the repository:
```bash
git clone https://github.com/yourusername/teammanager.git
cd teammanager
```

2. Configure Google Calendar API:
   - Create a project in Google Cloud Console
   - Enable Google Calendar API
   - Create OAuth 2.0 credentials
   - Place the credentials.json file in `src/main/resources/`

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080/api`

## API Documentation

### Authentication Endpoints
- POST /api/auth/login - User Login
- POST /api/auth/logout - User Logout

### User Management
- GET /api/users - List all users (Admin)
- POST /api/users - Create new user (Admin)
- PUT /api/users/{id} - Edit user (Admin)
- DELETE /api/users/{id} - Delete user (Admin)

### Team Management
- GET /api/teams - List all teams
- POST /api/teams - Create new team (Admin)
- PUT /api/teams/{id} - Edit team (Admin)

### Event Management
- GET /api/events - List all events
- GET /api/events/public - Public events
- POST /api/events - Create new event (Admin)
- PUT /api/events/{id} - Edit event (Admin)
- DELETE /api/events/{id} - Delete event (Admin)

## Frontend

The frontend is built using Budibase. To set up the frontend:

1. Create a Budibase account
2. Import the app-export.json file from the frontend directory
3. Configure the API endpoints in Budibase

## Development

This project uses GitHub Codespaces for development. The development container is already configured in the `.devcontainer` directory.

## Testing

Run the tests using:
```bash
mvn test
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.
