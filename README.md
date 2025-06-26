Hospital Management System (HMS) RESTful API
Project Overview
This project implements a core set of RESTful APIs for a Hospital Management System (HMS). The goal is to automate and streamline the key operations of a hospital, including patient management, appointment scheduling, and drug inventory control. This backend API serves as the foundation for a future full-stack application, reducing manual paperwork, minimizing human errors, and improving overall healthcare service quality.

This repository focuses specifically on the backend API implementation, adhering to RESTful principles.

Key Features (Implemented Modules)
The current implementation provides robust API functionalities for the following core modules:

Patient Management:

Register and manage patient demographic details.

Retrieve patient information by unique ID or list all patients.

Update existing patient records.

Appointment Management (Planned/Expandable):

(Currently placeholder, but API structure is designed) Schedule, view, and update doctor appointments.

Check doctor availability.

Send appointment confirmations.

Drug and Inventory Management (Planned/Expandable):

(Currently placeholder, but API structure is designed) Track drug availability and stock levels.

Update drug details and pricing.

Issue drugs to patients.

(Note: While the current code primarily demonstrates Patient Management, the API design covers these other modules for future expansion as per the project proposal.)

Technologies Used
Backend Framework: Spring Boot (Java)

Programming Language: Java 17+

Database: MySQL

ORM / Data Access: Spring Data JPA (Hibernate as JPA Provider)

Build Tool: Apache Maven

Utility Library: Lombok (for boilerplate code reduction - getters, setters, constructors)

API Testing: Postman (or similar REST client)

Database Management: phpMyAdmin (for easy database and user management)

API Endpoints
Below is a summary of the implemented Patient Management API endpoints. Similar patterns will be applied to other modules (Doctor, Appointment, Drug, Staff, Administration) as the project evolves.

Patient Management API
Base URL: /api/patients

Endpoint URL

HTTP Method

Description

Request Body (Example)

Response Body (Example)

/api/patients

POST

Register a new patient

{"name":"John Doe", "phoneNo":"0771234567", "address":"123 Main St", "age":30, "sex":"Male"}

{"id":1, "customId":"PS/2025/1000", "name":"John Doe", ...}

/api/patients

GET

Retrieve all patients

(None)

[{...patient1}, {...patient2}]

/api/patients/{customId}

GET

Retrieve patient by custom ID

(None)

{"id":1, "customId":"PS/2025/1000", "name":"John Doe", ...}

/api/patients/{customId}

PUT

Update patient details

{"address":"456 Oak Ave", "phoneNo":"0719876543"}

{"id":1, "customId":"PS/2025/1000", "address":"456 Oak Ave", ...}

/api/patients/{customId}

DELETE

Delete a patient

(None)

(No Content - Status 204)

Getting Started
Follow these steps to set up and run the HMS RESTful API on your local machine.

Prerequisites
Java Development Kit (JDK) 17+: Download JDK

Apache Maven: Download Maven

MySQL Server: Download MySQL Community Server or use a stack like XAMPP/WAMP/LAMP which includes MySQL.

phpMyAdmin: (Optional, but recommended for easy database management). Usually comes with XAMPP/WAMP/LAMP.

IDE (Integrated Development Environment): IntelliJ IDEA, VS Code, or Eclipse are recommended. Ensure Lombok plugin is installed in your IDE if you use it.

Database Setup
Start MySQL Server: Ensure your MySQL server instance is running. If you are using XAMPP/WAMP/LAMP, start the MySQL module from its control panel.

Create Database:

Using phpMyAdmin:

Open your browser and navigate to http://localhost/phpmyadmin.

Login with your MySQL credentials (default root and empty/no password for XAMPP/WAMP).

Click on the "Databases" tab, type hospitaldb in the "Create database" field, choose utf8mb4_unicode_ci for collation, and click "Create".

Using MySQL Command Line Client:

Open your terminal/command prompt and run mysql -u your_mysql_username -p (e.g., mysql -u root -p).

Enter your MySQL password when prompted.

Execute the command: CREATE DATABASE hospitaldb;

Create User (Recommended for Security):

While root can be used for local development, it's good practice to create a dedicated user.

Using phpMyAdmin: Go to "User accounts" tab, click "Add user account", provide a username (e.g., hmsuser) and a strong password, and grant all privileges on the hospitaldb database.

Using MySQL Command Line Client:

CREATE USER 'hmsuser'@'localhost' IDENTIFIED BY 'your_strong_password';
GRANT ALL PRIVILEGES ON hospitaldb.* TO 'hmsuser'@'localhost';
FLUSH PRIVILEGES;

Project Setup
Clone the Repository:

git clone <repository_url_here>
cd hospital-management-system

(Replace <repository_url_here> with your actual GitHub repository URL)

Configure Database Credentials:

Open the src/main/resources/application.properties file.

Update the spring.datasource.username and spring.datasource.password with the credentials of the MySQL user you created (e.g., hmsuser and your_strong_password). If you are using root with no password, set spring.datasource.password= (empty).

# src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospitaldb?useSSL=false&serverTimezone=UTC
spring.datasource.username=hmsuser  # Your MySQL username
spring.datasource.password=your_strong_password # Your MySQL password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect # Optional, can be removed
spring.jpa.open-in-view=false

server.port=8080

(Optional: Remove spring.jpa.properties.hibernate.dialect line to avoid a warning, as Hibernate can auto-detect it.)

Build and Run
Build the Project:

Open a terminal/command prompt in the project's root directory (where pom.xml is located).

Run the Maven command to build the project and download dependencies:

mvn clean install

Ensure the build is successful ([INFO] BUILD SUCCESS).

Run the Application:

From the same terminal/command prompt:

mvn spring-boot:run

Alternatively, if using an IDE (IntelliJ IDEA, VS Code), open the project and run the main method in HospitalManagementSystemApplication.java.

The application will start, and you should see logs indicating that Tomcat has started on port 8080 and that Hibernate has created the patients table in your hospitaldb database.

API Testing
Once the application is running, you can test the endpoints using a tool like Postman. Refer to the "API Endpoints" section above for example requests (URL, Method, Body, Headers).

Test URL: http://localhost:8080/api/patients

Future Enhancements
Implementing APIs for Doctor, Appointment, Drug, Staff, and Payment modules.

Adding Authentication and Authorization (e.g., JWT) for secure API access.

Developing a Frontend User Interface (e.g., using React, Angular, or Vue.js) to interact with this API.

Implementing more robust validation and error handling.

Adding logging and monitoring tools.

Deployment to a cloud platform (AWS, Azure, GCP).
