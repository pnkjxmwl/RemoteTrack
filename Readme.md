# RemoteTrack
This is the backend for RemoteTrack, a personal job application tracking system.
The idea is simple:
keep job applications organized, searchable, and easy to work with.

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- PostgreSQL (Supabase)
- Maven
- REST APIs
- UUID-based identifiers

## How it works?
* Stores job applications in a structured way

* Exposes clean REST APIs for frontend use

* Supports create, read, update, and delete operations

* Allows filtering by status, keyword, and date range

* Uses UUIDs for safe public identifiers

* Keeps entities internal using DTO-based responses

* Follows a layered structure (controller → service → repository)

* Manages createdAt and updatedAt automatically

* Returns consistent and predictable error responses

