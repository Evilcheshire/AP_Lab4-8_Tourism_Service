package tourism_app.users;

import java.io.Serializable;

public class User implements Serializable {
    private final int ID;
    private final UserType userType;
    private String name;
    private String passwordHash;

    public User(int id, UserType userType, String name, String password) {
        this.ID = id;
        this.userType = userType;
        this.name = name;
        this.passwordHash = hashPassword(password);
    }

    private String hashPassword(String password) {
        return Integer.toHexString(password.hashCode());
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }

    public boolean authenticate(String password) {
        return this.passwordHash.equals(hashPassword(password));
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setPassword(String password) {
        this.passwordHash = hashPassword(password);
    }
}