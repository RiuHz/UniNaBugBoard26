
# UniNaBugBoard

A bug tracking and management system developed as part of a university software engineering project.

## Disclaimer

This repository contains the client-side application and a subset of the server-side components developed for the UniNaBugBoard project. Additional services and infrastructure required for deployment are not included, therefore, the project cannot be executed as a standalone system.

## About

UniNaBugBoard26 is a bug management system developed to track, manage, and resolve issues within a software project. This project was created as part of a university assignment for simple and structured bug management and monitoring.

This project was developed to demonstrate:

- Object-Oriented Design principles
- Layered Software Architecture
- Client-Server application development
- RESTful API design and integration
- Spring Boot backend development
- Web application development with Dart
- Software maintenance and bug tracking workflows

## Features

The system allows users to:

- Report bugs with a description and a priority.
- View reported bugs in a list.
- Assign a status to the bugs to track their progress (e.g., "To Do", "In Progress", "Resolved").
- Modify or delete reported bugs if necessary.
## Tech Stack

**Client (Dart)**

- Object-oriented design
- REST API integration
- Responsive user interface
- Web-based graphical user interface (GUI)

**Server (Java)**

- Spring Boot
- RESTful Web Services
- Layered Architecture
- Dependency Injection
- Data Persistence with JPA/Hibernate
- Exception Handling and Validation

## Architecture

The project follows a layered architecture:

- **Presentation Layer**: Web-based graphical user interface developed in Dart.
- **Controller Layer**: Handles incoming HTTP requests and exposes REST endpoints.
- **Service Layer**: Contains the business logic and application rules.
- **Persistence Layer**: Manages database operations through repositories and entities.
- **Database Layer**: Stores bug reports and related information.

This separation of concerns improves maintainability, scalability, and testability.
## Repository Structure

**Server**
- `src/main/java/.../api` → REST controllers exposing the application's API endpoints
- `src/main/java/.../aws` → Integration with Amazon Web Services
- `src/main/java/.../exception` → Custom exceptions and error handling
- `src/main/java/.../model` → Domain models and data entities

## License

This project is licensed under the [MIT](https://choosealicense.com/licenses/mit/) License.


## Authors

- GitHub: [@RiuHz](https://www.github.com/RiuHz)
- GitHub: [@eman-giaquinto](https://github.com/eman-giaquinto)
