# 🚗 Car Rental Project

A backend REST API for a **Car Rental Management System** built using **Java Spring Boot**. The project provides secure authentication, role-based authorization, and APIs for managing users, cars, and bookings.

> **Note:** This repository currently contains only the backend implementation. The frontend is under development.

---

## ✨ Features

### 👤 User Features

- User Registration
- Secure Login (JWT Authentication)
- Browse Available Cars
- Search Cars
- View Car Details
- Book a Car
- View Booking History
- Update Profile
- Logout

### 👨‍💼 Admin Features

- Admin Login
- Manage Cars (Add, Update, Delete)
- Manage Users
- Manage Bookings
- Approve / Reject Bookings

---

## 🛠 Tech Stack

### Backend

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Maven

### Database

- MySQL

### API Testing

- Postman

### Tools

- IntelliJ IDEA
- Git
- GitHub

---

## 📂 Project Structure

```
Car_Rental_Project
│
├── src
│   ├── main
│   │   ├── controller
│   │   ├── service
│   │   ├── repository
│   │   ├── model
│   │   ├── dto
│   │   ├── config
│   │   ├── security
│   │   ├── exception
│   │   └── CarRentalApplication.java
│   │
│   └── resources
│       └── application.properties
│
├── pom.xml
└── README.md
```

---

## 🔐 Security

- JWT Authentication
- Spring Security
- Role-Based Authorization
- Password Encryption
- Protected REST APIs

---

## 📦 REST API Modules

### Authentication

- Register User
- Login

### Cars

- Get All Cars
- Get Car by ID
- Add Car
- Update Car
- Delete Car

### Bookings

- Book a Car
- Cancel Booking
- View User Bookings

### Users

- View Profile
- Update Profile

---

## 🗄 Database

**MySQL**

### Tables

- Users
- Roles
- Cars
- Bookings

---

## ⚙️ Backend Setup

### 1. Clone the Repository

```bash
git clone https://github.com/niik928/Car_Rental_Project.git
```

```bash
cd Car_Rental_Project
```

### 2. Configure MySQL Database

Update the `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/car_rental
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application will start at:

```
http://localhost:8080
```

---

## 📮 API Testing

All REST APIs were tested using **Postman**.

### Sample Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | User login |
| GET | `/api/cars` | Get all cars |
| GET | `/api/cars/{id}` | Get car by ID |
| POST | `/api/bookings` | Book a car |
| PUT | `/api/cars/{id}` | Update car |
| DELETE | `/api/cars/{id}` | Delete car |

---

