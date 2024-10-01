import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Course {
    private final String title;
    private final String code;
    private final String professorName;
    private final int semester;
    private int credits;
    private List<Course> prerequisites;
    private List<Student> enrolledStudents; //2 way association with student
    private List<Schedule> weeklySchedule;
    private Map<Student, Integer> studentGrades;  // Mapping between student and their grade
    private int maxEnrolments;
    private String startTime;
    private String endTime;
    private List<Feedback<?>> feedbackList;


    public Course(String title, String code, String professorName, int semester, int credits, List<Course> prerequisites) {
        this.title = title;
        this.code = code;
        this.professorName = professorName;
        this.semester = semester;
        this.credits = credits;
        this.prerequisites = prerequisites != null ? prerequisites : new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
        this.weeklySchedule = new ArrayList<>();
        this.studentGrades = new HashMap<>();
        this.maxEnrolments = 30; 
        this.startTime = "0000";
        this.endTime = "0001";
        this.feedbackList = new ArrayList<>();
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public String getProfessorName() {
        return professorName;
    }

    public int getSemester() {
        return semester;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits==2 || credits==4)
        {
            this.credits = credits;
        }

    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void addPrerequisite(String code) {
        List<Course> courseList = DataManager.getInstance().getCourseList();
        for(Course prerequisite : courseList)
        {
            if(prerequisite.getCode().equals(code))
            {
                this.prerequisites.add(prerequisite);
            }
        }
    }
    public boolean removePrerequisite(String code)
    {
        for(Course course : this.prerequisites)
        {
            if(course.getCode().equals(code))
            {
                this.prerequisites.remove(course);
                return true;
            }
        }
        return false;
    }

    public List<Student> getStudents() {
        return enrolledStudents;
    }

    public boolean addStudent(Student student) {
        if (enrolledStudents.size() < maxEnrolments) {
            enrolledStudents.add(student);
            return true;
        } else {
            System.out.println("Course is full! Cannot enroll more students.");
            return false;
        }
    }

    public void removeStudent(Student student) {
        enrolledStudents.remove(student);
    }

    public List<Schedule> getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setWeeklySchedule(List<Schedule> schedule) {
        this.weeklySchedule = schedule;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setMaxEnrolments(int maxEnrolments) {
        this.maxEnrolments = maxEnrolments;
    }

    public int getMaxEnrolments() {
        return maxEnrolments;
    }

    public List<Feedback<?>> getFeedbackList() {
        return feedbackList;
    }

    // Grade management
    public void setGrade(Student student, int grade) {
        if (enrolledStudents.contains(student)) {
            if (grade <= 10 && grade >= 4) {
                studentGrades.put(student, grade);
                System.out.println("Grade updated for " + student.getName() + ": " + grade);
            } else {
                System.out.println("Grade is out of range.");
            }
        } else {
            System.out.println("Student is not enrolled in this course.");
        }
    }

    public int getGrade(Student student) {
        return studentGrades.getOrDefault(student, 0);
    }

    public Map<Student, Integer> getStudentGrades() {
        return studentGrades;
    }

    // View enrolled students
    public void viewEnrolledStudents() {
        if (enrolledStudents.isEmpty()) {
            System.out.println("No students are currently enrolled.");
        } else {
            for (Student student : enrolledStudents) {
                System.out.println("Student Name: " + student.getName() + ", Roll No: " + student.getRollNo() + ", Email: " + student.getEmail());
            }
        }
    }

    public void addFeedback(Feedback<?> feedback)
    {
        feedbackList.add(feedback);
    }
}
