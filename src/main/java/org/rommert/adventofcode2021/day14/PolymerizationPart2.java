package org.rommert.adventofcode2021.day14;

import org.rommert.adventofcode2021.InputParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PolymerizationPart2 {

  private static final String input = "" +
      "NNCB\n" +
      "\n" +
      "CH -> B\n" +
      "HH -> N\n" +
      "CB -> H\n" +
      "NH -> C\n" +
      "HB -> C\n" +
      "HC -> B\n" +
      "HN -> C\n" +
      "NN -> C\n" +
      "BH -> H\n" +
      "NC -> B\n" +
      "NB -> B\n" +
      "BN -> B\n" +
      "BB -> N\n" +
      "BC -> B\n" +
      "CC -> N\n" +
      "CN -> C";
//
//  NNCB
//  After step 1: NCNBCHB
//  After step 2: NBCCNBBBCBHCB
//  After step 3: NBBBCNCCNBBNBNBBCHBHHBCHB

  private static final Map<String, String> rulesMap = new HashMap<>();

  public static void main(String[] args) {

    String startingPolymere = input.split("\n\n")[0];
    List<Rule> rules = new InputParser<Rule>().convertInput(input.split("\n\n")[1], PolymerizationPart2::convertToRule);
    rules.forEach(rule -> rulesMap.put(rule.leftChar + rule.rightChar, rule.newChar));

    Map<String, Long> polymereParts = breakInStartingParts(startingPolymere);
    System.out.println(polymereParts);
    for (int step=0;step<40;step++) {
      polymereParts = superGrowPolymere(polymereParts);
      System.out.println("end of step " + step + ", polymereParts = " + polymereParts);
    }


//
//    Map<String, Long> letterOccurences = getLetterOccurences(lastLetters);
//
//    Long max = letterOccurences.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(0L);
//    Long min = letterOccurences.entrySet().stream().min(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(0L);
//    System.out.println(max - min);
  }

  private static Map<String, Long> breakInStartingParts(String startingPolymere) {
    Map<String, Long> polymereParts = new HashMap<>();
    for (int position=0;position<startingPolymere.length()-1;position++) {
      String key = startingPolymere.substring(position, position + 2);
      polymereParts.putIfAbsent(key, 0L);
      polymereParts.put(key, polymereParts.get(key) + 1);
    }
    return polymereParts;
  }

  private static Map<String, Long> superGrowPolymere(Map<String, Long> oldPartsCount) {
    final Map<String, Long> newPartsCount = new HashMap<>(oldPartsCount);
    rulesMap.keySet()
        .forEach(ruleKey -> {
          // part AB has a rule AB -> C
          Long nrOfParts = oldPartsCount.get(ruleKey); // nr of AB parts
          if (nrOfParts != null && nrOfParts > 0L) {
            //set part AB to 0, replace by new parts AC and CB
            newPartsCount.put(ruleKey, newPartsCount.get(ruleKey) - oldPartsCount.get(ruleKey));

            String newChar = rulesMap.get(ruleKey);
            String leftKey = ruleKey.charAt(0) + newChar;
            String rightKey = newChar + ruleKey.charAt(1);

            Long leftKeyOccurences = newPartsCount.get(leftKey) == null ? 0L : newPartsCount.get(leftKey);
            newPartsCount.put(leftKey,  leftKeyOccurences + nrOfParts);

            Long rightKeyOccurences = newPartsCount.get(rightKey) == null ? 0L : newPartsCount.get(rightKey);
            newPartsCount.put(rightKey, rightKeyOccurences + nrOfParts);
          }
        });
    return newPartsCount;
  }

  private static Map<String, Long> getLetterOccurences(List<String> polymereLetters) {
    return polymereLetters.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  private static Rule convertToRule(String ruleInput) {
    String[] parts = ruleInput.split(" -> ");
    String newChar = parts[1];
    String leftChar = parts[0].substring(0, 1);
    String rightChar = parts[0].substring(1, 2);
    return new Rule(leftChar, rightChar, newChar);
  }

  private static class Rule {
    String leftChar;
    String rightChar;
    String newChar;

    public Rule(String leftChar, String rightChar, String newChar) {
      this.leftChar = leftChar;
      this.rightChar = rightChar;
      this.newChar = newChar;
    }
  }

  private static class RuleMap {

    static String getNewChar(String left, String right) {
      String newChar = rulesMap.get(left+right);
      if (newChar == null) {
        return "";
      }
      return newChar;
    }

  }
}
