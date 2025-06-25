package com.learnjava.parallelstream;

import com.learnjava.util.DataSet;
import com.learnjava.util.LoggerUtil;

import java.util.List;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.*;

public class ParallelStreamsExample {
    public static void main(String[] args) {
        List<String> namesList = DataSet.namesList();

        ParallelStreamsExample parallelStreamsExample = new ParallelStreamsExample();

        startTimer();
        List<String> resultList = parallelStreamsExample.transform(namesList);
        LoggerUtil.log("Result " + resultList);
        timeTaken();
    }

    public List<String> transform(List<String> namesList) {
        return namesList
                .parallelStream()
                .map(ParallelStreamsExample::addNameLength)
                .toList();
    }

    public List<String> transformConditionally(List<String> namesList, boolean isParallel) {
        Stream<String> namesStream = namesList.stream();
        if (isParallel) namesStream.parallel();
        return namesStream
                .map(ParallelStreamsExample::addNameLength)
                .toList();
    }

    private static String addNameLength(String name) {
        delay(500);
        return name.length() + " - " + name;
    }

    private static String convertToLowerCase(String name) {
        delay(500);
        return name.toLowerCase();
    }

    public List<String> transformToLowerCase(List<String> namesList) {
        return namesList
                .parallelStream()
                .map(ParallelStreamsExample::convertToLowerCase)
                .toList();
    }
}
