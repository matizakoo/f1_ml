package pl.zak.auth.utils;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerEndpoints {
    public static final String GUEST = "/guest";
    public static final String USER = "/user";
    public static final String ADMIN = "/admin";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String USERS = "/users";
}