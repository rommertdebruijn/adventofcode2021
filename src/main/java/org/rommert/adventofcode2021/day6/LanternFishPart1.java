package org.rommert.adventofcode2021.day6;

import org.rommert.adventofcode2021.InputParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LanternFishPart1 {

//  private static final String INPUT = "2,1,1,1,1,1,1,5,1,1,1,1,5,1,1,3,5,1,1,3,1,1,3,1,4,4,4,5,1,1,1,3,1,3,1,1,2,2,1,1,1,5,1,1,1,5,2,5,1,1,2,1,3,3,5,1,1,4,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,4,1,5,1,2,1,1,1,1,5,1,1,1,1,1,5,1,1,1,4,5,1,1,3,4,1,1,1,3,5,1,1,1,2,1,1,4,1,4,1,2,1,1,2,1,5,1,1,1,5,1,2,2,1,1,1,5,1,2,3,1,1,1,5,3,2,1,1,3,1,1,3,1,3,1,1,1,5,1,1,1,1,1,1,1,3,1,1,1,1,3,1,1,4,1,1,3,2,1,2,1,1,2,2,1,2,1,1,1,4,1,2,4,1,1,4,4,1,1,1,1,1,4,1,1,1,2,1,1,2,1,5,1,1,1,1,1,5,1,3,1,1,2,3,4,4,1,1,1,3,2,4,4,1,1,3,5,1,1,1,1,4,1,1,1,1,1,5,3,1,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,1,1,1,1,1,1,1,1,5,1,4,4,1,1,1,1,1,1,1,1,3,1,3,1,4,1,1,2,2,2,1,1,2,1,1";
  private static final String INPUT = "1";
  private static final int NR_OF_DAYS = 20;

  public static void main(String[] args) {
    List<Integer> input = new InputParser<Integer>(",").convertInput(INPUT, Integer::parseInt);

    List<Integer> result = new ArrayList<>(input);
    for (int days = 0; days<NR_OF_DAYS; days++) {
      result = calculateNextDay(result);
      System.out.println("day " + days + ": " + result);
    }
    System.out.println("After " + NR_OF_DAYS + " days, there are " + result.size() + " lanternfish");
  }

  private static List<Integer> calculateNextDay(List<Integer> input) {
    List<Integer> result = new ArrayList<>(input);
    long nrOfNewEntries = input.stream()
        .filter(number -> number.equals(0))
        .count();
    result = result.stream()
        .map(LanternFishPart1::mapToNextState)
        .collect(Collectors.toList());

    for (int newEntry = 0; newEntry < nrOfNewEntries; newEntry++) {
      result.add(8);
    }
    return result;
  }

  private static Integer mapToNextState(Integer input) {
    if (input.equals(0)) {
      return 6;
    }
    return input-1;
  }
}
