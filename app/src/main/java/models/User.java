package models;

/**
 * Created by Jorge on 01/03/2018.
 */

public class User {

    private String fullName;
    private String userName;

    public User(String fullName, String userName) {
        this.fullName = fullName;
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }
}
