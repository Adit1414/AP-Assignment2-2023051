public class TA extends Student{
    private Course course;
    public TA(String email, String password, String name, int rollNo, Course course)
    {
        super(email, password, name, rollNo);
        this.course=course;
    }

    public Course getCourse() {
        return course;
    }

    public void viewAssignedCourse()
    {
        if (course == null)
        {
            System.out.println("Not assigned to any course.");
            return;
        }
        System.out.println("Course Title: "+ course.getTitle());
        System.out.println("Course Code: "+ course.getCode());
        System.out.println("Professor Name: "+ course.getProfessorName());
        System.out.printf("Max Enrolments: %d\n", course.getMaxEnrolments());
    }

    public void viewStudents()
    {
        if (course == null)
        {
            System.out.println("TA is not assigned to any course.");
            return;
        }
        for(Student student: this.course.getStudents())
        {
            System.out.println("Name: "+ student.getName()+ ", Email: " + student.getEmail()+ ", Grade: "+ String.valueOf(this.course.getGrade(student)));
        }
    }
    public void assignGrade(String email, int grade)
    {
        Student student = (Student)DataManager.getInstance().getUserByEmail(email);
        if (course == null)
        {
            System.out.println("TA is not assigned to any course.");
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
        if (this.course.getStudents().contains(student))
        {
            this.course.setGrade(student, grade);
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
}