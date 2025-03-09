package pl.zak.auth.utils;

import org.springframework.stereotype.Service;

@Service
public class ValidatorUtils {
    public static final String NO_WHITE_SPACE = "^[^\\s]*$";
    public static final String AT_LEAST_UPPER_LETTER_AND_LOWER_LETTER = "^(?=.*[A-Za-z])(?=.*\\d).+$\n";
    public static final String UPPER_AND_LOWER_LETTER = "^(?=.*[A-Z]).*$\n";
}