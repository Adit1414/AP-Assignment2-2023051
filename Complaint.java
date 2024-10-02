public class Complaint {
    private String description;
    private String date;
    private String status;
    private String studentName;
    private String studentEmail;
    private int studentRollNo;

    public Complaint(String description, String date) {
        this.description = description;
        this.date = date;
        this.status = "Pending";
        this.studentName = "";
        this.studentEmail = "";
        this.studentRollNo = 0;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        if(status.equals("Pending") || status.equals("Resolved"))
        {
            this.status = status;
        }
        else
        {
            System.out.println("Invalid status");
        }
    }

    public String getStudentName() {
        return this.studentName;
    }   

    public void setStudentName(String name) {
        this.studentName = name;
    }

    public String getStudentEmail() {
        return this.studentEmail;
    }

    public void setStudentEmail(String email) {
        this.studentEmail = email;
    }

    public int getStudentRollNo() {
        return this.studentRollNo;
    }

    public void setStudentRollNo(int rollno) {
        this.studentRollNo = rollno;
    }
    
    @Override
    public String toString() {
        return "Student Issue: \n" + "Date: '" + this.date + '\'' + ", Status: '" + this.status + '\'' + ", Name: '" + this.studentName + '\'' + ", Email: '" + this.studentEmail + '\'' + "\n Description: " + this.description + ".";
    }
}
