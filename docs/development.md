# Development Guide

## Development Environment Setup

### Prerequisites
- Java 17 or later
- Maven 3.8.x or later
- Git
- Docker (optional)
- Google Cloud Platform account for Calendar API

### Local Development Setup

1. Clone the repository:
```bash
git clone https://github.com/yourusername/teammanager.git
cd teammanager
```

2. Set up Google Calendar API:
   - Create a project in Google Cloud Console
   - Enable Google Calendar API
   - Create OAuth 2.0 credentials
   - Download credentials.json
   - Place it in `src/main/resources/`

3. Configure environment variables:
```bash
cp .env.example .env
# Edit .env with your configuration
```

4. Build the project:
```bash
mvn clean install
```

5. Run the application:
```bash
mvn spring-boot:run
```

### Using GitHub Codespaces

1. Open the project in GitHub Codespaces
2. The development container will automatically set up the environment
3. Run the application using the provided scripts

## Project Structure

```
teammanager/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/teammanager/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── TeamManagerApplication.java
│   │   └── resources/
│   └── test/
├── frontend/
│   ├── app-export.json
│   └── custom-components/
└── docs/
```

## Development Workflow

1. Create a new branch for your feature:
```bash
git checkout -b feature/your-feature-name
```

2. Make your changes and commit them:
```bash
git add .
git commit -m "Description of your changes"
```

3. Push your changes:
```bash
git push origin feature/your-feature-name
```

4. Create a Pull Request on GitHub

## Testing

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=TestClassName

# Run specific test method
mvn test -Dtest=TestClassName#testMethodName
```

### Writing Tests

- Unit tests should be in `src/test/java`
- Test classes should end with `Test`
- Use JUnit 5 for testing
- Mock external dependencies using Mockito

Example test:
```java
@Test
void testUserCreation() {
    // Arrange
    User user = new User();
    user.setUsername("testuser");
    
    // Act
    User savedUser = userService.createUser(user);
    
    // Assert
    assertNotNull(savedUser.getId());
    assertEquals("testuser", savedUser.getUsername());
}
```

## Code Style

- Follow Google Java Style Guide
- Use 4 spaces for indentation
- Maximum line length: 100 characters
- Use meaningful variable and method names
- Add comments for complex logic

## Debugging

### Local Debugging
1. Run the application in debug mode:
```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
```

2. Attach your IDE's debugger to port 5005

### Logging
- Use SLF4J for logging
- Log levels:
  - ERROR: For errors that need immediate attention
  - WARN: For potentially harmful situations
  - INFO: For general information
  - DEBUG: For detailed information
  - TRACE: For very detailed information

## Performance Considerations

- Use appropriate indexes in database
- Implement caching where necessary
- Optimize database queries
- Use pagination for large datasets
- Implement rate limiting for API endpoints

## Security Best Practices

- Never commit sensitive data
- Use environment variables for secrets
- Implement proper input validation
- Use prepared statements for database queries
- Follow OWASP security guidelines

## Deployment

### Production Deployment
1. Build the application:
```bash
mvn clean package -Pprod
```

2. Run the application:
```bash
java -jar target/teammanager.jar
```

### Docker Deployment
1. Build the Docker image:
```bash
docker build -t teammanager .
```

2. Run the container:
```bash
docker run -p 8080:8080 teammanager
``` 