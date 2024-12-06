package io.github.sachithariyathilaka.resource;

/**
 * User return resource class.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
public class UserReturn {
    private String token;
    private int id;

    public UserReturn(String token, int id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
