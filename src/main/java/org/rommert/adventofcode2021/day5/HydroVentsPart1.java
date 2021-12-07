package org.rommert.adventofcode2021.day5;

import org.rommert.adventofcode2021.InputParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class HydroVentsPart1 {
  private static final String input = "0,9 -> 5,9\n" +
      "8,0 -> 0,8\n" + //diagonal
      "9,4 -> 3,4\n" +
      "2,2 -> 2,1\n" +
      "7,0 -> 7,4\n" +
      "6,4 -> 2,0\n" + //diagonal
      "0,9 -> 2,9\n" +
      "3,4 -> 1,4\n" +
      "0,0 -> 8,8\n" + //diagonal
      "5,5 -> 8,2"; // diagonal

  public static void main(String[] args) {
    List<Line> lines = new InputParser<Line>().convertInput(input, HydroVentsPart1::convertToLine);

    Map<Coordinate, Long> heatMap = lines.stream()
        .filter(line -> !line.isDiagonal())
        .flatMap(line -> line.getCoordinates().stream())
        .collect(Collectors.groupingBy(coordinate -> coordinate, Collectors.counting()));

    String representation = buildRepresentation(heatMap);
    System.out.println(representation);

    long nrOf2OrMoreLinesCrossing = heatMap.entrySet().stream()
        .filter(entry -> entry.getValue() > 1)
        .count();
    System.out.println("Nr of 2 or more lines crossing: " + nrOf2OrMoreLinesCrossing);

  }

  private static String buildRepresentation(Map<Coordinate, Long> heatMap) {
    int maxX = heatMap.keySet().stream().max(Comparator.comparing(coordinate -> coordinate.x)).get().x;
    int maxY = heatMap.keySet().stream().max(Comparator.comparing(coordinate -> coordinate.y)).get().y;

    StringBuilder representation = new StringBuilder("\n");
    for (int y=0;y<=maxY;y++) {
      for (int x=0;x<=maxX;x++) {
        representation.append(render(new Coordinate(x, y), heatMap));
      }
      representation.append("\n");
    }
    return representation.toString();
  }

  private static String render(Coordinate coordinate, Map<Coordinate, Long> heatMap) {
    if (!heatMap.containsKey(coordinate)) {
      return ".";
    }
    return heatMap.get(coordinate) + "";
  }

  private static Line convertToLine(String lineInput) {
    String[] coordinateStrings = lineInput.split(" -> ");
    String[] startCoordinateStrings = coordinateStrings[0].split(",");
    String[] endCoordinateStrings = coordinateStrings[1].split(",");

    Coordinate startCoordinate = new Coordinate(Integer.parseInt(startCoordinateStrings[0]), Integer.parseInt(startCoordinateStrings[1]));
    Coordinate endCoordinate = new Coordinate(Integer.parseInt(endCoordinateStrings[0]), Integer.parseInt(endCoordinateStrings[1]));
    return new Line(startCoordinate, endCoordinate);
  }

  private static class Line {

    private final Coordinate start;
    private final Coordinate end;

    public Line(Coordinate start, Coordinate end) {
      this.start = start;
      this.end = end;
    }

    boolean isDiagonal() {
      int deltaX = end.x - start.x;
      int deltaY = end.y - start.y;
      return deltaX != 0 && deltaY != 0;
    }

    private List<Coordinate> getCoordinates() {
      int deltaX = end.x - start.x;
      int deltaY = end.y - start.y;

      int stepX = Integer.compare(deltaX, 0);
      int stepY = Integer.compare(deltaY, 0);

      int nrOfSteps = Integer.max(abs(deltaX), abs(deltaY));
      List<Coordinate> coordinates = new ArrayList<>();
      for (int steps=0;steps<=nrOfSteps;steps++) {
        int x = start.x + (steps * stepX);
        int y = start.y + (steps * stepY);
        coordinates.add(new Coordinate(x, y));
      }

      return coordinates;
    }
  }

  private static class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Coordinate that = (Coordinate) o;
      return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }

    @Override
    public String toString() {
      return "[x=" + x + ", y=" + y + ']';
    }
  }
}
