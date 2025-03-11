# Student Management System

## Overview
The Student Management System is a Java-based application that allows users to manage student records, including adding student details and retrieving student information based on roll numbers. It uses MySQL as the database to store and manage student data.

## Features
- Create a student database table if it does not exist.
- Add student details including roll number, name, age, grade, address, CGPA, and attendance percentage.
- Retrieve student details using roll numbers.
- Display a warning if the student's attendance is below the minimum required percentage (75%).

## Technologies Used
- Java
- MySQL
- JDBC (Java Database Connectivity)

## Database Setup
1. Ensure you have MySQL installed and running.
2. Create a database named `student_db` using the following SQL command:
   ```sql
   CREATE DATABASE student_db;
   ```
3. Use the following SQL command to grant privileges (replace `your_password` accordingly):
   ```sql
   CREATE USER 'root'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON student_db.* TO 'root'@'localhost';
   ```
4. Update the database connection URL, username, and password in the Java code if necessary:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/student_db";
   private static final String USER = "root";
   private static final String PASSWORD = "root";
   ```

## How to Run the Application
1. Compile the Java program:
   ```sh
   javac StudentManagementSystem.java
   ```
2. Run the program:
   ```sh
   java StudentManagementSystem
   ```
3. Follow the on-screen prompts to add or retrieve student records.

## Example Usage
### Adding a Student
```
Enter Roll No: 101
Enter Name: John Doe
Enter Age: 20
Enter Grade: A
Enter Address: 123 Main St
Enter CGPA (0.00 - 4.00): 3.8
Enter Attendance Percentage (0.00 - 100.00): 80.0
Student added successfully.
```

### Retrieving a Student
```
Enter Roll No to search: 101

Student Details:
Roll No: 101
Name: John Doe
Age: 20
Grade: A
Address: 123 Main St
CGPA: 3.8
Attendance: 80.0%
```

## Future Enhancements
- Implement student record deletion and updating.
- Add a GUI interface using JavaFX or Swing.
- Enhance error handling and validation.

## Author
D Sai Charan

