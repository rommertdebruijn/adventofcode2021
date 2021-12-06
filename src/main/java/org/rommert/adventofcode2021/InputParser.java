package org.rommert.adventofcode2021;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InputParser<T> {

    private String inputSeparator;

    public InputParser() {
        this("\n");
    }

    public InputParser(String inputSeparator) {
        this.inputSeparator = inputSeparator;
    }

    // Expects a String of \n-separated input strings, and a converter to transform
    // each of those input Strings to a T java class, returning the list of all T input
    public List<T> convertInput(String totalInputString, Function<String, T> converterMethod) {
        return Arrays.stream(totalInputString.split(inputSeparator))
                .map(converterMethod)
                .collect(Collectors.toList());
    }

}
