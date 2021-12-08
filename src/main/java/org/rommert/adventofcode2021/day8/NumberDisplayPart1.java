package org.rommert.adventofcode2021.day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberDisplayPart1 {

  private static final String input = "acedgfb cdfbe gcdfa fbcad dab cdfgeb cefabd eafb cagedb ab | cdfeb fcadb cdfeb cdbaf";

  public static void main(String[] args) {
    List<String> signals = getSignalsFromInput(input);
    List<String> secretNumbers = getSecretNumbersFromInput(input);

    Map<String, Integer> numbers = determineNumbers(signals);

    System.out.println(secretNumbers.stream()
        .map(NumberDisplayPart1::sortSignal)
        .map(numbers::get)
        .collect(Collectors.toList()));
  }

  private static List<String> getSignalsFromInput(String fullInput) {
    String signalsString = fullInput.split(" \\| ")[0];
    return Arrays.asList(signalsString.split(" "));
  }

  private static List<String> getSecretNumbersFromInput(String fullInput) {
    String signalsString = fullInput.split(" \\| ")[1];
    return Arrays.asList(signalsString.split(" "));
  }

  private static Map<String, Integer> determineNumbers(List<String> signals) {
    Map<String, Integer> knownNumbers = new HashMap<>();
    Map<Integer, List<String>> unknownSignals = new HashMap<>();

    for (String signal : signals) {
      List<String> currentListForLength = unknownSignals.getOrDefault(signal.length(), new ArrayList<>());
      currentListForLength.add(signal);
      unknownSignals.put(signal.length(), currentListForLength);
    }

    // some lengths only have 1 nr
    String signal1 = unknownSignals.get(2).get(0);
    knownNumbers.put(sortSignal(signal1), 1);

    String signal4 = unknownSignals.get(4).get(0);
    knownNumbers.put(sortSignal(signal4), 4);

    String signal7 = unknownSignals.get(3).get(0);
    knownNumbers.put(sortSignal(signal7), 7);

    String signal8 = unknownSignals.get(7).get(0);
    knownNumbers.put(sortSignal(signal8), 8);

    // nr9 is the only 6-char signal that contains all letters of the nr4 signal
    String signal9 = findSignalThatContainsOtherSignal(unknownSignals.get(6), signal4);
    knownNumbers.put(sortSignal(signal9), 9);
    unknownSignals.get(6).remove(signal9);

    // nr3 is the only 5-char signal that contains all letters of the nr7 signal
    String signal3 = findSignalThatContainsOtherSignal(unknownSignals.get(5), signal7);
    knownNumbers.put(sortSignal(signal3), 3);
    unknownSignals.get(5).remove(signal3);

    for (String signalWith5Char : unknownSignals.get(5)) {
      String signalThatContainsOtherSignal = findSignalThatContainsOtherSignal(unknownSignals.get(6), signalWith5Char);
      if (signalThatContainsOtherSignal != null) {
        // The nr6 signal is the only 6-char signal that contains all chars of another 5-char signal. And that 5-char signal is nr5
        String signal6 = signalThatContainsOtherSignal;
        String signal5 = signalWith5Char;

        knownNumbers.put(sortSignal(signal5), 5);
        knownNumbers.put(sortSignal(signal6), 6);

        unknownSignals.get(6).remove(signal6); // remove these...
        unknownSignals.get(5).remove(signal5); // ... so we know which numbers are left
      }
    }

    String signal2 = unknownSignals.get(5).get(0); // nr2 is the the only 5-char signal left
    knownNumbers.put(sortSignal(signal2), 2);

    String signal0 = unknownSignals.get(6).get(0); // nr0 is the the only 6-char signal left
    knownNumbers.put(sortSignal(signal0), 0);

    return knownNumbers;
  }

  private static String findSignalThatContainsOtherSignal(List<String> containingSignals, String targetSignal) {
    for (String signal : containingSignals) {
      boolean match = true;
      for (int i=0;i<targetSignal.length();i++) {
        if (!signal.contains(targetSignal.charAt(i) + "")) {
          match = false;
          break;
        }
      }

      if (match) {
        return signal;
      }
    }
    return null;
  }

  private static String sortSignal(String signal) {
    char[] temp = signal.toCharArray();
    Arrays.sort(temp);
    return new String(temp);
  }
}
