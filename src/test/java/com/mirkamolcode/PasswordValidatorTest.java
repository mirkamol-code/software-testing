package com.mirkamolcode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordValidatorTest {
    private final PasswordValidator underTest = new PasswordValidator();

    @Test
    void willFailIfPasswordIsNull() {
        // given
        String password = null;
        // when
        var actual = underTest.test(password);
        // then
        assertThat(actual).isFalse();
    }

    @Test
    void willFailIfPasswordIsEmpty() {
        // given
        String password = "";
        // when
        var actual = underTest.test(password);
        // then
        assertThat(actual).isFalse();
    }

    @Test
    void willFailIfPasswordHasEmptySpaces() {
        // given
        String password = "    ";
        // when
        var actual = underTest.test(password);
        // then
        assertThat(actual).isFalse();
    }

    @Test
    void willFailIfPasswordDoesNotMeetMinimumLengthRequiredAfterTrimming() {
        // given
        String password = "    pass";
        // when
        var actual = underTest.test(password);
        // then
        assertThat(actual).isFalse();
    }

    @Test
    void willFailIfPasswordDoesContainsAtLeastOneDigit() {
        // given
        String password = "    password";
        // when
        var actual = underTest.test(password);
        // then
        assertThat(actual).isFalse();
    }

    @Test
    void willFailIfPasswordDoesContainsAtLeastOneSpecialChar() {
        // given
        String password = "    password1";
        // when
        var actual = underTest.test(password);
        // then
        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @CsvSource({
            "password!, false",
            "password@, false",
            "password1#, true",
            "password1$, true",
            "password1^, true",
            "password1&, true",
            "password1*, true",
            "password1), true",
            "password1(, true",
            "password1_, true",
            "password1>, true",
            "password1<, true",
            "password1@[, true",
            "password1@], true",
            "password1{, true",
            "password1}, true",
            "password1~, false",
    })
    void canValidateEspecialCharacters(String password, boolean expected) {
        if(password.equals("null")) password = null;
        // when
        var actual = underTest.test(password);
        // then
        assertThat(actual).isEqualTo(expected);
    }
}
