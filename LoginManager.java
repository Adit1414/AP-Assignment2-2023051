import java.util.HashMap;
import java.util.Map;

public class LoginManager {

    private static LoginManager instance = null;
    private Map<String, String> credentials;
    private Map<String, User> users;

    public LoginManager() {
        this.credentials = new HashMap<>();
        this.users = new HashMap<>();
    }

    // Singleton access method
    public static LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public void signUp(User user) {
        if (credentials.containsKey(user.getEmail())) {
            System.out.println("User already registered with this email.");
            return;
        }
        
        // Store credentials and map to the user
        credentials.put(user.getEmail(), user.getPassword());
        users.put(user.getEmail(), user);
        
        System.out.println("User " + user.getName() + " registered successfully!");
    }

    // Method for login
    public User login(String email, String password) {
        if (!this.credentials.containsKey(email)) {
            System.out.println("Email not found. Please Sign Up.");
            return null;
        }
        if (!this.credentials.get(email).equals(password)) {
            System.out.println("Invalid password.");
            return null;
        }

        User loggedInUser = users.get(email);
        System.out.println(loggedInUser.getName() + " logged in successfully.");
        
        return loggedInUser;
    }

    public void removeUser(User user) {
        if (users.containsKey(user.getEmail())) {
            users.remove(user.getEmail());
            System.out.println("User " + user.getName() + " removed from login system.");
        } else {
            System.out.println("User not found in login system.");
        }
    }

    // Method to list all registered users
    public void listRegisteredUsers() {
        for (String email : users.keySet()) {
            System.out.println("Registered User: " + users.get(email).getName());
        }
    }
}
