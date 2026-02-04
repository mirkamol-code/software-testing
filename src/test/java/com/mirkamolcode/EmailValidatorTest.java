package com.mirkamolcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class EmailValidatorTest {
    private final EmailValidator underTest = new EmailValidator();

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvSource({
            "hello@amigos.com, true",
            "nelson+foo@amigos.com, true",
            "nelson, false",
            "helloamigos@com, false",
            "helloamigos.com, false",
            "'', false",
    })
    void canValidateEmail(String email, boolean expected) {
        // when
        var actual = underTest.test(email);
        // then
        assertThat(actual).isEqualTo(expected);
    }
}