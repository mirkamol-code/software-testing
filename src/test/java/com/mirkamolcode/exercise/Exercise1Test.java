package com.mirkamolcode.exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class Exercise1Test {
    private final Exercise1 underTest = new Exercise1();

    @ParameterizedTest
    @CsvSource({
            "92, A",
            "40, F",
            "57, E",
            "60, D",
            "77, C",
            "86, B",
    })
    void canGetCorrectGrade(int grade, String expectedGrade) {
        // when
        String actual = underTest.getGrade(grade);
        // then
        assertThat(actual).isEqualTo(expectedGrade);
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "101",
            "-101",
    })
    void willThrowWhenInvalidGrade(int grade) {
        assertThatThrownBy(() -> underTest.getGrade(grade))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Score must be between 0 and 100");
    }


    @ParameterizedTest
    @CsvSource({
            "aaa, 3",
            "abc, 1",
            "'', 0",
            "-, 0",
            "aaaaaaaaaaaaaaaa, 16"
    })
    void canCountVowels(String name, int expected) {
        // when
        int actual = underTest.countVowels(name);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "S1234, true",
            "S, false",
            "S12345, false",
            "S005, false",
            "S0005, true",
            "s0005, false",
            "B0015, false",
    })
    void canValidateStudentId(String studentId, boolean expected) {
        // when
        boolean actual = underTest.isValidStudentId(studentId);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canCalculateAverageValidNumberOfList() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        // when
        double actual = underTest.calculateAverage(numbers);
        // then
        assertThat(actual).isEqualTo(3.0);
    }

    @Test
    void canCalculateAverageEmptyList() {
        // given
        List<Integer> numbers = List.of();
        // when
        double actual = underTest.calculateAverage(numbers);
        // then
        assertThat(actual).isEqualTo(0.0);
    }

    @Test
    void canCalculateAverageWhenNull() {
        // given
        List<Integer> numbers = null;
        // when
        double actual = underTest.calculateAverage(numbers);
        // then
        assertThat(actual).isEqualTo(0.0);
    }

    @ParameterizedTest
    @CsvSource({
            "Ensieh Rahbar, erahbar",
            "Yusuf Kaya, ykaya",
            "'', ''",
            "sdsddaf, ''"
    })
    void canGenerateUsernameFromFullName(String fullName, String expected) {
        // when
        String actual = underTest.generateUsername(fullName);
        // then
        assertThat(actual).isEqualTo(expected);

    }

    @ParameterizedTest
    @CsvSource({
            "70",
            "60",
            "20",
            "100",
    })
    void canGetTopStudents(int threshold) {
        // given
        List<Student> students = List.of(
                new Student("Ensieh", 85),
                new Student("Jamila", 50),
                new Student("Ali", 60),
                new Student("Javohir", 90),
                new Student("Yusuf", 78),
                new Student("Franco", 83)
        );
        // when
        List<Student> actual = underTest.getTopStudents(students, threshold);
        // then
        List<Student> expected = students.stream().filter(student -> student.score() <= threshold)
                .sorted((a, b) -> Integer.compare(b.score(), a.score()))
                .toList();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canCheckDuplicateNames() {
        // given
        List<Student> students = List.of(
                new Student("Ensieh", 85),
                new Student("Ensieh", 70),
                new Student("Ali", 60),
                new Student("Javohir", 90),
                new Student("Yusuf", 78),
                new Student("Franco", 83)
        );
        // when
        boolean actual = underTest.hasDuplicateNames(students);
        // then
        assertThat(actual).isTrue();
    }

    @Test
    void canReverseCourseList() {
        // given
        List<String> courses = List.of("Math", "Music", "Science");
        // when
        List<String> actual = underTest.reverseCourses(courses);
        // then
        List<String> expected = new ArrayList<>(courses);
        Collections.reverse(expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void canReturnANewArrayListWhenListIsEmpty() {
        // given
        List<String> courses = List.of();
        // when
        List<String> actual = underTest.reverseCourses(courses);
        // then
        assertThat(actual).isEmpty();
    }

    @ParameterizedTest
    @CsvSource({
            "90, true",
            "10, false",
            "49, false",
            "51, true",
            "50, true",
            "-1, false",

    })
    void canValidateScore(int score, boolean expected) {
        // when
        boolean actual = underTest.hasPassed(score);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "true, Star Student",
            "false, Needs Improvement",
    })
    void canAssignBadgeBasedOnIsHelpfulOrNot(boolean isHelpfull, String expectedBadge) {
        // when
        String actual = underTest.assignBadge(isHelpfull);
        // then
        assertThat(actual).isEqualTo(expectedBadge);
    }
}