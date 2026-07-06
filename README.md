# Car_Rental_Project

Using Java Spring Boot 

#Features

## 👤 User Features

- User Registration
- Secure Login
- Browse Available Cars
- Search Cars
- View Car Details
- Book a Car
- View Booking History
- Update Profile
- Logout

## 👨‍💼 Admin Features
  
- Admin Login
- Dashboard
- Add New Cars
- Update Car Details
- Delete Cars
- Manage Users
- Manage Bookings
- Approve / Reject Bookings

# 🛠 Tech Stack
- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Maven


## Database

- MySQL

## API Testing

- Postman

## Tools

- IntelliJ IDEA 
- Git
- GitHub

# 📂 Project Structure

```
Car_Rental_Project
│
├── backend
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│   ├── dto
│   ├── config
│   ├── security
│   ├── exception
│   └── CarRentalApplication.java
│
└── README.md
```  

# 🔐 Security

- Spring Security
- JWT Authentication
- Role-Based Authorization
- Password Encryption
- Protected REST APIs

# 📦 REST APIs

## Authentication

- Register User
- Login

## Cars

- Get All Cars
- Get Car By ID
- Add Car
- Update Car
- Delete Car

## Booking

- Book Car
- Cancel Booking
- View User Bookings

## Users

- View Profile
- Update Profile

---

# 🗄 Database

**MySQL**

Tables:

- Users
- Roles
- Cars
- Bookings

---
## Backend Setup

Open the backend project in IntelliJ IDEA or Eclipse.

Configure the MySQL database in `application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/car_rental
spring.datasource.username=root
spring.datasource.password=your_password
```

Build the project:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

The backend will start at:

```
http://localhost:8080
```

# 📮 API Testing

All REST APIs were tested using **Postman**.

Examples:

- POST /api/auth/register
- POST /api/auth/login
- GET /api/cars
- POST /api/bookings
- PUT /api/cars/{id}
- DELETE /api/cars/{id}

---




