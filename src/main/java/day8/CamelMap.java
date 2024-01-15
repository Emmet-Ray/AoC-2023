package day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelMap {


    public static int totalSteps(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String instructions = reader.readLine();
        Queue<Character> instructionQueue = new ArrayDeque<>();
        for (int i = 0; i < instructions.length(); i++) {
            instructionQueue.add(instructions.charAt(i));
        }

        reader.readLine();

        Pattern regex = Pattern.compile("(?<source>[A-Z]{3}) = \\((?<left>[A-Z]{3}), (?<right>[A-Z]{3})\\)");
        Map<String, Next> maps = new HashMap<>();

        String currentMap = reader.readLine();
        while (currentMap != null) {
            Matcher m = regex.matcher(currentMap);
            if (m.matches()) {
                String source = m.group("source");
                String left = m.group("left");
                String right = m.group("right");
                Next next = new Next(left, right);
                maps.put(source, next);
            }
            currentMap = reader.readLine();
        }

        int steps = 0;
        String currentSource = "AAA";

        while (true) {
            // magic string
            if (currentSource.equals("ZZZ")) {
                break;
            }
            char currentInstruction = instructionQueue.remove();
            System.out.printf("%c", currentInstruction);
            Next next = maps.get(currentSource);
            if (currentInstruction == 'L') {
                currentSource = next.getLeft();
            } else {
                currentSource = next.getRight();
            }
            steps++;
            instructionQueue.add(currentInstruction);
        }
        System.out.println();


        return steps;
    }
}
