# Project Deployment Guide

This guide provides step-by-step instructions to deploy the project on a server.

---

## Prerequisites

- **Java 17+** (JDK installed)
- **Maven 3.8+**
- **MySQL 8+** (or your preferred database)
- **Git**
---

## 1. Clone the Repository

```bash
git clone https://github.com/Cybeear/SimpleCalendarApi.git
cd SimpleCalendarApi
```
## 2. Configure Environment Variables

Create a .env file in the project root or configure system variables:
```ini 
# Database Configuration
DB_URL=jdbc:postgresql://localhost:5432/your_db
DB_USER=postgres
DB_PASSWORD=your_password

# Server Port
SERVER_PORT=8080
```

## 3. Build the Project
Using Maven:
```bash
mvn clean install
```

## 4. Set Up the Database
Install PostgreSQL and create a database:

```sql
Copy
CREATE DATABASE your_db;
CREATE USER your_user WITH PASSWORD 'your_password';
GRANT ALL PRIVILEGES ON DATABASE your_db TO your_user;
Run migrations (if using Flyway/Liquibase):
```
```bash
Copy
mvn flyway:migrate 
```

## 5. Deploy the Application
Run as a JAR
```bash
java -jar target/SimpleCalendarApi-0.0.1-SNAPSHOT.jar
```
