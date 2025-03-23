# Team Manager
Group project for the module 'Internet Technology'. It is a minimal web-based team management application for sports teams to organize their association, teams, players, games, etc.

## Contents
... To be done

## Analysis

### Scenario
TeamManager is a team management tool that allows coaches and players to organize training sessions, matches, and team communication efficiently.

### User Stories
1.	As an Admin, I want to be able to modify all existing teams.
2.	As an Admin, I want to be able to create teams.
3.	As a User (Coach), I want to create teams, update/modify my teams and invite players to my teams.
4.	As a User (Coach), I want to schedule training sessions and matches for my team.
5.	As a User (Coach), I want review the available and unavailable players for the scheduled training sessions and matches of my team.
6.	As a User (Coach), I want to send notifications to players about scheduled trainings and matches of my team.
7.	As a User (Player), I want to join teams via an invite from the creator of the team within TeamManager.
8.	As a User (Player), I want to receive notifications about future scheduled trainings and matches of my team.
9.	As a User (Player), I want to mark myself as available or unavailable for future scheduled trainings and matches of my team.

### Use Cases
![XP7DJiCm383lUGfh9pWCYV-V0zgq6r8HsiO3U8dNMUG7fUvGXNXt5gKzB1Jj5iMVxS_sh16CqZghB6q5943aUYTWE98M3bufr18Yp8rZtxyN0VMkVxzPx2WgbN3qjIMOh0aTLEYn9aTUODKrGcu2tme0SY6OJk6i2eQt7IIyEOSdU5zCBmja4Gc5Zf0hbPZi08d9dJ83sEH83rydzb_a](https://github.com/user-attachments/assets/bd391662-5c61-49e9-8c81-1ac4e1e1cc40)

1.  UC-1 [Modify Teams]: Admin can update or delete all existing teams.
2.  UC-2 [Create Teams]: Admin can create new teams. 
3.  UC-3 [Manage Teams as Coach]: Coach can create teams, update teams, and invite players. 
4.  UC-4 [Schedule Training and Matches]: Coach can create training sessions and matches for their team. 
5.  UC-5 [Review Player Availability]: Coach can view available and unavailable players for scheduled events. 
6.  UC-6 [Send Notifications]: Coach can send notifications to players about upcoming trainings and matches. 
7.  UC-7 [Join Teams]: Players can accept invitations from a coach to join a team. 
8.  UC-8 [Receive Notifications]: Players receive notifications about future scheduled events. 
9.  UC-9 [Mark Availability]: Players can mark themselves as available or unavailable for scheduled training sessions and matches.

## Design

### Wireframes
![Bildschirmfoto 2025-03-23 um 23 03 03](https://github.com/user-attachments/assets/f11ce79e-6fd2-43b0-a6f5-270f06bc405a)
![Bildschirmfoto 2025-03-23 um 23 03 31](https://github.com/user-attachments/assets/6df1cd3f-785f-466b-a51f-de18bbd48f84)
![Bildschirmfoto 2025-03-23 um 23 03 42](https://github.com/user-attachments/assets/5b7a7e89-1813-4b74-bb03-8d2cbdecc44c)
![Bildschirmfoto 2025-03-23 um 23 03 51](https://github.com/user-attachments/assets/7616a4d1-c97a-47e0-9ecb-6aa155742402)

### Prototype
🚧 A clickable prototype can be designed using Budibase or Figma before connecting to the backend. To be done

### Domain Model
![6d06fa22-0919-46d3-9cb8-63fd5a073ae2](https://github.com/user-attachments/assets/9038eea1-da37-45c1-be5f-c59f54a04ac8)

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
•	Backend Developer: Ray Pinzon, Sharbel Yakoub
•	Frontend Developer: Anna Geiser
•	Database Engineer: Armir Lecaj

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
