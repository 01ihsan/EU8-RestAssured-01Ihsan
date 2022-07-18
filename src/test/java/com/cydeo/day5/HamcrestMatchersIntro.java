package com.cydeo.day5;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {
    @Test
    public void simpleTest() {
        assertThat(5 + 5, is(10));
        assertThat(5 + 5, equalTo(10));
        assertThat(5 + 5, not(9));

    }

    @Test
    public void stringHamcrest() {
        String text = "EU8 is learning Hamcrest";
        assertThat(text, is("EU8 is learning Hamcrest"));
        assertThat(text, startsWith("EU8"));
        assertThat(text, startsWithIgnoringCase("eu8"));
        assertThat(text, endsWith("rest"));
        assertThat(text, containsStringIgnoringCase("IS LEa"));
    }
}