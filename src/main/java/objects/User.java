package objects;

import java.util.Objects;
import java.util.Set;

/**
 * User class
 */
public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private int rating;
    private Set<String> completedQuests;

    public User(String firstName, String lastName, String userName, String email, String password, int rating, Set<String> completedQuests) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.rating = rating;
        this.completedQuests = completedQuests;
    }

    public User(String firstName, String lastName, String userName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<String> getCompletedQuests() {
        return completedQuests;
    }

    public void setCompletedQuests(Set<String> completedQuests) {
        this.completedQuests = completedQuests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return rating == user.rating &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                userName.equals(user.userName) &&
                email.equals(user.email) &&
                password.equals(user.password) &&
                completedQuests.equals(user.completedQuests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, email, password, rating, completedQuests);
    }
}
