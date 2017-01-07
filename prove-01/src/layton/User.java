package layton;

public class User {

    private String password;
    private String salt;
    private String hash;

    /**
     * Constructor: sets the initial password for the user.
     */
    public User(String password) {
        this.password = password;
    }

    /**
     * Getter for password
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter for password
     * @param p - the password
     */
    public void setPassword(String p) {
        this.password = p;
    }

    /**
     * Getter for salt
     * @return salt
     */
    public String getSalt() {
        return this.salt;
    }

    /**
     * Setter for salt
     * @param s - the salt
     */
    public void setSalt(String s) {
        this.salt = s;
    }

    /**
     * Getter for hash
     * @return hash
     */
    public String getHashedPassword() {
        return this.hash;
    }

    /**
     * Setter for hash
     * @param h - the hash
     */
    public void setHashedPassword(String h) {
        this.hash = h;
    }
}
