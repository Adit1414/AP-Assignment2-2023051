import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collection;

public class DataManager {
    // Singleton instance
    private static DataManager instance = null;

    // Data Storage
    private List<Course> courseList;
    private List<TA> taList;
    private Map<String, String> userCredentials;  // Stores email and password
    private Map<String, Student> studentData;     // Maps email to Student object
    private Map<String, Professor> professorData; // Maps email to Professor object
    private Map<String, Admin> adminData;         // Maps email to Admin object

    private DataManager() {
        courseList = new ArrayList<>();
        userCredentials = new HashMap<>();
        studentData = new HashMap<>();
        professorData = new HashMap<>();
        adminData = new HashMap<>();
        taList=new ArrayList<>();
    }

    // Get the singleton instance
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void viewTaList()
    {
        System.out.println("List of all TAs: ");
        System.out.println(taList);
    }

    // Manage courses
    public void addCourse(Course course) {
        courseList.add(course);
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    // Manage user credentials
    public void addUserCredentials(String email, String password) {
        userCredentials.put(email, password);
    }

    public boolean verifyUserCredentials(String email, String password) {
        return userCredentials.containsKey(email) && userCredentials.get(email).equals(password);
    }

    // General method to add a User (Student, Professor, etc.)
    public <T extends User> void addUser(T user) {
        if (user instanceof Professor)
        {
            professorData.put(user.getEmail(), (Professor) user);
        }
        else if (user instanceof Admin)
        {
            adminData.put(user.getEmail(), (Admin) user);
        }
        else if (user instanceof Student)
        {
            studentData.put(user.getEmail(), (Student) user);
        }
        addUserCredentials(user.getEmail(), user.getPassword());
    }

    public User getUserByEmail(String email) 
    {
        if (professorData.containsKey(email))
        {
            return professorData.get(email);
        }
        else if (adminData.containsKey(email))
        {
            return adminData.get(email);
        }
        else if (studentData.containsKey(email))
        {
            return studentData.get(email);
        }
        return null; // User not found
    }
    public <T extends User> List<T> getAllUsers(Class<T> userType) {
        if (userType == Professor.class)
        {
            return new ArrayList<>((Collection<? extends T>) professorData.values());
        }
        else if (userType == Admin.class)
        {
            return new ArrayList<>((Collection<? extends T>) adminData.values());
        }
        else if (userType == Student.class)
        {
            return new ArrayList<>((Collection<? extends T>) studentData.values());
        }
        return new ArrayList<>(); // Empty list if type doesn't match
    }

    public void addTA(TA ta)
    {
        taList.add(ta);
        System.out.println("TA " + ta.getName() + " has been added to the system.");
    }
    public List<TA> getTAsByCourse(Course course)
    {
        List<TA> result = new ArrayList<>();
        for (TA ta : taList)
        {
            if (ta.getCourse().equals(course))
            {
                result.add(ta);
            }
        }
        return result;
    }
    public void removeTA(TA ta)
    {
        if (taList.remove(ta))
        {
            System.out.println("TA " + ta.getName() + " has been removed from the system.");
        }
        else
        {
            System.out.println("TA not found in the system.");
        }
    }
}
 