import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User implements CourseViewer {
    private DataManager dataManager;
    private List<Course> courseList;

    public Admin(String email, String password, String name, DataManager dataManager) {
        super(email, password, name);
        this.dataManager = dataManager;
        this.courseList = dataManager.getCourseList();
    }

    //Method to view courses
    public void viewCourses()
    {
        System.out.println("\n");
        List<Course> courseList = DataManager.getInstance().getCourseList();

        if(courseList.isEmpty()){
            System.out.println("No courses available");
        }

        for (Course course : courseList)
        {
            System.out.println("Course Title: "+ course.getTitle());
            System.out.println("Course Code: "+ course.getCode());
            System.out.println("Professor Name: "+ course.getProfessorName());
            System.out.printf("Credits: %d\n", course.getCredits());
            System.out.printf("Max Enrolments: %d\n", course.getMaxEnrolments());
            System.out.println("Prerequisites: ");
            for (Course preq : course.getPrerequisites())
            {
                System.out.println(preq.getCode());
            }
            System.out.println();
                
            System.out.println("Weekly Schedule: ");
            for (Schedule schedule : course.getWeeklySchedule())
            {
                System.out.println(schedule);
            }
            System.out.println();
        }
    }


    // Method to add a new course
    public void addCourse(String title, String code, String professorName, int semester, int credits, List<Course> prerequisites) 
    {
        for (Course c : courseList) 
        {
            if (c.getCode().equals(code)) 
            {
                System.out.println("Course with code " + code + " already exists.");
                return;
            }
        }
        Course course = new Course(title, code, professorName, semester, credits, prerequisites);
        dataManager.addCourse(course);
        System.out.println("Course " + title + " added successfully.");
    }

    // Method to remove an existing course by code
    public void removeCourse(String code) {
        Course courseToRemove = null;
        for (Course course : courseList) {
            if (course.getCode().equals(code)) {
                courseToRemove = course;
                break;
            }
        }
        if (courseToRemove != null) {
            courseList.remove(courseToRemove);
            System.out.println("Course with code " + code + " removed successfully.");
        } else {
            System.out.println("No course found with code " + code);
        }
    }

    // Method to view all students
    public void viewAllStudents() {
        List<Student> students = dataManager.getAllUsers(Student.class);
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                System.out.println("Student Name: " + student.getName() + ", Email: " + student.getEmail() + ", Roll No: " + student.getRollNo());
            }
        }
    }
    // Method to view all professors
    public void viewAllProfessors() {
        List<Professor> professors = dataManager.getAllUsers(Professor.class);
        if (professors.isEmpty()) {
            System.out.println("No professors found.");
        } else {
            for (Professor professor : professors) {
                System.out.println("Professor Name: " + professor.getName() + ", Email: " + professor.getEmail() + ", Assigned Course: " + professor.getCourse().getCode());
            }
        }
    }
    //Method to view student grades
    public void viewStudentGrades(String email) 
    {
        Student student = (Student)dataManager.getUserByEmail(email);
        if (student != null) 
        {
            System.out.println("Student Name: " + student.getName() + ", Email: " + student.getEmail() + ", Roll Number: " + String.valueOf(student.getRollNo()));
            System.out.println("Grades: ");
            student.viewGrades();
        } 
        else 
        {
            System.out.println("Student not found.");
        }
    }

    //Method to track student progress
    public void trackStudentProgress(String email)
    {
        Student student = (Student)dataManager.getUserByEmail(email);
        if (student != null)
        {
            System.out.println("Student Name: " + student.getName() + ", Email: " + student.getEmail() + ", Roll Number: " + String.valueOf(student.getRollNo()));
            System.out.println("Progress: ");
            student.trackProgress();
        }
        else
        {
            System.out.println("Student not found.");
        }
    }

    //Method to assign a course to a professor
    public void assignCourseToProfessor(String email, String courseCode)
    {
        Professor professor = (Professor)dataManager.getUserByEmail(email);
        if (professor != null)
        {
            Course course = null;
            for (Course c : courseList){
                if (c.getCode().equals(courseCode)){
                    course = c;
                    break;
                }
            }
            if (course != null)
            {
                professor.setCourse(course);
                System.out.println("Course assigned to Professor " + professor.getName());
            }
            else
            {
                System.out.println("Course not found.");
            }
        }
    }

    //Method to view all complaints
    public void viewAllComplaints()
    {   
        List<Student> students = dataManager.getAllUsers(Student.class);
        for (Student s : students)
        {
            System.out.println("Student Name: " + s.getName() + ", Email:  " + s.getEmail());
            System.out.println("Complaints: ");
            for (Complaint c : s.getComplaints())
            {
                System.out.println(c);
            }
        }
    }

    // Method to handle complaints
    public void handleComplaint(String email) 
    {
        Student student = (Student)dataManager.getUserByEmail(email);
        if (student != null) 
        {
            System.out.println("Complaint received from " + student.getName());
            List<Complaint> complaints = student.getComplaints();
            for (Complaint c : complaints) 
            {
                System.out.println("Complaint: " + c.getDescription());
                System.out.println("Current Status: " + c.getStatus());
                Scanner scanner = new Scanner(System.in);
                System.out.println("Update Status('Pending' or 'Resolved'): ");
                String status = scanner.nextLine();
                c.setStatus(status);
            }

        } 
        else 
        {
            System.out.println("Student not found.");
        }
            
    }
}
