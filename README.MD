Trinity Manager - Core Service 🥋
The Trinity Core Service is the central engine of the Trinity Academy ecosystem. It manages Martial Arts academies (specifically Jiu-Jitsu), handling the hierarchy between main academies and branches, student progression (belts and degrees), and the attendance check-in system.

🚀 What it does
Academy & Branch Management: Registers academic entities and their physical units.

Student & Rank Tracking: Manages student records, belt ranks, and degree progression.

Attendance System: Handles student check-ins via administrator actions or QR Code integration.

Data Synchronization: Automatically receives and updates academy data sent from the Auth service.

⚙️ How to Set Up
1. Prerequisites
   JDK 17

Maven

Docker (to run the database and message broker)

2. Infrastructure
   This service requires PostgreSQL and RabbitMQ to be running (usually via the centralized Trinity-System-Infra repository).

# From your infra folder:
docker-compose up -d
3. Running the Service
   Ensure a database named trinity-core-service exists.

From the project root, run:

mvn spring-boot:run
The service starts on port 8081.

4. API Documentation
   Once running, you can view all available endpoints and test them via Swagger:
   URL: http://localhost:8081/swagger-ui.html

Developed for Trinity Academy.