package org.rommert.adventofcode2021.day6;

import org.rommert.adventofcode2021.InputParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LanternFishPart2 {

  private static final String INPUT = "2,1,1,1,1,1,1,5,1,1,1,1,5,1,1,3,5,1,1,3,1,1,3,1,4,4,4,5,1,1,1,3,1,3,1,1,2,2,1,1,1,5,1,1,1,5,2,5,1,1,2,1,3,3,5,1,1,4,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,4,1,5,1,2,1,1,1,1,5,1,1,1,1,1,5,1,1,1,4,5,1,1,3,4,1,1,1,3,5,1,1,1,2,1,1,4,1,4,1,2,1,1,2,1,5,1,1,1,5,1,2,2,1,1,1,5,1,2,3,1,1,1,5,3,2,1,1,3,1,1,3,1,3,1,1,1,5,1,1,1,1,1,1,1,3,1,1,1,1,3,1,1,4,1,1,3,2,1,2,1,1,2,2,1,2,1,1,1,4,1,2,4,1,1,4,4,1,1,1,1,1,4,1,1,1,2,1,1,2,1,5,1,1,1,1,1,5,1,3,1,1,2,3,4,4,1,1,1,3,2,4,4,1,1,3,5,1,1,1,1,4,1,1,1,1,1,5,3,1,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,1,1,1,1,1,1,1,1,5,1,4,4,1,1,1,1,1,1,1,1,3,1,3,1,4,1,1,2,2,2,1,1,2,1,1";
  private static final int NR_OF_DAYS = 256;

  private static final Map<Integer, Long> moduloMap = new HashMap<>();
  static {
    moduloMap.put(0, 0L);
    moduloMap.put(1, 0L);
    moduloMap.put(2, 0L);
    moduloMap.put(3, 0L);
    moduloMap.put(4, 0L);
    moduloMap.put(5, 0L);
    moduloMap.put(6, 0L);
  }

  public static void main(String[] args) {
    List<Integer> input = new InputParser<Integer>(",").convertInput(INPUT, Integer::parseInt);

    for (Integer fish : input) {
      moduloMap.put(fish, moduloMap.get(fish) + 1L);
    }

    long babies = 0L;
    long premature = 0L;

    for (int days = 0; days<NR_OF_DAYS; days++) {
      int moduloDay = days%7; // lets see what the spawn cycle is
      long nrOfFishSpawningToday = moduloMap.get(moduloDay); // how many fish are spawning today?

      moduloMap.put(moduloDay, nrOfFishSpawningToday + premature); // fish that are 2 days old, are added to the 7 day spawn cycle. They will spawn for the first time in 7 days

      // This takes care of the first 2 days of maturing
      premature = babies; // babies grow up to be prematures
      babies = nrOfFishSpawningToday; // newborns, yay

      Long nrOfAdultsSpawning = moduloMap.values().stream().reduce(0L, Long::sum);
      long totalFish = babies + premature + nrOfAdultsSpawning;

      System.out.println("On day " + days + ": total nr of fish: " + totalFish + "\n\n");
    }
  }
}
