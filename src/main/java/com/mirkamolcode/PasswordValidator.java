package com.mirkamolcode;

import java.util.function.Predicate;

public class PasswordValidator implements Predicate<String> {

    public static final int MIN_PASSWORD_CHAR_LENGTH = 8;
    public static final String DIGIT_REGEX = ".*\\d.*";
    public static final String SPECIAL_CHARACTER_REGEX = ".*[!@#$%^&*()_+=<>?/\\\\[\\\\]{}|].*";

    @Override
    public boolean test(String password) {
        if (password == null || password.trim().isBlank()) {
            return false;
        }
        password = password.trim();

        if (password.length() < MIN_PASSWORD_CHAR_LENGTH) {
            return false;
        }

        if (!password.matches(DIGIT_REGEX)){
            return false;
        }

        if (!password.matches(SPECIAL_CHARACTER_REGEX)){
            return false;
        }
        return true;
    }
}
