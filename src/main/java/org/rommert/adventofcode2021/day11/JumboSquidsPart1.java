package org.rommert.adventofcode2021.day11;

import org.rommert.adventofcode2021.InputParser;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JumboSquidsPart1 {

    private static String input = "" +
            "6111821767\n" +
            "1763611615\n" +
            "3512683131\n" +
            "8582771473\n" +
            "8214813874\n" +
            "2325823217\n" +
            "2222482823\n" +
            "5471356782\n" +
            "3738671287\n" +
            "8675226574";

    public static void main(String[] args) {
        List<List<Squid>> squidRows = new InputParser<List<Squid>>().convertInput(input, JumboSquidsPart1::convertToSquidsList);

        System.out.println("Before: " + squidRows);
        int flashCounter = 0;
        for (int step=0;step<100;step++) {
            squidRows.stream().flatMap(Collection::stream).forEach(Squid::resetForStep);

            Boolean someSquidFlashed = null;
            squidRows.stream().flatMap(Collection::stream).forEach(Squid::poke);
            while (someSquidFlashed == null || someSquidFlashed) {
                someSquidFlashed = processFlash(squidRows);
            }
            flashCounter += squidRows.stream().flatMap(Collection::stream).filter(squid -> squid.hasFlashed).count();
            System.out.println("After step " + step + ": " + squidRows);
        }
        System.out.println("Total nr of flashes: " + flashCounter);

    }

    private static Boolean processFlash(List<List<Squid>> squidRows) {
        boolean flashOccurred = false;
        for (int x = 0; x < squidRows.get(0).size(); x++) {
            for (int y = 0;y < squidRows.get(0).size(); y++) {
                Squid squid = squidRows.get(y).get(x);
                if (squid.energyLevel > 9) {
                    squid.flash();
                    flashOccurred = true;
                    // This squid flashed! Increase all neighbours
                    pokeNeighbour(squidRows, x-1, y-1);
                    pokeNeighbour(squidRows, x, y-1);
                    pokeNeighbour(squidRows, x+1, y-1);
                    pokeNeighbour(squidRows, x-1, y);
                    pokeNeighbour(squidRows, x+1, y);
                    pokeNeighbour(squidRows, x-1, y+1);
                    pokeNeighbour(squidRows, x, y+1);
                    pokeNeighbour(squidRows, x+1, y+1);
                }
            }
        }
        return flashOccurred;
    }

    private static void pokeNeighbour(List<List<Squid>> squidRows, int x, int y) {
        if (x<0 || x > squidRows.size()-1 || y < 0 || y > squidRows.size()-1) {
            return;
        }
        squidRows.get(y).get(x).poke();
    }

    private static List<Squid> convertToSquidsList(String squids) {
        return squids.chars()
                .mapToObj(e -> (char)e)
                .map(c -> Character.toString(c))
                .map(Integer::parseInt)
                .map(Squid::new)
                .collect(Collectors.toList());
    }

    private static class Squid {
        int energyLevel;
        boolean hasFlashed = false;

        public Squid(int energyLevel) {
            this.energyLevel = energyLevel;
        }

        void resetForStep() {
            hasFlashed = false;
        }

        void poke() {
            if (!hasFlashed) {
                energyLevel++;
            }
        }

        void flash() {
            energyLevel = 0;
            hasFlashed = true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Squid squid = (Squid) o;
            return energyLevel == squid.energyLevel;
        }

        @Override
        public int hashCode() {
            return Objects.hash(energyLevel);
        }

        @Override
        public String toString() {
            if (hasFlashed) {
                return "*" + energyLevel + "*";
            }
            return " " + energyLevel + " ";
        }
    }
}
