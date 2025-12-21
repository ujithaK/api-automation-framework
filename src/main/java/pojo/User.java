package pojo;

public class User {
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String phone;
    public String password;
    public int userStatus;

    public User(String username, String firstName, String lastName, String email, String phone, String password, int userStatus) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.userStatus = userStatus;
    }
}
