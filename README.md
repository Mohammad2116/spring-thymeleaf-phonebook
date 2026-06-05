# Phonebook Application

A full-stack Spring Boot web application built with Spring MVC, Thymeleaf, Spring Security, and JPA/Hibernate.

This project demonstrates a layered backend architecture, secure authentication, server-side rendering, form validation, reusable UI fragments, pagination, and modal-based CRUD operations.

---

## Features

- User registration and authentication
- Spring Security integration
- Remember-me authentication
- Contact management (Create, Update, Delete)
- Search functionality
- Pagination and sorting
- Server-side validation
- Thymeleaf template fragments
- Modal-based forms
- DTO/Form mapping architecture
- Responsive Bootstrap UI

---

## Tech Stack

### Backend

- Java
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate

### Frontend

- Thymeleaf
- Bootstrap
- JavaScript

### Database

- PostgreSQL

### API Documentation

- OpenAPI 3
- Swagger UI

### Build Tool

- Maven

---

## Project Structure

```text
src/main/java/ir/aspireapps/phonebook
│
├── config
├── controller
├── dto
├── form
├── mapper
├── model
├── repo
├── security
├── service vv
└── error
```

## Architecture Highlights

Layered Architecture

The application follows a layered architecture to separate concerns between:

    Controllers
    Services
    Repositories
    DTOs / Forms
    Domain Models

DTO & Form Separation

Dedicated DTO and form classes are used to avoid exposing entities directly to the presentation layer.
Reusable Thymeleaf Fragments

The UI uses reusable fragments for:

    Navigation bars
    Footer
    Modals
    Shared components

Validation

Server-side validation is implemented using Jakarta Validation annotations and Spring BindingResult.
Security

Authentication is implemented using:

    Spring Security
    Custom UserDetailsService
    Password encoding
    Remember-me functionality

Clone the Repository
git clone https://github.com/Mohammad2116/spring-thymeleaf-phonebook.git
cd phonebook

Configure Database

Update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/phonebook

spring.datasource.username=YOUR_USERNAME

spring.datasource.password=YOUR_PASSWORD

Run the Application
mvn spring-boot:run

Application will start on:
http://localhost:8080

Future Improvements

    REST API version
    Profile pictures
    Contact categories/tags
    Docker support
    Unit and integration tests
    Role-based authorization

Learning Goals

This project was built to practice and demonstrate:

    Spring Boot application architecture
    Authentication and authorization
    Thymeleaf server-side rendering
    Form validation
    MVC design pattern
    Repository/service layering
    UI composition with fragments and modals
