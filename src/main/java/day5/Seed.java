package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Seed {

    private static final int MAPS_NUM = 7;
    private static final int NOT_FOUND = -1;

    public static class aMap {
        private long sourceRangeStart;
        private long destinationRangeStart;
        private long rangeLength;

        public aMap(long sourceRangeStart, long destinationRangeStart, long rangeLength) {
            this.sourceRangeStart = sourceRangeStart;
            this.destinationRangeStart = destinationRangeStart;
            this.rangeLength = rangeLength;
        }

        /**
         * transform from src to dst
         * @param src
         * @return the corresponding dst result if src is in the src range; otherwise return NOT_FOUND;
         */
        public long srcToDst(long src) {
            if (src < sourceRangeStart || src >= sourceRangeStart + rangeLength) {
                return NOT_FOUND;
            }
            long distance = src - sourceRangeStart;
            return destinationRangeStart + distance;
        }

        @Override
        public String toString() {
            return "" + destinationRangeStart + " " + sourceRangeStart + " " + rangeLength;
        }
    }

    // from src to dst
    // 2 cases: 1. with shift 2. no shift
    private static long oneIteration(long src, Set<aMap> map) {
        long result = NOT_FOUND;
        for (aMap a: map) {
            result = a.srcToDst(src);
            if (result != NOT_FOUND) {
                return result;
            }
        }
        return src;
    }

    private static long seedToLocation(long seedNum, Set<aMap>[] maps) {
        long src = seedNum;
        for (int i = 0; i < maps.length; i++) {
            src = oneIteration(src, maps[i]);
        }
        return src;
    }

    public static long smallestLocation(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        String[] seeds = line.split(": ")[1].split(" ");

        Set<aMap>[] maps = new HashSet[MAPS_NUM];
        for (int i = 0; i < MAPS_NUM; i++) {
            maps[i] = new HashSet<>();
        }
        int mapNum = -1;
        line = reader.readLine();
        while (line != null) {
            if (line.isEmpty()) {
                line = reader.readLine();
                continue;
            }
            if (!Character.isDigit(line.charAt(0))) {
                mapNum++;
            } else {
                String[] nums = line.split(" ");
                aMap newMap = new aMap(Long.parseLong(nums[1]), Long.parseLong(nums[0]), Long.parseLong(nums[2]));
                maps[mapNum].add(newMap);
            }
            line = reader.readLine();
        }

        long result = Integer.MAX_VALUE;
        for (int i = 0; i < seeds.length; i++) {
            long seedNum = Long.parseLong(seeds[i]);
            result = Math.min(result, seedToLocation(seedNum, maps));
        }
        return result;
    }
}
