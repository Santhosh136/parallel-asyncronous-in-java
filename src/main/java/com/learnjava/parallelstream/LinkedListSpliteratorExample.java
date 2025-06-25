package com.learnjava.parallelstream;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.*;

public class LinkedListSpliteratorExample {

    public List<Integer> multiplyEach(LinkedList<Integer> input, int value, boolean isParallel) {
        Stream<Integer> inputStream = input.stream();
        if (isParallel) inputStream = inputStream.parallel();

        startTimer();
        List<Integer> output = inputStream.map(i -> i * value)
                .toList();
        timeTaken();
        resetTimer();

        return output;
    }

}
