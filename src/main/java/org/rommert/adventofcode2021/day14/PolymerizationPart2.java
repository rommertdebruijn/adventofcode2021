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
      "VPPHOPVVSFSVFOCOSBKF\n" +
      "\n" +
      "CO -> B\n" +
      "CV -> N\n" +
      "HV -> H\n" +
      "ON -> O\n" +
      "FS -> F\n" +
      "NS -> S\n" +
      "VK -> C\n" +
      "BV -> F\n" +
      "SC -> N\n" +
      "NV -> V\n" +
      "NC -> F\n" +
      "NH -> B\n" +
      "BO -> K\n" +
      "FC -> H\n" +
      "NB -> H\n" +
      "HO -> F\n" +
      "SB -> N\n" +
      "KP -> V\n" +
      "OS -> C\n" +
      "OB -> P\n" +
      "SH -> N\n" +
      "BC -> H\n" +
      "CK -> H\n" +
      "SO -> N\n" +
      "SP -> P\n" +
      "CF -> P\n" +
      "KV -> F\n" +
      "CS -> V\n" +
      "FF -> P\n" +
      "VS -> V\n" +
      "CP -> S\n" +
      "PH -> V\n" +
      "OP -> K\n" +
      "KH -> B\n" +
      "FB -> S\n" +
      "CN -> H\n" +
      "KS -> P\n" +
      "FN -> O\n" +
      "PV -> O\n" +
      "VC -> S\n" +
      "HF -> N\n" +
      "OC -> O\n" +
      "PK -> V\n" +
      "KC -> C\n" +
      "HK -> C\n" +
      "PO -> N\n" +
      "OO -> S\n" +
      "VH -> N\n" +
      "CC -> K\n" +
      "BP -> K\n" +
      "HC -> K\n" +
      "FV -> K\n" +
      "KF -> V\n" +
      "VF -> C\n" +
      "HN -> S\n" +
      "VP -> B\n" +
      "HH -> O\n" +
      "FO -> O\n" +
      "PC -> N\n" +
      "KK -> C\n" +
      "PN -> P\n" +
      "NN -> C\n" +
      "FH -> N\n" +
      "VV -> O\n" +
      "OK -> V\n" +
      "CB -> N\n" +
      "SN -> H\n" +
      "VO -> H\n" +
      "BB -> C\n" +
      "PB -> F\n" +
      "NF -> P\n" +
      "KO -> S\n" +
      "PP -> K\n" +
      "NO -> O\n" +
      "SF -> N\n" +
      "KN -> S\n" +
      "PS -> O\n" +
      "VN -> V\n" +
      "SS -> N\n" +
      "BF -> O\n" +
      "HP -> H\n" +
      "HS -> N\n" +
      "BS -> S\n" +
      "VB -> F\n" +
      "PF -> K\n" +
      "SV -> V\n" +
      "BH -> P\n" +
      "FP -> O\n" +
      "CH -> P\n" +
      "OH -> K\n" +
      "OF -> F\n" +
      "HB -> V\n" +
      "FK -> V\n" +
      "BN -> V\n" +
      "SK -> F\n" +
      "OV -> C\n" +
      "NP -> S\n" +
      "NK -> S\n" +
      "BK -> C\n" +
      "KB -> F";
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

    HashMap<String, Long> totals = new HashMap<>();
    polymereParts.forEach((key, value) -> {
      String letter = key.substring(1, 2);
      totals.putIfAbsent(letter, 0L);
      totals.put(letter, totals.get(letter) + value);
    });
    String startingLetter = startingPolymere.substring(0, 1);
    totals.put(startingLetter, totals.get(startingLetter) + 1);

    System.out.println(totals);

    Long max = totals.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(0L);
    Long min = totals.entrySet().stream().min(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(0L);
    System.out.println(max - min);
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
