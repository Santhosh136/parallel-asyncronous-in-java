package com.learnjava.parallelstream;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListSpliteratorExampleTest {

    static ArrayListSpliteratorExample example;

    @BeforeAll
    static void setUp() {
        example = new ArrayListSpliteratorExample();
    }

    @RepeatedTest(5)
    void multiplyEachSequential() {
        ArrayList<Integer> input = DataSet.generateArrayList(1000000);
        List<Integer> output = example.multiplyEach(input, 3, false);
        assertEquals(output.size(), input.size());
    }

    @RepeatedTest(5)
    void multiplyEachParallel() {
        ArrayList<Integer> input = DataSet.generateArrayList(1000000);
        List<Integer> output = example.multiplyEach(input, 3, true);
        assertEquals(output.size(), input.size());
    }
}