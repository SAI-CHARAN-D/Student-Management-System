import java.sql.*;
import java.util.Scanner;

public class StudentManagementSystem {
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final double MIN_ATTENDANCE = 75.0; // Minimum required attendance percentage
    
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    private static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS students ("
                + "roll_no INT PRIMARY KEY,"
                + "name VARCHAR(100) NOT NULL,"
                + "age INT,"
                + "grade VARCHAR(2),"
                + "address VARCHAR(200),"
                + "cgpa DECIMAL(4,2),"
                + "attendance DECIMAL(5,2))";
                
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
    
    private static void addStudent(int rollNo, String name, int age, String grade, String address, double cgpa, double attendance) {
        String sql = "INSERT INTO students (roll_no, name, age, grade, address, cgpa, attendance) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, rollNo);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setString(4, grade);
            pstmt.setString(5, address);
            pstmt.setDouble(6, cgpa);
            pstmt.setDouble(7, attendance);
            
            if (attendance < MIN_ATTENDANCE) {
                System.out.println("Warning: Student attendance is below minimum requirement of " + MIN_ATTENDANCE + "%");
            }
            
            pstmt.executeUpdate();
            System.out.println("Student added successfully");
            
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }
    
    private static void getStudentByRollNo(int rollNo) {
        String sql = "SELECT * FROM students WHERE roll_no = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, rollNo);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("\nStudent Details:");
                System.out.println("Roll No: " + rs.getInt("roll_no"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Grade: " + rs.getString("grade"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("CGPA: " + rs.getDouble("cgpa"));
                System.out.println("Attendance: " + rs.getDouble("attendance") + "%");
            } else {
                System.out.println("No student found with Roll No: " + rollNo);
            }
            
        } catch (SQLException e) {
            System.out.println("Error retrieving student: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            // Create table if not exists
            createTable();
            
            while (true) {
                System.out.println("\nStudent Management System");
                System.out.println("1. Add Student");
                System.out.println("2. Get Student Details");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        System.out.print("Enter Roll No: ");
                        int rollNo = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        
                        System.out.print("Enter Age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        System.out.print("Enter Grade: ");
                        String grade = scanner.nextLine();
                        
                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine();
                        
                        System.out.print("Enter CGPA (0.00 - 4.00): ");
                        double cgpa = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        
                        System.out.print("Enter Attendance Percentage (0.00 - 100.00): ");
                        double attendance = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        
                        addStudent(rollNo, name, age, grade, address, cgpa, attendance);
                        break;
                        
                    case 2:
                        System.out.print("Enter Roll No to search: ");
                        int searchRollNo = scanner.nextInt();
                        getStudentByRollNo(searchRollNo);
                        break;
                        
                    case 3:
                        System.out.println("Thank you for using Student Management System");
                        return;
                        
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            }
            
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}