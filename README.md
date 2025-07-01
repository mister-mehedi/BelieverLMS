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

## ⚙️ Configuration

```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/lms_test
spring.datasource.username=root
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=100MB
```

---

🚀 How to Run
Follow these steps to get started:
- Clone the Repository
```
git clone https://github.com/your-username/BelieverLMS.git
cd BelieverLMS
```

- Configure the Database
  - Create a new schema named lms_test in MySQL.
  - Update credentials in application.properties.
- Build and Run
```
./mvnw spring-boot:run
```
- Open in Browser
  - Navigate to: http://localhost:8080
  - Log in via Google OAuth2
  - On first login, complete your profile (Student or Teacher

---


## 📌 Key Endpoints

| Method | Path                            | Access         | Description                             |
|--------|----------------------------------|----------------|-----------------------------------------|
| GET    | `/login`                         | Public         | Login with OAuth2 (Google)              |
| GET    | `/home`                          | Authenticated  | Redirect to dashboard based on role     |
| GET    | `/studentCohort`                | Student        | View all enrolled cohorts               |
| GET    | `/cohort/{id}`                  | Teacher        | View/manage a specific cohort           |
| POST   | `/post/{cohortID}`              | Teacher        | Add post with attached files            |
| GET    | `/quizStudent/{id}`             | Student        | Take a quiz                             |
| POST   | `/processQuizStudent/{id}`      | Student        | Submit answers for grading              |
| GET    | `/viewMarkSheetStd`             | Student        | View marksheet and quiz results         |
| GET    | `/createCohort`                 | Teacher        | Create a new cohort                     |
| GET    | `/createQuiz/{cohortID}`        | Teacher        | Create a quiz for a cohort              |
| POST   | `/studentData`, `/teacherData`  | First-time User| Save role-based profile info            |



