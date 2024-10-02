import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Student extends User { //Inheritance
    private int semester;
    private final int rollNo;
    private List<Course> completedCourses; //course is association
    private List<Course> currentCourses;
    private List<Complaint> complaints;
    private TA ta;

    public Student(String email, String password, String name, int rollNo){
        super(email, password, name);
        this.semester=1;
        this.rollNo = rollNo;
        this.completedCourses = new ArrayList<>();
        this.currentCourses = new ArrayList<>();
        this.complaints = new ArrayList<>();
        this.ta=null;
    }

    public void setSemester(int semester)
    {
        this.semester = semester;
    }

    // Getters
    public int getRollNo()
    {
        return this.rollNo;
    }
    public List<Course> getCurrentCourses()
    {
        return this.currentCourses;
    }
    public int getSemester()
    {
        return this.semester;
    }

    public int getCredits(int semester)
    {
        // This method will return the credits of the student for a specific semester 
        int credits=0;
        for (Course course : this.completedCourses)
        {
            if (course.getSemester() == semester)
            {
                credits+=course.getCredits();
            }
        }
        return credits;
    }

    public int getCredits()
    {
        // This method will return the all credits of the student
        int credits=0;
        for (Course course : this.completedCourses)
        {
            credits+=course.getCredits();
        }
        return credits;
    }

    public TA getTa() {
        return ta;
    }

    public void setTa(TA ta) {
        this.ta = ta;
    }

    public boolean isTa()
    {
        if (this.ta!=null) return true;
        return false;
    }

    public List<Course> getCompletedCourses() {
        return completedCourses;
    }

    public double calculateCgpa()
    {
        // This method will calculate the CGPA of the student based on the completed courses
        double totalGrades = 0.0;
        for (Course course : this.completedCourses){
            totalGrades+=course.getGrade(this)*course.getCredits();
        }
        int   totalCredits = this.getCredits();

        if (totalCredits==0) {
            System.out.println("There are no Course Credits!");
            return 0;
        }

        double cgpa = totalGrades/totalCredits;
        cgpa = Math.round(cgpa * 100.0) / 100.0;
        return cgpa;
    }

    public double calculateSgpa(int semester)
    {
        // This method will calculate the SGPA of the student based on the completed courses of the last semester
        if (semester>=this.semester) 
        {
            System.out.println("Grades not received for this semester yet.");
            return 0;
        }
        
        double totalGrades = 0.0;
        for (Course course : this.completedCourses){
            if (course.getSemester() == semester) {
                totalGrades+=course.getGrade(this)*course.getCredits();
            }
        }
        int   totalCredits = this.getCredits(semester);
        
        if (totalCredits==0) {
            System.out.println("There are no Course Credits!");
            return 0;
        }
        
        double sgpa = totalGrades/totalCredits;
        sgpa = Math.round(sgpa * 100.0) / 100.0;
        return sgpa;
    }

    public void viewCompletedCourses() 
    {
        // This method will display the completed courses of the student with grade
        System.out.println("\n");
        if (completedCourses.isEmpty()) {
            System.out.println("No courses completed yet.");
            return;
        }
        for (Course course : this.completedCourses)
        {
                System.out.println(course.getTitle() + " " + course.getGrade(this));
        }
    }

    public void viewCourses(int semester)
    {
        // This method will display the details of the current courses of the student
        System.out.println("\n");
        List<Course> courseList = DataManager.getInstance().getCourseList(); //Student is dependent on DataManager
        for (Course course : courseList)
        {
            if (course.getSemester()==semester)
            {
                System.out.println("Course Title: "+ course.getTitle());
                System.out.println("Course Code: "+ course.getCode());
                System.out.println("Professor Name: "+ course.getProfessorName());
                System.out.printf("Credits: %d\n", course.getCredits());
                System.out.printf("Max Enrolments: %d\n", course.getMaxEnrolments());
                System.out.println("Prerequisites: " + course.getPrerequisites());
                System.out.println("Weekly Schedule: " + course.getWeeklySchedule());
            }
        }
    }

    public void viewCourses()
    {
        // This method will display the details of the current courses of the student
        System.out.println("\n");
        List<Course> courseList = DataManager.getInstance().getCourseList();
        if (courseList.isEmpty()) {
            System.out.println("No courses Available.");
            return;
        }
        boolean check = false;
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
            check = true;
        }
        if (!check) {
            System.out.println("No courses Available.");
        }
    }

    public void viewCourse(String courseCode)
    {
        // This method will display the details of a particular course
        System.out.println("\n");
        List<Course> courseList = DataManager.getInstance().getCourseList();
        for (Course course : courseList)
        {
            if (course.getCode().equals(courseCode))
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
                return;
            }
        }
        System.out.println("Course "+ courseCode + " doesnt exist.");
    }

    public void viewGrades(int semester)
    {
        // This method will display the grades of the student for a specific semester
        System.out.println("\n");
        if (semester>=this.semester) 
        {
            System.out.println("Grades not received for this semester yet.");
            return;
        }
        boolean check = false;
        for (Course course : this.completedCourses)
        {
            if(course.getSemester()==semester)
            {
                System.out.println("Title: "+ course.getTitle() + ", Grade: " + course.getGrade(this));
                check = true;
            }
        }
        if (!check){
            System.out.println("Grades not received for this semester yet.");
        }
    }

    public void viewGrades()
    {
        System.out.println("\n");
        boolean check =false;
        for (Course course : this.completedCourses)
        {
            System.out.println("Semester: "+ course.getSemester()+ ", Title: "+ course.getTitle() + ", Grade: " + course.getGrade(this));
            check = true;
        }
        if(!check){
            System.out.println("No grades received yet.");
        }
    }
    public void viewSchedule() 
    {
        // This method displays the schedule of the courses the student is taking
        System.out.println("\n");
        if (currentCourses.isEmpty()) {
            System.out.println("No courses taken.");
            return;
        }
        for (Course course: this.currentCourses){
            System.out.println("Weekly Schedule: ");
            for (Schedule schedule : course.getWeeklySchedule())
            {
                System.out.println(schedule);
            }
            System.out.println();
        }
    }
    
    public void trackProgress(int semester)
    {
        // This method will display the progress of the student for a specific semester
        this.viewGrades(semester);
        System.out.println("SGPA of Student " + this.getName() + " is " + this.calculateSgpa(semester));
    }
    public void trackProgress()
    {
        // This method will display the progress of the student for all completed semesters
        this.viewGrades();
        System.out.println("CGPA of Student " + this.getName() + " is " + this.calculateCgpa());
    }
    public void registerCourse(String code) throws CourseFullException {
        // This method will register a course for the student
        List<Course> courseList = DataManager.getInstance().getCourseList();

        for (Course course : this.currentCourses) {
            if (course.getCode().equals(code)) {
                System.out.println("You are already registered for this course.");
                return;
            }
        }

        for (Course c : this.completedCourses) {
            if (c.getCode().equals(code)) {
                System.out.println("You have already completed this course.");
                return;
            }
        }
        // Create a new course object
        Course course = null;
        for (Course c : courseList) {
            if (c.getCode().equals(code)) {
                course = c;
                break;
            }
        }
        
        if(course.getSemester()!=this.semester)
        {
            System.out.println("This Course is not available in this semester");
            return;
        }

        if (course.getStudents().size() >= course.getMaxEnrolments()) {
            throw new CourseFullException("Course " + course.getCode() + " is full.");
        }

        int totalCredits = this.currentCourses.stream().mapToInt(Course::getCredits).sum();
        if (totalCredits + course.getCredits() > 20) {
            System.out.println("You cannot register for more than 20 credits.");
            return;
        }

        if(!this.completedCourses.containsAll(course.getPrerequisites())){
            System.out.println("You do not meet the prerequisites for this course.");
            return;
        }
        // Add the course to the overall courseList
        if(course.addStudent(this)){
            this.currentCourses.add(course);
            System.out.println("Course registered: " + course.getTitle());
        }
    }
    
    public void dropCourse(String code) {
        for (Course course : this.currentCourses) {
            if (course.getCode().equals(code)) {
                this.currentCourses.remove(course);
                course.removeStudent(this);
                System.out.println("Course with code " + code + " dropped.");
                return;
            }
        }
        System.out.println("You are not enrolled in this course.");
    }

    public void completedSemester(){
        this.completedCourses.addAll(this.currentCourses);
        // for(Course course : this.currentCourses){
        //     this.dropCourse(course.getCode());
        // }
        // Use an iterator to safely remove courses from the list
        Iterator<Course> iterator = this.currentCourses.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            iterator.remove();  // Safely remove the course from the list
            course.getStudents().remove(this);  // Also remove the student from the course
        }
    }
    
    public void submitComplaint(String desc, String date) {
        // Your code here
        Complaint complaint = new Complaint(desc, date); //Complaint is composition
        complaint.setStudentName(this.getName());
        complaint.setStudentEmail(this.getEmail());
        complaint.setStudentRollNo(this.getRollNo());
        this.complaints.add(complaint);
    }

    public List<Complaint> getComplaints()
    {
        return this.complaints;
    }

    public void viewAllComplaints()
    {
        System.out.println("Complaints:");
        for (Complaint complaint : complaints) {
            System.out.println(complaint);
        }
    }

    @Override
    public String toString() {
        return "Name: " + this.getName() + ", RollNo: " + this.getRollNo() + ", Email: " + this.getEmail();
    }


    public void addFeedback(String code, Object fb){
        Course course = null;
        for(Course c : this.completedCourses)
        {
            if(c.getCode().equals(code))
            {
                course = c;
                break;
            }
        }
        Feedback<Object> feedback = new Feedback<>(fb);
        if(course != null)
        {
            course.addFeedback(feedback);
            System.out.println("Feedback added.");
        }
        else
        {
            System.out.println("This course is not completed.");
        }
    }
}