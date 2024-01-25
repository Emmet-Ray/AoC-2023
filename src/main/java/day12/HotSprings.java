package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HotSprings {

    private static int current;
    private static List<Integer> actualResult;

    public static int totalArrangements(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int result = 0;

        String currentLine = reader.readLine();
        while (currentLine != null) {
            String[] split = currentLine.split(" +");
            StringBuilder firstFormatBuilder = new StringBuilder();
            StringBuilder secondFormatBuilder = new StringBuilder();

            for (int i = 0; i < 4; i++) {
                firstFormatBuilder.append(split[0] + "?");
                secondFormatBuilder.append(split[1] + ",");
            }
            firstFormatBuilder.append(split[0]);
            secondFormatBuilder.append(split[1]);

            String firstFormat = firstFormatBuilder.toString();
            String secondFormat = secondFormatBuilder.toString();

            actualResult = new ArrayList<>();
            for (String s: secondFormat.split(",")) {
                actualResult.add(Integer.parseInt(s));
            }

            // generate all possible combinations of split[0]
            current= 0;
            List<String> possibleArrangements = possibleArrangements(firstFormat);

            /*
            for (String s: possibleArrangements) {
                List<Integer> currentResult = contiguousDamagedSprings(s);
                if (actualResult.equals(currentResult)) {
                    current += 1;
                }
            }
             */
            System.out.println(current);

            result += current;
            currentLine = reader.readLine();
        }

        return result;
    }

    private static List<String> possibleArrangements(String s) {
        List<String> result = new ArrayList<>();
        generateCombinations(s, 0, result);
        return result;
    }


    private static void generateCombinations(String s, int index, List<String> result) {
        if (index == s.length()) {
            List<Integer> currentResult = contiguousDamagedSprings(s);
            if (actualResult.equals(currentResult)) {
                current += 1;
            }
            return;
        }

        if (s.charAt(index) == '?') {
            generateCombinations(s.substring(0, index) + '.' + s.substring(index + 1), index + 1, result);
            generateCombinations(s.substring(0, index) + '#' + s.substring(index + 1), index + 1, result);
        } else {
            generateCombinations(s, index + 1, result);
        }
    }

    private static List<Integer> contiguousDamagedSprings(String springs) {
        List<Integer> result = new ArrayList<>();
        String[] damaged = springs.split("\\.+");
        for (String s: damaged) {
            if (s.isEmpty()) {
                continue;
            }
            result.add(s.length());
        }
        return result;
    }

}
