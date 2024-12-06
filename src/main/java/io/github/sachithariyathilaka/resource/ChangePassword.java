package io.github.sachithariyathilaka.resource;

/**
 * Change password resource class.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
public class ChangePassword {
    private int id;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
