# 💡 BelieverLMS

BelieverLMS is a full-featured **Learning Management System** (LMS) built with Spring Boot. It provides dynamic role-based access for students and teachers, offering functionality like cohort management, quiz creation and evaluation, post sharing with file attachments, secure OAuth2 authentication, and more.

---

## 🚀 Features

### 👨‍🏫 Teacher Functionalities
- Create and manage **Cohorts** and **Sub-Cohorts**
- Share posts with **file attachments**
- Create **Quizzes**, add questions (MCQs or subjective), and assign to cohorts
- View student participation and **Marksheets**

### 👩‍🎓 Student Functionalities
- Enroll in assigned cohorts
- View cohort **materials, people**, and **groups**
- Take **interactive quizzes**
- View personal **marksheet and quiz history**

### 🔐 Authentication & Security
- **OAuth2 Login** (Google)
- Role-based navigation and dashboard redirection
- Secure session and profile setup per user

### 📁 File Uploading
- Upload and attach files to posts using Base64 encoding
- Large file support (up to 100MB)
- Files stored and retrieved from `UploadedFiles` directory

---

## 🛠️ Tech Stack

| Layer | Tech |
|-------|------|
| Backend | Spring Boot, Spring MVC |
| Database | MySQL (via Spring Data JPA) |
| Security | Spring Security, OAuth2 |
| View | Thymeleaf (via `.html` templates) |
| Tools | Lombok, Multipart File Handling |
| Deployment | Runs on port `8080` |

---


