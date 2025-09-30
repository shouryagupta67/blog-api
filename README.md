📝 Blog REST API
📌 Overview

A Spring Boot Blog API with JWT authentication where users can:

Register & Login

Create, Read, Update, Delete (CRUD) blog posts

Add comments & like posts

Get structured error responses

⚙️ Tech Stack

Java 17+, Spring Boot, Spring Security (JWT)

Spring Data JPA, Hibernate, MySQL

Maven

🚀 Features

🔐 User Registration & Login (JWT based)

📝 CRUD Operations for Posts

💬 Comment on Posts

👍 Like/Unlike Posts

⚠️ Custom Exception Handling (UserNotFound, PostNotFound, CommentNotFound)

✅ Secure Layered Architecture

🔑 API Endpoints
Auth

POST /auth/register → Register

POST /auth/login → Login (JWT)

Posts

GET /posts → All posts

GET /posts/{id} → Post by ID

POST /posts → Create (JWT required)

PUT /posts/{id} → Update (JWT required)

DELETE /posts/{id} → Delete (JWT required)

Comments

POST /posts/{postId}/comments → Add comment

GET /posts/{postId}/comments → Get comments

Likes

POST /posts/{postId}/like → Like a post

DELETE /posts/{postId}/like → Unlike a post

🛠️ Setup

Clone repo & configure MySQL in application.properties

Run:

mvn spring-boot:run


API runs at http://localhost:8080

📬 Contact

👤 Shourya Gupta • Shouryasahu08@gmail.com
