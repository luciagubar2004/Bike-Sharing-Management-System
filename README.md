# Bike-Sharing Management System

A robust backend system designed to manage a metropolitan bike-sharing network. This project focuses on **Object-Oriented Programming (OOP)**, modular architecture, and reliable data handling.

## Architecture & Design
The system was meticulously planned using UML standards to ensure a scalable and maintainable codebase:
- **Class Diagram:** Defines the core hierarchy, including specialized entities like `BiciElectrica` and `BiciMecanica`.
- **Sequence Diagrams:** Documented critical workflows such as bike unlocking, station registration, and real-time bike localization.

## Technical Implementation (Java)
- **Modular Controllers:** Dedicated logic for different user roles via `ControladorCiclista` and `ControladorAdministrador`.
- **Infrastructure Managers:** Independent handling of stations and bike inventory through `GestorEstacion` and `GestorBicis`.
- **Custom Exception Handling:** Implementation of a robust error-catching layer, including `SaldoException`, `BiciException`, and `TrayectoException` to ensure system stability.

## Quality Assurance & Testing
To ensure the reliability of the business logic, the system was validated against a series of automated unit tests:
- **Test-Driven Validation:** The core logic successfully passes the `PruebasBici` suites (Iter 0, 1, and 2), covering registration, bike allocation, and user transactions.
- **Reliability:** These tests confirm that the backend handles both standard operations and edge cases correctly.

## Academic Context
Developed as part of my **Telecommunication Engineering** degree at the **University of Valladolid**. While the core logic and architecture were my primary focus, the testing suites and part of the code were provided to align with professional industry standards and requirements.
