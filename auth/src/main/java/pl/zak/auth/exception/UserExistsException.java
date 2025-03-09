package pl.zak.auth.exception;

import org.springframework.security.core.AuthenticationException;

public class UserExistsException extends AuthenticationException {
    public UserExistsException(String msg) {
        super(msg);
    }
}