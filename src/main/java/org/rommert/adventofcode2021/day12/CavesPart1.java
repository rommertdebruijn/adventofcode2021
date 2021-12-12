package org.rommert.adventofcode2021.day12;

import org.rommert.adventofcode2021.InputParser;

import java.util.*;
import java.util.stream.Collectors;

public class CavesPart1 {

    private static final String input = "" +
            "mx-IQ\n" +
            "mx-HO\n" +
            "xq-start\n" +
            "start-HO\n" +
            "IE-qc\n" +
            "HO-end\n" +
            "oz-xq\n" +
            "HO-ni\n" +
            "ni-oz\n" +
            "ni-MU\n" +
            "sa-IE\n" +
            "IE-ni\n" +
            "end-sa\n" +
            "oz-sa\n" +
            "MU-start\n" +
            "MU-sa\n" +
            "oz-IE\n" +
            "HO-xq\n" +
            "MU-xq\n" +
            "IE-end\n" +
            "MU-mx";

    public static void main(String[] args) {
        List<CaveConnection> caveConnections = new InputParser<CaveConnection>().convertInput(input, CavesPart1::convertToSegment);

        List<CavePath> startPaths = caveConnections.stream()
                .filter(caveConn -> caveConn.containsCave("start"))
                .map(caveConn -> new CavePath(Collections.singletonList(caveConn), caveConn.getOtherCave("start")))
                .collect(Collectors.toList()); //assuming that startelements always have start as first cave :S

        List<CavePath> paths = startPaths;
        while (paths.stream().anyMatch(path -> !path.currentPosition.equals("end"))) {
            paths = progressPaths(paths, caveConnections);
        }

        System.out.println(paths.size() + " paths found:\n\n" + paths);
    }

    private static List<CavePath> progressPaths(List<CavePath> paths, List<CaveConnection> caveConnections) {
        List<CavePath> newPaths = new ArrayList<>();
        for (CavePath path : paths) {
            if (path.currentPosition.equals("end")) {
                newPaths.add(path); // This one is a keeper!
                continue;
            }

            List<CaveConnection> suiteableSteps = findSuitableSteps(path, caveConnections);
            if (suiteableSteps.isEmpty()) {
                continue; // no use pursueing this path any further. Wont be in the next generation
            }

            for (CaveConnection connection : suiteableSteps) {
                List<CaveConnection> newConnections = new ArrayList<>(path.caveConnections);
                newConnections.add(connection);
                String newPosition = connection.getOtherCave(path.currentPosition);
                newPaths.add(new CavePath(newConnections, newPosition)); // append new connection to Path and update its position
            }
        }
        return newPaths;
    }

    private static List<CaveConnection> findSuitableSteps(CavePath path, List<CaveConnection> caveConnections) {
        return caveConnections.stream()
            .filter(connection -> !connection.containsCave("start"))
            .filter(connection -> connection.containsCave(path.currentPosition))
            .filter(connection -> !connection.containsAnyOfCaves(path.getSmallCavesVisited()))
            .collect(Collectors.toList());
    }

    private static CaveConnection convertToSegment(String segmentInput) {
        String[] caves = segmentInput.split("-");

        return new CaveConnection(caves[0], caves[1]);
    }

    private static class CaveConnection {
        String cave1;
        String cave2;

        public CaveConnection(String cave1, String cave2) {
            this.cave1 = cave1;
            this.cave2 = cave2;
        }

        boolean containsCave(String cave) {
            return cave1.equals(cave) || cave2.equals(cave);
        }

        String getOtherCave(String thisCave) {
            if (cave1.equals(thisCave)) {
                return cave2;
            }
            if (cave2.equals(thisCave)) {
                return cave1;
            }
            throw new RuntimeException("Expected either of caves " + cave1 +" and " + cave2 + " to be " + thisCave);
        }

        public boolean containsAnyOfCaves(Set<String> smallCavesVisited) {
            return smallCavesVisited.contains(cave1) || smallCavesVisited.contains(cave2);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CaveConnection that = (CaveConnection) o;
            return Objects.equals(cave1, that.cave1) &&
                    Objects.equals(cave2, that.cave2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cave1, cave2);
        }

        @Override
        public String toString() {
            return "[cave1='" + cave1 + '\'' +
                    ", cave2='" + cave2 + '\'' +
                    ']';
        }
    }

    private static class CavePath {
        List<CaveConnection> caveConnections = new ArrayList<>();
        String currentPosition;


        public CavePath(List<CaveConnection> caveConnections,  String currentPosition) {
            this.caveConnections = caveConnections;
            this.currentPosition = currentPosition;
        }

        Set<String> getSmallCavesVisited() {
            Set<String> smallCaves = new HashSet<>();
            for (CaveConnection connection : caveConnections) {
                if (isSmallCave(connection.cave1)) {
                    smallCaves.add(connection.cave1);
                }
                if (isSmallCave(connection.cave2)) {
                    smallCaves.add(connection.cave2);
                }
            }
            smallCaves.remove(currentPosition);
            return smallCaves;
        }

        private boolean isSmallCave(String cave) {
            char c = cave.toCharArray()[0];
            return c > 96 && c < 123;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CavePath cavePath = (CavePath) o;
            return Objects.equals(caveConnections, cavePath.caveConnections) &&
                    Objects.equals(currentPosition, cavePath.currentPosition);
        }

        @Override
        public int hashCode() {
            return Objects.hash(caveConnections, currentPosition);
        }

        @Override
        public String toString() {
            String representation = "start";
            String position = "start";
            for (CaveConnection connection : caveConnections) {
                String newPosition = connection.getOtherCave(position);
                representation += "-" + newPosition;
                position = newPosition;
            }

            return representation;
        }
    }
}

/*

 */


/*


 */