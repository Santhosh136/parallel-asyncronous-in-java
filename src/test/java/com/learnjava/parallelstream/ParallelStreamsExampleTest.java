package com.learnjava.parallelstream;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.learnjava.util.CommonUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class ParallelStreamsExampleTest {

    private static ParallelStreamsExample parallelStreamsExample;

    @BeforeAll
    static void setUp() {
        parallelStreamsExample = new ParallelStreamsExample();
    }

    @Test
    void shouldTransform() {
        List<String> input = DataSet.namesList();

        startTimer();
        List<String> result = parallelStreamsExample.transform(input);
        timeTaken();

        assertEquals(input.size(), result.size());
        result.forEach(s -> assertTrue(s.contains("-")));

    }

    @Test
    void shouldTransformToLowerCase() {
        List<String> input = DataSet.namesList();

        startTimer();
        List<String> result = parallelStreamsExample.transformToLowerCase(input);
        timeTaken();

        assertEquals(input.size(), result.size());
        result.forEach(s -> assertEquals(s.toLowerCase(), s));
    }


    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void shouldTransformConditionally(boolean isParallel) {
        List<String> input = DataSet.namesList();

        startTimer();
        List<String> result = parallelStreamsExample.transformConditionally(input, isParallel);
        timeTaken();
        resetTimer();

        assertEquals(4, result.size());
        result.forEach(s -> assertTrue(s.contains("-")));

    }
}