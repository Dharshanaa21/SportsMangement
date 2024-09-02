# Sports Event Management System

## Objective
Develop a menu-based console application to assess your proficiency in Core Java, MySQL, and JDBC. The application simulates a sports event management system, allowing users to manage athletes, events, and results.

## Functionalities
### 1. Athlete Management
- Add new athletes
- View athlete details
- Update athlete information
- Remove athletes

### 2. Event Management
- Create new events
- View event details
- Update event information
- Remove events

### 3. Result Management
- Record results for events
- View result details
- Update result information
- Remove results

## Database Schema
### Athlete Table
- `athlete_id` (Primary Key)
- `name`
- `country`
- `age`
- `sport`

### Event Table
- `event_id` (Primary Key)
- `name`
- `date`
- `venue`
- `type` (e.g., track, field, team sport)

### Result Table
- `result_id` (Primary Key)
- `event_id` (Foreign Key references Event Table)
- `athlete_id` (Foreign Key references Athlete Table)
- `result`
- `result_date`


## Setup Instructions

### 1. Prerequisites
- Install MySQL on your machine.
- Ensure that Java Development Kit (JDK) is installed.

### 2. Setup MySQL Database
- Create a MySQL database named `sports_management`.
- Run the provided `database.sql` script to create the necessary tables (`Athlete`, `Event`, `Result`).
- Update the `DatabaseConnection` class in the source code with your MySQL credentials.

### 3. Run the Application
- Open the project in your preferred Java IDE.
- Compile and run the `SportsManagementSystem` class.
- Interact with the application using the menu-based console interface.

## Usage Instructions
- Athlete Management: Choose the respective option from the main menu to add, view, update, or remove athletes.
- Event Management:Choose the respective option from the main menu to create, view, update, or remove events.
- Result Management: Choose the respective option from the main menu to record, view, update, or remove results.

## Error Handling
The application handles exceptions effectively, providing user-friendly error messages and ensuring the system remains stable during execution.

## Code Structure
```bash
/src
  /sports
    ├── Athlete.java                # Manages athlete-related operations
    ├── Event.java                  # Manages event-related operations
    ├── Result.java                 # Manages result-related operations
    ├── SportsManagementSystem.java # Main class to run the application
    └── DatabaseConnection.java     # Manages database connections
