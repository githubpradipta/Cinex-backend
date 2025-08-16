# 🎬 Cinex – Movie Ticket Booking API

A **Spring Boot REST API** where **Organizers** can create & manage Movies, and **Users** can browse, book, and cancel tickets for any movies.  
It uses **authentication, CRUD operations, role-based permissions, search, filtering, transactional booking logic**.

---

## 📌 Project Concept

- **Organizers** → Create, update, and manage movies.
- **Users** → Browse movies, book tickets, cancel bookings.
- **Admins** → Manage the application and access control.

---

## 📂 Core Features

###  1. Authentication & Roles
- **Sign Up** → Register as Organizer or User (default: User)
- **Login** → JWT-based authentication
- **Role-based Access Control**:
    - Organizer → Manage movies
    - Users → Browse & book movies
    - Admins → Access Control


---

###  2. Movie Management (Organizer only)
- **Create Movie**
- **Update movie**
- **Delete movie** (only if no active bookings)
- **Get movies** (sorting, filtering)
- **Get movies by ID**

---

###  3. Ticket Booking System (For Users)
- **Book Ticket**
    - Checks `availableSeats > 0`
    - Decrements `availableSeats` **atomically** to prevent overbooking
    - Creates bookingStatus with `status: True means CONFIRMED`
- **Cancel Booking**
    - Marks bookingStatus as `false means CANCELLED`
    - Restores `availableSeats`

---

###  4. Search & Filters
- Filter by:
    - **Category** (Scify, Horror, Funny, etc.)
    - **Location** (city/state)
    - **Date range**
    - **Price range**

---

### 5. Admin Controls
- View all users & movies
- Approve Organizer role
- Ban users

---

## 🛠 API Endpoints

| Method | Endpoint                | Role      | Description             |
|--------|-------------------------|-----------|-------------------------|
| POST   | `/auth/register`        | Public    | Register user           |
| POST   | `/auth/login`           | Public    | Login & get JWT         |
| GET    | `/events`               | Public    | List all events         |
| GET    | `/events/:id`           | Public    | View event details      |
| POST   | `/events`               | Organizer | Create new event        |
| PUT    | `/events/:id`           | Organizer | Update event            |
| DELETE | `/events/:id`           | Organizer | Delete event            |
| POST   | `/bookings`             | User      | Book ticket             |
| GET    | `/bookings/:id`         | User      | View my bookings        |
| PATCH  | `/bookings/:id/cancel`  | User      | Cancel booking          |
| GET    | `/admin/users`          | Admin     | View all users          |
| PATCH  | `/admin/users/:id/role` | Admin     | Change role             |

---

## ERD (Entity Relationship Diagram)

### **User**
- id (PK)
- name
- email
- password
- role (Admin / Organizer / User)
- createdAt
- updatedAt

### **Movie**
- id (PK)
- organizerId (FK → User.id)
- title
- description
- category
- location
- dateTime
- price
- totalSeats
- availableSeats
- imageUrl
- createdAt
- updatedAt

### **Booking**
- id (PK)
- eventId (FK → Event.id)
- userId (FK → User.id)
- seats
- status (CONFIRMED / CANCELLED)
- createdAt
- updatedAt


### **Relationships**
- **User (Organizer)** → Movie (1-to-many)
- **User (User)** → Booking (1-to-many)
- **Movie** → Booking (1-to-many)

---

## 🚀 Tech Stack
- **Backend**: Spring Boot, Spring Security, JWT
- **Database**: MySQL (configurable via `.env`)
- **ORM**: Hibernate, Spring Data JPA
- **Build Tool**: Maven
- **Dev Tools**: Lombok

---

## ⚙️ Setup & Run Locally

1. **Clone the repo**
   ```bash
   git clone https://github.com/yourusername/cinex-backend.git
   cd cinex-backend
   

2. **Create .env file in the root:**

    ```bash
    DB_URL=jdbc:postgresql://localhost:5432/cinex
    DB_USERNAME=your_user
    DB_PASSWORD=your_pass
    JWT_SECRET=your_jwt_secret


3. **Run the app**

    ```bash
    mvn spring-boot:run


4. ***Test the APIs → http://localhost:8080***

## 📌 Future Enhancements

Payment gateway integration

Event recommendations

Rating & reviews system

## 🤝 Contributing

Pull requests are welcome! If you’d like to contribute, check out the issues page.


