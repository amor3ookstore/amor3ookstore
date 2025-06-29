# Amor 3ookstore Backend

This is the Spring Boot backend for the Amor 3ookstore online bookstore project.

## 🚀 Getting Started

### 1. Prerequisites

- Java 17+
- Maven 3.6+
- MySQL running locally

### 2. MySQL Setup

1. Open MySQL Workbench or CLI.
2. Create the database:
   ```
   CREATE DATABASE `Local instance MySQL80` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
3. Ensure your `application.yml` (already included) matches:
   - host: `localhost`
   - port: `3306`
   - db: `Local instance MySQL80`
   - user: `root`
   - password: `Kristel123`

### 3. Build & Run

```bash
# In project root
mvn clean install
mvn spring-boot:run
```

App runs at [http://localhost:8080](http://localhost:8080)

### 4. IntelliJ IDEA

- Open IntelliJ IDEA
- Select "Open" and choose the root folder
- Wait for Maven to import dependencies
- Run `Amor3ookstoreApplication.java`

---

**Contact:** amor3ookstore@example.com
