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
    public User login(String email, String password) throws InvalidLoginException {
        if (!this.credentials.containsKey(email)) {
            throw new InvalidLoginException("Email not found. Please sign up.");
        }
        if (!this.credentials.get(email).equals(password)) {
            throw new InvalidLoginException("Invalid password.");
        }

        User loggedInUser = users.get(email);
        System.out.println(loggedInUser.getName() + " logged in successfully.");
        
        return loggedInUser;
    }
}