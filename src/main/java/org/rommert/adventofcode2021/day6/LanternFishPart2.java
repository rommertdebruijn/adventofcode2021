package org.rommert.adventofcode2021.day6;

import org.rommert.adventofcode2021.InputParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LanternFishPart2 {

//  private static final String INPUT = "2,1,1,1,1,1,1,5,1,1,1,1,5,1,1,3,5,1,1,3,1,1,3,1,4,4,4,5,1,1,1,3,1,3,1,1,2,2,1,1,1,5,1,1,1,5,2,5,1,1,2,1,3,3,5,1,1,4,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,4,1,5,1,2,1,1,1,1,5,1,1,1,1,1,5,1,1,1,4,5,1,1,3,4,1,1,1,3,5,1,1,1,2,1,1,4,1,4,1,2,1,1,2,1,5,1,1,1,5,1,2,2,1,1,1,5,1,2,3,1,1,1,5,3,2,1,1,3,1,1,3,1,3,1,1,1,5,1,1,1,1,1,1,1,3,1,1,1,1,3,1,1,4,1,1,3,2,1,2,1,1,2,2,1,2,1,1,1,4,1,2,4,1,1,4,4,1,1,1,1,1,4,1,1,1,2,1,1,2,1,5,1,1,1,1,1,5,1,3,1,1,2,3,4,4,1,1,1,3,2,4,4,1,1,3,5,1,1,1,1,4,1,1,1,1,1,5,3,1,5,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,5,1,1,1,1,1,1,1,1,5,1,4,4,1,1,1,1,1,1,1,1,3,1,3,1,4,1,1,2,2,2,1,1,2,1,1";
  private static final String INPUT = "3,4,3,1,2";
  private static final int NR_OF_DAYS = 80;

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

    List<Integer> result = new ArrayList<>(input);
    for (int days = 0; days<NR_OF_DAYS; days++) {
      int moduloDay = days%7;
      //System.out.println("==== DAY " + days + " (modulo6 = " + moduloDay + ") =====");

      long nrOfFishSpawningToday = moduloMap.get(moduloDay);
      //System.out.println("There are " + nrOfFishSpawningToday + " adult fish spawning offspring today");

      long nrOfFishSpawningForTheFirstTime = result.stream()
          .filter(number -> number.equals(0))
          .count(); // These are only "virgin" fish that reach 0 for the first time
      //System.out.println("There are " + nrOfFishSpawningForTheFirstTime + " fish spawning for the first time");

      moduloMap.put(moduloDay, nrOfFishSpawningToday + nrOfFishSpawningForTheFirstTime); // They will be added to the modulo map...
      //System.out.println("This is modulo map: " + moduloMap);

      result = result.stream()
          .filter(number -> !number.equals(0))
          .collect(Collectors.toList()); //.. and removed from the result list

      result = calculateNextDay(result); // result will only be calculated over fish that never reached 0
      //System.out.println("Adult fish are moved to modulo map. For the remaining fish, the next day is: " + result);

      for (int newEntry=0;newEntry<(nrOfFishSpawningToday + nrOfFishSpawningForTheFirstTime);newEntry++) {
        result.add(8); // dont forget to add some new fish for each fish that reached 0 for the first time
      }
      //System.out.println("After adding new spawn, result for the next day is: " + result);

      long totalFish = moduloMap.values().stream().reduce(0L, Long::sum) + result.size();
      System.out.println("On day " + days + ": total nr of fish: " + totalFish + "\n\n");
    }
  }

  private static List<Integer> calculateNextDay(List<Integer> input) {
    List<Integer> result = new ArrayList<>(input);
    result = result.stream()
        .map(LanternFishPart2::mapToNextState)
        .collect(Collectors.toList());

    return result;
  }

  private static Integer mapToNextState(Integer input) {
    return input-1;
  }
}
