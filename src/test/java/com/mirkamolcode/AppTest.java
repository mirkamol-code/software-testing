package com.mirkamolcode;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class AppTest {
    @Test
    @Disabled
    void myFirstTest(){

    }

    @EnabledOnOs(OS.MAC)
    @RepeatedTest(10)
    void mySecondTest(){

    }
}
