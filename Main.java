import java.util.List;
import java.util.Scanner;

public class Main 
{
    
    private static DataManager dataManager = DataManager.getInstance();
    private static LoginManager loginManager = new LoginManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) 
    {
        boolean exit = false;

        seedData();
        testTA();

        while (!exit) 
        {
            System.out.println("\nWelcome to IIIT Delhi Course Registration System");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) 
            {
                case 1:
                    loginUser();
                    break;
                case 2:
                    signUpUser();
                    break;
                case 3:
                    exit = true;
                    System.out.println("\nExiting.");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private static void loginUser() 
    {
        
        System.out.println("\nLog In");
        System.out.println("Enter your email: ");

        scanner.nextLine();
        String email = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        User user = loginManager.login(email, password);
        if (user != null) 
        {
            System.out.println("\nLogin successful!");

            // Determine user type and show respective menu
            if (user instanceof Student)
            {
                Student student = (Student) user;
                if (student.isTa())
                {
                    taMenu(student.getTa());
                }
                else
                {
                    studentMenu(student);
                }
            }
            else if (user instanceof Professor) 
            {
                professorMenu((Professor) user);
            } 
            else if (user instanceof Admin) 
            {
                adminMenu((Admin) user);
            } 
            else 
            {
                System.out.println("Unrecognized role.");
            }
        } 
        else 
        {
            System.out.println("Login failed. Please check your credentials.");
        }
    }

    private static void signUpUser() 
    {
        System.out.println("\nSign Up");
        System.out.println("1. Sign up as Student");
        System.out.println("2. Sign up as Professor");
        System.out.println("3. Sign up as Admin");
        System.out.print("\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
    
        switch (choice) {
            case 1:
                Student student = new Student(email, password, name, 0);
                dataManager.addUser(student);
                loginManager.signUp(student);
                System.out.println("Student sign up successful!");
                break;
    
            case 2:
                Professor professor = new Professor(email, password, name, null);
                dataManager.addUser(professor);
                loginManager.signUp(professor);
                System.out.println("Professor sign up successful!");
                break;
    
            case 3:
                if(password.equals("adminpass"))
                {
                    Admin admin = new Admin(email, password, name, dataManager);
                    dataManager.addUser(admin);
                    loginManager.signUp(admin);
                    System.out.println("Admin sign up successful!");
                    break;
                }
                else
                {
                    System.out.println("Wrong Password.");
                }
    
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    

    // Student Menu
    private static void studentMenu(Student student) 
    {
        boolean exit = false;

        while (!exit) 
        {
            System.out.println("\nStudent Menu ");
            System.out.println("1. Get Student Details");
            System.out.println("   1a. View roll number");
            System.out.println("   1b. View current semester");
            System.out.println("   1c. View grades");
            System.out.println("   1d. View credits");
            System.out.println("   1e. View schedule");
            System.out.println("2. Track Progress");
            System.out.println("   2a. Calculate CGPA");
            System.out.println("   2b. Calculate SGPA");
            System.out.println("   2c. View progress report");
            System.out.println("3. Manage Courses");
            System.out.println("   3a. View completed courses");
            System.out.println("   3b. View available courses");
            System.out.println("   3c. View registered courses");
            System.out.println("   3d. View details of a course");
            System.out.println("   3e. Register for a course");
            System.out.println("   3f. Drop a course");
            System.out.println("4. Manage Complaints");
            System.out.println("   4a. Submit a complaint");
            System.out.println("   4b. View complaints");
            System.out.println("5. Give Feedback");
            System.out.println("   5a. Give rating");
            System.out.println("   5b. Give a comment");
            System.out.println("6. Logout");
            System.out.println();
            
            System.out.print("\nEnter your choice (e.g., 1a, 3b): ");
            String choice = scanner.nextLine();
            switch (choice) 
            {
                // Student details section
                case "1a":
                    if(student.getRollNo()==0)
                    {
                        System.out.println("\nRoll number not assigned yet.");
                        break;
                    } 
                    System.out.println("\nRoll number: " + student.getRollNo());
                    break;
                case "1b":
                    System.out.println("\nCurrent semester: " + student.getSemester());
                    break;
                case "1c":
                    System.out.print("\nEnter the semester (or -1 for all semesters): ");
                    int semester = scanner.nextInt();
                    scanner.nextLine();
                    if (semester == -1){
                        student.viewGrades();
                        break;
                    }
                    student.viewGrades(semester);
                    break;
                case "1d":
                    System.out.print("\nEnter the semester (or -1 for all semesters): ");
                    semester = scanner.nextInt();
                    scanner.nextLine();
                    if (semester == -1){
                        System.out.println("Total Credits: " + student.getCredits());
                        break;
                    }
                    System.out.println("Total Credits: " + student.getCredits(semester));
                    break;
                case "1e":
                    student.viewSchedule();
                    break;
                // Progress tracking section
                case "2a":
                    System.out.printf("CGPA: %.2f\n", student.calculateCgpa());
                    break;
                case "2b":
                    System.out.print("Enter the semester for SGPA: ");
                    semester = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.printf("SGPA for semester %d: %.2f\n", semester, student.calculateSgpa(semester));
                    break;
                case "2c":
                    System.out.print("Enter the semester (or -1 for all semesters): ");
                    semester = scanner.nextInt();
                    scanner.nextLine();
                    if (semester == -1){
                        student.trackProgress();
                        break;
                    }
                    student.trackProgress(semester);
                    break;
                // Course management section
                case "3a":
                    student.viewCompletedCourses();
                    break;
                case "3b": 
                    System.out.print("Enter the semester (or -1 for all semesters): ");
                    semester = scanner.nextInt();
                    scanner.nextLine(); 
                    if (semester == -1){
                        student.viewCourses();
                        break;
                    }
                    student.viewCourses(semester);
                    break;
                case "3c":
                    List<Course> currentCourses = student.getCurrentCourses();
                    System.out.println("Registered Courses:");
                    for (Course course : currentCourses) {
                        System.out.println("Title: " + course.getTitle() + ", Code: "+ course.getCode());
                    }
                    break;
                case "3d":
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    student.viewCourse(courseCode);
                    break;
                case "3e":
                    System.out.print("Enter course code to register: ");
                    String courseToRegister = scanner.nextLine();
                    student.registerCourse(courseToRegister);
                    break;
                case "3f":
                    System.out.print("Enter course code to drop: ");
                    String courseToDrop = scanner.nextLine();
                    student.dropCourse(courseToDrop);
                    break;

                // Complaint management section
                case "4a":
                    System.out.print("Enter complaint description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter complaint date (dd/mm/yyyy): ");
                    String date = scanner.nextLine();
                    student.submitComplaint(description, date);
                    System.out.println("Complaint submitted successfully.");
                    break;
                case "4b":
                    student.viewAllComplaints();
                    break;
                // Feedback section
                case "5a":
                    System.out.println("Enter course code: ");
                    courseCode = scanner.nextLine();
                    System.out.println("Rate the course out of 5: ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();
                    if(rating<6 && rating>0)
                    {
                        Feedback<Integer>fbi =new Feedback<Integer>(rating);
                        student.addFeedback(courseCode,fbi);
                        break;
                    }
                    System.out.println("Invalid rating");
                    break;
                case "5b":
                    System.out.println("Enter course code: ");
                    courseCode = scanner.nextLine();
                    System.out.println("Comment a feedback: ");
                    String comment = scanner.nextLine();
                    Feedback<String>fbs =new Feedback<String>(comment);
                    student.addFeedback(courseCode,fbs);
                    break;

                // Logout
                case "6":
                    System.out.println("Logging out...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Professor Menu
    private static void professorMenu(Professor professor) 
    {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nProfessor Menu");
            System.out.println("1. View assigned course");
            System.out.println("2. Update course details");
            System.out.println("    2a. Update credits");
            System.out.println("    2b. Update course timings");
            System.out.println("    2c. Update max enrolments");
            System.out.println("    2d. Add a prerequisite");
            System.out.println("    2e. Remove a prerequisite");
            System.out.println("3. View enrolled students");
            System.out.println("4. Assign grade to a student");
            System.out.println("5. Add an office hour");
            System.out.println("6. View office hours");
            System.out.println("7. View feedbacks");
            System.out.println("8. Handle TAs");
            System.out.println("    8a. View all TAs");
            System.out.println("    8b. Add a TA");
            System.out.println("    8c. Remove a TA");
            System.out.println("    8d. View students eligible to be TA");
            System.out.println("9. Logout");
            System.out.println();

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    professor.viewCourseDetails();
                    break;
                case "2a":
                    System.out.println("New credits: ");
                    int newCredits = scanner.nextInt();
                    scanner.nextLine();
                    professor.updateCredits(newCredits);
                    break;
                case "2b":
                    System.out.println("Enter start time(HHMM): ");
                    String startTime = scanner.nextLine();
                    System.out.println("Enter end time(HHMM): ");
                    String endTime = scanner.nextLine();
                    int result = startTime.compareTo(endTime);
                    if(result<0)
                    {
                        professor.updateTimings(startTime,endTime);
                        break;
                    }
                    System.out.println("Invalid timings.");
                    break;
                case "2c":
                    System.out.println("New max enrolments: ");
                    int newMaxEnrolments = scanner.nextInt();
                    scanner.nextLine();
                    if(newMaxEnrolments<professor.getCourse().getStudents().size())
                    {
                        System.out.println("Invalid max enrolments.");
                        break;
                    }
                    professor.updateMaxEnrolments(newMaxEnrolments);
                    break;
                case "2d":
                    System.out.println("Enter prerequisite course code: ");
                    String preCode = scanner.nextLine();
                    professor.addPrerequisite(preCode);
                    break;
                case "2e":
                    System.out.println("Enter course code: ");
                    String code = scanner.nextLine();
                    professor.removePrerequisite(code);
                case "3":
                    professor.viewEnrolledStudents();
                    break;
                case "4":
                    System.out.println("Enter email of the Student: ");
                    String email = scanner.nextLine();
                    System.out.println("Enter grade of the Student: ");
                    int grade = scanner.nextInt();
                    scanner.nextLine();
                    professor.assignGrade(email, grade);
                    break;
                case "5":
                    System.out.println("Enter day: ");
                    String day = scanner.nextLine();
                    System.out.println("Enter Start Time(HHMM): ");
                    startTime = scanner.nextLine();
                    System.out.println("Enter End Time(HHMM): ");
                    endTime = scanner.nextLine();
                    result = startTime.compareTo(endTime);
                    if(result>=0)
                    {
                        System.out.println("Invalid timings.");
                        break;
                    }
                    System.out.println("Enter the location: ");
                    String location = scanner.nextLine();
                    Schedule schedule = new Schedule(day, startTime, endTime, location);
                    professor.addOfficeHour(schedule);
                    break;
                case "6":
                    professor.viewOfficeHours();
                    break;
                case "7":
                    professor.viewFeedbacks();
                    break;
                case "8a":
                    professor.viewAllTAs();
                    break;
                case "8b":
                    System.out.println("Enter email of Student to make TA: ");
                    email = scanner.nextLine();
                    professor.addTA(email);
                    break;
                case "8c":
                    System.out.println("Enter email of TA to be removed: ");
                    email = scanner.nextLine();
                    professor.removeTA(email);
                    break;
                case "8d":
                    professor.viewStudentsEligibleForTA();
                case "9":
                    System.out.println("Logging out...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Admin Menu
    private static void adminMenu(Admin admin)
    {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nAdmin Menu ");
            System.out.println("1. Manage Courses");
            System.out.println("    1a. View all courses");
            System.out.println("    1b. Add a new course");
            System.out.println("    1c. Remove a course");
            System.out.println("2. Manage Students");
            System.out.println("    2a. View all students");
            System.out.println("    2b. View grades of a student");
            System.out.println("    2c. Track progress of a student");
            System.out.println("3. Manage Professors");
            System.out.println("    3a. View all professors");
            System.out.println("    3b. Assign a course to a professor");
            System.out.println("4. Manage Complaints");
            System.out.println("    4a. View all complaints");
            System.out.println("    4b. Handle a complaint");
            System.out.println("5. Logout");
            System.out.println();

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1a": //View all courses
                    admin.viewCourses();
                    break;
                case "1b": //Add a new course
                    System.out.print("Enter course title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter professor's name: ");
                    String professorName = scanner.nextLine();
                    System.out.print("Enter semester: ");
                    int semester = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter course credits: ");
                    int credits = scanner.nextInt();
                    scanner.nextLine();
                    admin.addCourse(title, code, professorName, semester, credits, null);
                    break;

                case "1c": //Remove a course
                    System.out.print("Enter course code to remove: ");
                    String courseCodeToRemove = scanner.nextLine();
                    admin.removeCourse(courseCodeToRemove);
                    break;

                case "2a": //View all students
                    admin.viewAllStudents();
                    break;

                case "2b": //View grades of a student
                    System.out.print("Enter student's email to view grades: ");
                    String studentEmail = scanner.nextLine();
                    admin.viewStudentGrades(studentEmail);
                    break;

                case "2c": //Track progress of a student
                    System.out.print("Enter student's email to track progress: ");
                    String studentEmailProgress = scanner.nextLine();
                    admin.trackStudentProgress(studentEmailProgress);
                    break;

                case "3a": //View all professors
                    admin.viewAllProfessors();
                    break;

                case "3b": //Assign a course to a professor
                    System.out.print("Enter professor's email: ");
                    String professorEmail = scanner.nextLine();
                    System.out.print("Enter course code to assign: ");
                    String courseCodeToAssign = scanner.nextLine();
                    admin.assignCourseToProfessor(professorEmail, courseCodeToAssign);
                    break;

                case "4a": //View all complaints
                    admin.viewAllComplaints();
                    break;

                case "4b": //Handle a complaint
                    System.out.print("Enter student's email to handle complaint: ");
                    String complaintEmail = scanner.nextLine();
                    admin.handleComplaint(complaintEmail);
                    break;

                case "5":
                    exit = true;
                    System.out.println("Exiting admin menu.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void taMenu(TA ta)
    {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nTA Menu ");
            System.out.println("1. Get Student Details");
            System.out.println("   1a. View roll number");
            System.out.println("   1b. View current semester");
            System.out.println("   1c. View grades");
            System.out.println("   1d. View credits");
            System.out.println("   1e. View schedule");
            System.out.println("2. Track Progress");
            System.out.println("   2a. Calculate CGPA");
            System.out.println("   2b. Calculate SGPA");
            System.out.println("   2c. View progress report");
            System.out.println("3. Manage Courses");
            System.out.println("   3a. View completed courses");
            System.out.println("   3b. View available courses");
            System.out.println("   3c. View registered courses");
            System.out.println("   3d. View details of a course");
            System.out.println("   3e. Register for a course");
            System.out.println("   3f. Drop a course");
            System.out.println("4. Manage Complaints");
            System.out.println("   4a. Submit a complaint");
            System.out.println("   4b. View complaints");
            System.out.println("5. Give Feedback");
            System.out.println("   5a. Give rating");
            System.out.println("   5b. Give a comment");
            System.out.println("6. Functionalities as a TA");
            System.out.println("   6a. View assigned course");
            System.out.println("   6b. View students");
            System.out.println("   6c. Assign grade");
            System.out.println("7. Logout");
            System.out.println();

            System.out.print("\nEnter your choice (e.g., 1a, 3b): ");
            String choice = scanner.nextLine();
            switch (choice) {
                // Student details section
                case "1a":
                    if (ta.getRollNo() == 0) {
                        System.out.println("\nRoll number not assigned yet.");
                        break;
                    }
                    System.out.println("\nRoll number: " + ta.getRollNo());
                    break;
                case "1b":
                    System.out.println("\nCurrent semester: " + ta.getSemester());
                    break;
                case "1c":
                    System.out.print("\nEnter the semester (or -1 for all semesters): ");
                    int semester = scanner.nextInt();
                    scanner.nextLine();
                    if (semester == -1) {
                        ta.viewGrades();
                        break;
                    }
                    ta.viewGrades(semester);
                    break;
                case "1d":
                    System.out.print("\nEnter the semester (or -1 for all semesters): ");
                    semester = scanner.nextInt();
                    scanner.nextLine();
                    if (semester == -1) {
                        System.out.println("Total Credits: " + ta.getCredits());
                        break;
                    }
                    System.out.println("Total Credits: " + ta.getCredits(semester));
                    break;
                case "1e":
                    ta.viewSchedule();
                    break;
                // Progress tracking section
                case "2a":
                    System.out.printf("CGPA: %.2f\n", ta.calculateCgpa());
                    break;
                case "2b":
                    System.out.print("Enter the semester for SGPA: ");
                    semester = scanner.nextInt();
                    scanner.nextLine();
                    System.out.printf("SGPA for semester %d: %.2f\n", semester, ta.calculateSgpa(semester));
                    break;
                case "2c":
                    System.out.print("Enter the semester (or -1 for all semesters): ");
                    semester = scanner.nextInt();
                    scanner.nextLine();
                    if (semester == -1) {
                        ta.trackProgress();
                        break;
                    }
                    ta.trackProgress(semester);
                    break;
                // Course management section
                case "3a":
                    ta.viewCompletedCourses();
                    break;
                case "3b":
                    System.out.print("Enter the semester (or -1 for all semesters): ");
                    semester = scanner.nextInt();
                    scanner.nextLine();
                    if (semester == -1) {
                        ta.viewCourses();
                        break;
                    }
                    ta.viewCourses(semester);
                    break;
                case "3c":
                    List<Course> currentCourses = ta.getCurrentCourses();
                    System.out.println("Registered Courses:");
                    for (Course course : currentCourses) {
                        System.out.println("Title: " + course.getTitle() + ", Code: " + course.getCode());
                    }
                    break;
                case "3d":
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    ta.viewCourse(courseCode);
                    break;
                case "3e":
                    System.out.print("Enter course code to register: ");
                    String courseToRegister = scanner.nextLine();
                    ta.registerCourse(courseToRegister);
                    break;
                case "3f":
                    System.out.print("Enter course code to drop: ");
                    String courseToDrop = scanner.nextLine();
                    ta.dropCourse(courseToDrop);
                    break;

                // Complaint management section
                case "4a":
                    System.out.print("Enter complaint description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter complaint date (dd/mm/yyyy): ");
                    String date = scanner.nextLine();
                    ta.submitComplaint(description, date);
                    System.out.println("Complaint submitted successfully.");
                    break;
                case "4b":
                    ta.viewAllComplaints();
                    break;
                // Feedback section
                case "5a":
                    System.out.println("Enter course code: ");
                    courseCode = scanner.nextLine();
                    System.out.println("Rate the course out of 5: ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();
                    if (rating < 6 && rating > 0) {
                        Feedback<Integer> fbi = new Feedback<Integer>(rating);
                        ta.addFeedback(courseCode, fbi);
                        break;
                    }
                    System.out.println("Invalid rating");
                    break;
                case "5b":
                    System.out.println("Enter course code: ");
                    courseCode = scanner.nextLine();
                    System.out.println("Comment a feedback: ");
                    String comment = scanner.nextLine();
                    Feedback<String> fbs = new Feedback<String>(comment);
                    ta.addFeedback(courseCode, fbs);
                    break;
                // TA functionality
                case "6a":
                    ta.viewAssignedCourse();
                    break;
                case "6b":
                    ta.viewStudents();
                    break;
                case "6c":
                    System.out.println("Enter email of student to assign a grade: ");
                    String email=scanner.nextLine();
                    System.out.println("Enter grade to assign: ");
                    int grade=scanner.nextInt();
                    scanner.nextLine();
                    ta.assignGrade(email,grade);
                    break;
                // Logout
                case "7":
                    System.out.println("Logging out...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // Method to seed initial data into the system
    private static void seedData() {
        Admin admin = new Admin("admin@iiitd.edu", "adminpass", "iiitd Admin", dataManager);
        dataManager.addUser(admin);
        loginManager.signUp(admin);

        Student student1 = new Student("student1@iiitd.edu", "password1", "Student One", 2024101);
        dataManager.addUser(student1);
        loginManager.signUp(student1);
        Student student2 = new Student("student2@iiitd.edu", "password2", "Student Two", 2024102);
        dataManager.addUser(student2);
        loginManager.signUp(student2);
        Student student3 = new Student("student3@iiitd.edu", "password3", "Student Three", 2024103);
        dataManager.addUser(student3);
        loginManager.signUp(student3);
        
//        Course course1 = new Course("Linear Algebra", "M101", "Professor One", 1, 4, null);
//        dataManager.addCourse(course1);
        Course course2 = new Course("Digital Circuits", "ECE101", "Professor Two", 1, 4, null);
        dataManager.addCourse(course2);
        Course course3 = new Course("Introduction to Programming", "CSE101", "Professor Three", 1, 4, null);
        dataManager.addCourse(course3);
        Course course4 = new Course("Competetive Programming 1", "CSE211", "Professor Four", 2, 2, null);
        dataManager.addCourse(course4);
        Course course5 = new Course("Data Structures and Algorithms", "CSE201", "Professor Five", 2, 4, null);
        dataManager.addCourse(course5);
        Course course6 = new Course("Advanced Programming", "CSE301", "Professor Six", 3, 4, null);
        dataManager.addCourse(course6);
        Course course7 = new Course("Operating System", "CSE311", "Professor Seven", 3, 4, null);
        dataManager.addCourse(course7);

//        Professor professor1 = new Professor("prof1@iiitd.edu", "profpass1", "Professor One", course1);
//        dataManager.addUser(professor1);
//        loginManager.signUp(professor1);
        Professor professor2 = new Professor("prof2@iiitd.edu", "profpass2", "Professor Two", course2);
        dataManager.addUser(professor2);
        loginManager.signUp(professor2);
        Professor professor3 = new Professor("prof3@iiitd.edu", "profpass3", "Professor Three", course3);
        dataManager.addUser(professor3);
        loginManager.signUp(professor3);
        Professor professor4 = new Professor("prof4@iiitd.edu", "profpass4", "Professor Four", course4);
        dataManager.addUser(professor4);
        loginManager.signUp(professor4);
        Professor professor5 = new Professor("prof5@iiitd.edu", "profpass5", "Professor Five", course5);
        dataManager.addUser(professor5);
        loginManager.signUp(professor5);
        Professor professor6 = new Professor("prof6@iiitd.edu", "profpass6", "Professor Six", course6);
        dataManager.addUser(professor6);
        loginManager.signUp(professor6);
        Professor professor7 = new Professor("prof7@iiitd.edu", "profpass7", "Professor Seven", course7);
        dataManager.addUser(professor7);
        loginManager.signUp(professor7);
    }

    private static void testTA()
    {
        Course course1 = new Course("Linear Algebra", "M101", "Professor One", 1, 4, null);
        dataManager.addCourse(course1);

        Student tester = new Student("1", "1", "1", 2024104);
        dataManager.addUser(tester);
        loginManager.signUp(tester);
        tester.registerCourse("M101");

        Professor professor1 = new Professor("proftest", "prof", "Professor One", course1);
        dataManager.addUser(professor1);
        loginManager.signUp(professor1);

        professor1.assignGrade("1",10);
        professor1.addTA("1");
        DataManager.getInstance().viewTaList();
    }
}