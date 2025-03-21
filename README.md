# Team Manager
Group project for the module 'Internet Technology'. It is a minimal web-based team management application for sports teams to organize their association, teams, players, games, etc.

## Contents
...


## Analysis


### Scenario
TeamManager is a team management tool that allows coaches and players to organize training sessions, matches, and team communication efficiently.

### User Stories
1.	As an Admin, I want to have a web app accessible on mobile and desktop.
2.	As an Admin, I want to create and manage teams and players.
3.	As an Admin, I want to schedule training sessions and matches.
4.	As an Admin, I want to track player attendance for each session.
5.	As an Admin, I want to send notifications to players.
6.	As a Player, I want to confirm my attendance for a training or match.
7.	As a Player, I want to view my team’s schedule.
8.	As a Player, I want to receive updates about changes to the schedule.

### Use Case
1.	UC-1 [Manage Teams]: Admin can create, update, and delete teams.
2.	UC-2 [Manage Players]: Admin can add or remove players from teams.
3.	UC-3 [Schedule Events]: Admin can create training sessions and matches.
4.	UC-4 [Track Attendance]: Players can mark attendance, and the admin can review it.
5.	UC-5 [Send Notifications]: Admin can notify players about schedule updates.

## Design
...

### Wireframe
🚧 A wireframe should outline the structure of the web application, showing the main screens such as the dashboard, schedule, player list, and attendance tracking.

### Prototype
🚧 A clickable prototype can be designed using Budibase or Figma before connecting to the backend.

### Domain Design

•	Team (id, name, coach_id)
•	Player (id, name, team_id, email)
•	Event (id, date, type, team_id)
•	Attendance (id, player_id, event_id, status)

### Business Logic
Based on UC-4, attendance tracking follows these rules:
•	A player can only confirm attendance once per event.
•	If the event is canceled, all attendance records are marked as void.

### Path: /api/attendance/{event_id}
Method: POST
Body:
{
  "player_id": 123,
  "status": "present"
}

### Implementation
Backend Technology
This web application is built using Spring Boot and the following dependencies:
•	Spring Boot (Main framework)
•	Spring Data JPA (Database access)
•	Spring Security (Authentication & Authorization)
•	H2 Database (Demo mode) / MySQL (Production)

### Database Example (MySQL)
CREATE TABLE players (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    team_id INT
);

### Swagger API Documentation Dependency
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>

### Frontend Technology
•	React (UI Framework)
•	React Router (Navigation)
•	Axios (API Calls)

### Views and API Usage
View	API Endpoint	Method
Team Overview	/api/teams	GET
Player Management	/api/players	GET
Training Schedule	/api/events	GET
Attendance Update	/api/attendance/{event_id}	POST

### Execution
1.	Clone this repository.
2.	Start the backend with mvn spring-boot:run.
3.	Start the frontend with npm start.
4.	Access the web application at http://localhost:3000.

### Deployment to PaaS
1.	Use Render or Heroku for backend hosting.
2.	Use Vercel or Netlify for frontend hosting.
3.	Set up a PostgreSQL/MySQL database in production.

## Project Management
Roles
•	Backend Developer: [Name]
•	Frontend Developer: [Name]
•	Database Engineer: [Name]

### Milestones
1.	Analysis: Define use cases and user stories.
2.	Prototype Design: Wireframe and UI layout.
3.	Backend Development: Implement API and database.
4.	Frontend Development: Connect UI with API.
5.	Security: Implement authentication and authorization.
6.	Testing & Optimization: Unit and integration tests.
7.	Deployment: Host the application online.


## Maintainer
Anna Geiser
Armir Lecaj
Ray Pinzon
Sharbel Yakoub

## License
Apache License, Version 2.0
![image](https://github.com/user-attachments/assets/31e71217-889a-4add-90d8-34c7e8f34e1d)
