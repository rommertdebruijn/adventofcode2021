package org.rommert.adventofcode2021.day9;

import org.rommert.adventofcode2021.InputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SmokeBasinPart2 {

  private static final String input = "" +
      "2199943210\n" +
      "3987894921\n" +
      "9856789892\n" +
      "8767896789\n" +
      "9899965678";

  public static void main(String[] args) {




    List<List<Integer>> numberBlock = new InputParser<List<Integer>>().convertInput(input, SmokeBasinPart2::convertToListOfInt);
    CaveMap caveMap = new CaveMap(numberBlock);

    List<Integer> basinSizes = new ArrayList<>();
    while (caveMap.determineBasinStartingPoint() != null) {
//      Coordinate startingPoint = caveMap.determineBasinStartingPoint();
//
//      CaveMap previousMap = new CaveMap(numberBlock) {
//
//      }
    }
  }

  private static List<Integer> convertToListOfInt(String numbers) {
    char[] chars = numbers.toCharArray();
    return IntStream
        .range(0, chars.length)
        .mapToObj(i -> chars[i])
        .map(c -> c + "")
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  private static class CaveMap {
    private final List<List<Integer>> numberBlock = new ArrayList<>();
    private final int caveX;
    private final int caveY;

    public CaveMap(List<List<Integer>> numberBlock) {
      caveX = numberBlock.get(0).size();
      caveY = numberBlock.size();

      // dont pass reference
      for (List<Integer> thatRow : numberBlock) {
        List<Integer> thisRow = new ArrayList<>(thatRow);
        this.numberBlock.add(thisRow);
      }
    }

    Coordinate determineBasinStartingPoint() {
      //get first lower-then-9 location as starting point
      for (int x=0;x<caveX;x++) {
        for (int y=0;y<caveY;y++) {
          if (numberBlock.get(y).get(x) < 9) {
            return new Coordinate(x, y);
          }
        }
      }
      return null; // end state reached
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      CaveMap caveMap = (CaveMap) o;
      return Objects.equals(numberBlock, caveMap.numberBlock);
    }

    @Override
    public int hashCode() {
      return Objects.hash(numberBlock);
    }
  }

  static class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
