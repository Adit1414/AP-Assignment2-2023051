import java.util.ArrayList;
import java.util.List;

public class Professor extends User
{
    private Course course;
    private List<Schedule> officeHours;
    

    public Professor(String email, String password, String name, Course course)
    {
        super(email, password, name);
        officeHours = new ArrayList<Schedule>();
        this.course = course;
    }

    public Course getCourse()
    {
        return course;
    }
    public void setCourse(Course course)
    {
        this.course = course;
    }

    public void assignGrade(String email, int grade) 
    {
        Student student = (Student)DataManager.getInstance().getUserByEmail(email);
        if (course == null)
        {
            System.out.println("Professor is not assigned to any course.");
            return;
        }
        if (student == null) 
        {
            System.out.println("Student not found.");
            return;
        }
        if(grade>10 || grade<4)
        {
            System.out.println("Invalid grade");
            return;
        }
        if (course.getStudents().contains(student)) 
        {
            course.setGrade(student, grade);
            System.out.println("Grade " + grade + " assigned to student " + student.getName() + " for course " + course.getTitle() + ".");

            for(Course c: student.getCurrentCourses())
            {
                if(!(c.getGrade(student)>4 && c.getGrade(student)<=10))
                {
                    return;
                }
            }
            System.out.println("Student " + student.getName() + " has completed semester " + student.getSemester());
            student.setSemester(student.getSemester()+1);
            student.completedSemester();
        }

        else 
        {
            System.out.println("Student " + student.getName() + " is not enrolled in the course " + course.getTitle() + ".");
        }
    }

    // Add Office Hours
    public void addOfficeHour(Schedule  officeHour)
    {
        this.officeHours.add(officeHour);
    }

    // View Office Hours
    public void viewOfficeHours() 
    {
        System.out.println("Office Hours for " + this.getName() + ":");
        for (Schedule schedule : this.officeHours) 
        {
            System.out.println(schedule);
        }
    }

    // Manage course details
    public void updateCredits(int credits) 
    {
        if (course != null) 
        {
            if(credits==2 || credits==4)
            {
                this.course.setCredits(credits);
                System.out.println("Course credits updated to " + credits);
            }
            else
            {
                System.out.println("Invalid credits.");
            }
        }
        else 
        {
            System.out.println("No course assigned.");
        }
    }

    public void updateTimings(String startTime, String endTime) 
    {
        if (course != null) 
        {
            this.course.setStartTime(startTime);
            this.course.setEndTime(endTime);
            System.out.println("Timings updated for " + this.course.getCode());
        }
        else
        {
            System.out.println("No course assigned.");
        }
    }

    public void addPrerequisite(String code) 
    {        
        List<Course> courseList = DataManager.getInstance().getCourseList();
        if (this.course != null) 
        {
            for (Course course : courseList)
            {
                if (course.getCode().equals(code) && course.getSemester()<this.course.getSemester())
                {
                    this.course.addPrerequisite(code);
                    System.out.println("Prerequisite added for " + this.course.getCode());
                    return;
                }
                else if (course.getCode().equals(code) && course.getSemester()>=this.course.getSemester())
                {
                    System.out.println(code + " cannot be added as a prerequisite.");
                    return;
                }
            }
            System.out.println(code + " course does not exist.");
        }
        else
        {
            System.out.println("No course assigned.");
        }
    }
    public void removePrerequisite(String code)
    {
        if (this.course != null)
        {
            if(this.course.removePrerequisite(code))
            {
                System.out.println("Prerequisites updated for " + this.course.getCode());
            }
            else
            {
                System.out.println("Course not found");
            }
        }
        else
        {
            System.out.println("No course assigned.");
        }
    }

    public void updateMaxEnrolments(int maxEnrolments)
    {
        if (course != null) 
        {
            course.setMaxEnrolments(maxEnrolments);
            System.out.println("Max Enrolments updated for " + this.course.getCode());
        }
        else
        {
            System.out.println("No course assigned.");
        }
    }

    // View enrolled students
    public void viewEnrolledStudents() 
    {
        if (course != null) 
        {
            boolean check = false;
            for (Student student : this.course.getStudents()) {
                System.out.println(student);
                check =true;
            }
            if(!check)
            {
                System.out.println("No students enrolled");
            }
        }
        else
        {
            System.out.println("No course assigned.");
        }
    }

    //Method to view course details
    public void viewCourseDetails() 
    {
        if (course != null) 
        {
            System.out.println("Course Code: " + course.getCode());
            System.out.println("Course Title: " + course.getTitle());
            System.out.printf("Course Credits: %d\n", course.getCredits());
            System.out.println("Prerequisites: " + course.getPrerequisites());
            System.out.println("Max Enrolments: " + course.getMaxEnrolments());
            System.out.println("Enrolled Students: " + course.getStudents());
        } 
        else 
        {
                System.out.println("No course assigned.");
        }
    }


    public void viewFeedbacks()
    {
        List<Feedback<?>> feedbackList = this.course.getFeedbackList();
        int i=1;
        for(Feedback<?>fb : feedbackList)
        {
            System.out.println(String.valueOf(i)+ ") " + fb.getFeedback().toString());
            i+=1;
        }
    }

//    public void removeTA(String email)
//    {
//        DataManager dataManager = DataManager.getInstance();
//        TA ta = (TA)dataManager.getUserByEmail(email);
//        List<TA> taList = dataManager.getTAsByCourse(this.course);
//
//        if (taList.contains(ta))
//        {
//            dataManager.removeTA(ta);
//            System.out.println("TA " + ta.getName() + " has been removed.");
//        }
//        else
//        {
//            System.out.println("TA not found or not assigned to your course.");
//        }
//    }

    public void viewStudentsEligibleForTA()
    {
        List<Student> students = DataManager.getInstance().getAllUsers(Student.class);
        if (students.isEmpty())
        {
            System.out.println("No students found.");
        }
        else
        {
            for (Student student : students)
            {
                if(student.getCompletedCourses().contains(this.course) && this.course.getGrade(student)>=9)
                {
                    System.out.println("Name: " + student.getName() + ", Email: " + student.getEmail()+ ", Grade: "+ String.valueOf(this.course.getGrade(student)));
                }
            }
        }
    }
    public void addTA(String email)
    {
        DataManager dataManager = DataManager.getInstance();
        LoginManager loginManager = LoginManager.getInstance();

        Student student;
        student = (Student)dataManager.getUserByEmail(email);
        if (student==null)
        {
            System.out.println("Student not found.");
            return;
        }
        if (student.isTa())
        {
            System.out.println(student.getName() + " is already a TA.");
            return;
        }
        if(student.getCompletedCourses().contains(this.course) && this.course.getGrade(student)>=9)
        {
            TA ta = new TA(student.getEmail(), student.getPassword(), student.getName(), student.getRollNo(), this.course);

            dataManager.addTA(ta);
            student.setTa(ta);

            System.out.println("Student " + ta.getName() + " is now a TA for course " + this.course.getCode() + ".");
        }
        else
        {
            System.out.println(student.getName()+ " is not eligible to be a TA for this course");
        }
    }
    // Method to remove a TA
    public void removeTA(String email) {
        DataManager dataManager = DataManager.getInstance();
        User user = dataManager.getUserByEmail(email); // Use User here, not TA directly

        if (!(user instanceof TA)) {
            System.out.println("User with email " + email + " is not a TA.");
            return;
        }

        TA ta = (TA) user; // Now safe to cast
        List<TA> taList = dataManager.getTAsByCourse(this.course);

        if (taList.contains(ta)) {
            dataManager.removeTA(ta);
            System.out.println("TA " + ta.getName() + " has been removed.");
        } else {
            System.out.println("TA not found or not assigned to your course.");
        }
    }


    // Method to show all TAs
    public void viewAllTAs()
    {
        List<TA> taList = DataManager.getInstance().getTAsByCourse(this.course);

        if (taList.isEmpty())
        {
            System.out.println("No TAs are assigned to your course.");
        }
        else
        {
            System.out.println("List of TAs assigned to your course:");
            for (TA ta : taList)
            {
                System.out.println("TA Name: " + ta.getName() + ", Email: "+ ta.getEmail());
            }
        }
    }
}
