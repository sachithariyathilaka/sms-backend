package io.github.sachithariyathilaka.config;

import java.io.Serializable;

/**
 * Jwt response class.
 *
 * @author	Sachith Ariyathilaka
 * @version 1.0.0
 * @since 	2024/12/06
 */
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;

    }
    public String getToken() {
        return this.jwttoken;
    }
}
