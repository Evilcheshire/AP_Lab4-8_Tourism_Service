package tourism_app.users;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements Serializable {
    private final int ID;
    private final UserType userType;
    private String name;
    private String passwordHash;
    private String userHashKey;

    public User(int id, UserType userType, String name, String password) {
        this.ID = id;
        this.userType = userType;
        this.name = name;
        this.passwordHash = Integer.toHexString(password.hashCode());
        this.userHashKey = generateHashKey(name, this.passwordHash);
    }

    private String generateHashKey(String name, String passwordHash) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String combined = name + passwordHash;
            byte[] hash = digest.digest(combined.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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

    public String getUserHashKey() {
        return userHashKey;
    }

    public boolean authenticate(String password) {
        return this.passwordHash.equals(Integer.toHexString(password.hashCode()));
    }

    public void setName(String newName) {
        this.name = newName;
        this.userHashKey = generateHashKey(newName, this.passwordHash);
    }

    public void setPassword(String password){
        this.passwordHash = Integer.toHexString(password.hashCode());
    }
}

