package com.learnjava.parallelstream;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListSpliteratorExampleTest {

    static LinkedListSpliteratorExample example;

    @BeforeAll
    static void setUp() {
        example = new LinkedListSpliteratorExample();
    }

    @RepeatedTest(5)
    void multiplyEachSequential() {
        LinkedList<Integer> input = DataSet.generateIntegerLinkedList(1000000);
        List<Integer> output = example.multiplyEach(input, 3, false);
        assertEquals(output.size(), input.size());
    }

    @RepeatedTest(5)
    void multiplyEachParallel() {
        LinkedList<Integer> input = DataSet.generateIntegerLinkedList(1000000);
        List<Integer> output = example.multiplyEach(input, 3, true);
        assertEquals(output.size(), input.size());
    }
}