package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.BigInteger;

public class CamelMap {


    public static long totalSteps(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String instructions = reader.readLine();
        Queue<Character> instructionQueue = new ArrayDeque<>();
        for (int i = 0; i < instructions.length(); i++) {
            instructionQueue.add(instructions.charAt(i));
        }

        reader.readLine();

        Pattern regex = Pattern.compile("(?<source>.{3}) = \\((?<left>.{3}), (?<right>.{3})\\)");
        Map<String, Next> maps = new HashMap<>();
        Set<String> sources = new HashSet<>();

        String currentMap = reader.readLine();
        while (currentMap != null) {
            Matcher m = regex.matcher(currentMap);
            if (m.matches()) {
                String source = m.group("source");
                if (source.endsWith("A")) {
                    sources.add(source);
                }
                String left = m.group("left");
                String right = m.group("right");
                Next next = new Next(left, right);
                maps.put(source, next);
            }
            currentMap = reader.readLine();
        }

        long[] number = new long[sources.size()];
        int index = 0;
        for (String s: sources) {
            long currentResult = steps(maps, s, new ArrayDeque<>(instructionQueue));
            number[index++] = currentResult;
        }

        return lcmOfArray(number);
    }

    public static long lcmOfArray(long[] numbers) {
        BigInteger lcm = BigInteger.valueOf(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            BigInteger bi = BigInteger.valueOf(numbers[i]);
            BigInteger gcd = lcm.gcd(bi);
            lcm = lcm.multiply(bi).divide(gcd);
        }
        return lcm.longValue();
    }
    private static long steps(Map<String, Next> maps, String currentSource, Queue<Character> instructionQueue) {
        int steps = 0;
        while (true) {
            // magic string
            if (currentSource.endsWith("Z")) {
                break;
            }
            char currentInstruction = instructionQueue.remove();
            Next next = maps.get(currentSource);
            if (currentInstruction == 'L') {
                currentSource = next.getLeft();
            } else {
                currentSource = next.getRight();
            }
            steps++;
            instructionQueue.add(currentInstruction);
        }
        return steps;
    }
}
