🏫 School Management System
📌 Project Overview

The School Management System is a web-based application developed using Spring Boot (Java), MySQL, and HTML/CSS to manage and automate the academic and administrative activities of a school. The system provides a centralized platform to handle student records, teacher details, attendance, and academic information efficiently, reducing manual work and improving data accuracy.

🎯 Objectives

Automate school administration processes

Reduce paperwork and manual data handling

Maintain secure and structured school data

Provide role-based access to users

✨ Features

Student Management

Teacher Management

Attendance Management

Academic Information Handling

Role-Based Login (Admin / Teacher / Student)

Secure database storage using MySQL

🛠️ Technologies Used

Backend: Java, Spring Boot, Spring MVC

Frontend: HTML, CSS

Database: MySQL

Build Tool: Maven

IDE: VS Code / IntelliJ IDEA

Version Control: Git & GitHub

🗂️ Project Structure
school/
│
├── src/
│   ├── main/
│   │   ├── java/com/school/web/
│   │   │   ├── controller/
│   │   │   ├── models/
│   │   │   ├── repo/
│   │   │   └── SchoolApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       ├── application.properties
│   │       └── application-secret.properties
│   └── test/
│
├── pom.xml
├── mvnw
├── .gitignore
└── README.md

⚙️ How to Run the Project
✅ Prerequisites

Make sure you have the following installed:

Java JDK 17 or above

Maven

MySQL Server

VS Code or IntelliJ IDEA

🗄️ Step 1: Create MySQL Database

Open MySQL Command Line or MySQL Workbench

Create a database:

CREATE DATABASE school_db;

⚙️ Step 2: Configure Database

Open application.properties and update:

spring.datasource.url=jdbc:mysql://localhost:3306/school_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

▶️ Step 3: Run the Spring Boot Application
Option 1: Using Maven (Recommended)

Open terminal in project root:

mvn spring-boot:run

Option 2: Using IDE

Open SchoolApplication.java

Right-click → Run Java Application

🌐 Step 4: Access the Application

Open browser and go to:

http://localhost:8080

🔐 User Roles

Admin: Manages entire system

Teacher: Manages attendance and academic data

Student: Views academic information

📸 Screenshots

(Add screenshots of login page, dashboard, etc.)

👨‍💻 Author

Sanjay Anand M
Computer Science & Engineering

📄 License

This project is developed for academic and educational purposes.

