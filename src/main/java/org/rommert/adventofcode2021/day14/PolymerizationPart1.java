package org.rommert.adventofcode2021.day14;

import org.rommert.adventofcode2021.InputParser;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PolymerizationPart1 {

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

  public static void main(String[] args) {
    String startingPolymere = input.split("\n\n")[0];
    List<Rule> rules = new InputParser<Rule>().convertInput(input.split("\n\n")[1], PolymerizationPart1::convertToRule);

    String polymere = startingPolymere;
    System.out.println(polymere);
    for (int step=0;step<40;step++) {
      polymere = growPolymere(polymere, rules);
      System.out.println(polymere);
    }

    Map<String, Long> letterOccurences = getLetterOccurences(polymere, rules);

    Long max = letterOccurences.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(0L);
    Long min = letterOccurences.entrySet().stream().min(Map.Entry.comparingByValue()).map(Map.Entry::getValue).orElse(0L);
    System.out.println(max - min);
  }

  private static Map<String, Long> getLetterOccurences(String polymere, List<Rule> rules) {
    return polymere.chars()
        .mapToObj(c -> (char) c)
        .map(c -> Character.toString(c))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  private static String growPolymere(String polymere, List<Rule> rules) {
    StringBuilder growingPolymere = new StringBuilder(polymere.charAt(0) + ""); // start with first letter
    for (int cursor=0;cursor<polymere.length()-1;cursor++) {
      final String leftChar = polymere.charAt(cursor) + "";
      final String rightChar = polymere.charAt(cursor+1) + "";

      String newChar = rules.stream()
          .filter(rule -> rule.appliesTo(leftChar, rightChar))
          .findFirst().map(rule -> rule.newChar)
          .orElse(""); // if no rule applies, no new character is added
      growingPolymere.append(newChar).append(rightChar);
    }
    return growingPolymere.toString();
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

    boolean appliesTo(String leftChar, String rightChar) {
      return this.leftChar.equals(leftChar) &&
             this.rightChar.equals(rightChar);
    }
  }
}
