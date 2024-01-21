package day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day9 {

    private static boolean allZero(List<Integer> sequence) {
        for (int i: sequence) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static int nextValue(List<Integer> sequence) {
        // base case
        if (allZero(sequence)) {
            return 0;
        }

        List<Integer> nextSequence = new ArrayList<>();
        for (int i = 0; i < sequence.size() - 1; i++) {
            int current = sequence.get(i + 1) - sequence.get(i);
            nextSequence.add(current);
        }
        int next = nextValue(nextSequence);
        return sequence.get(0) - next;
    }

    private static List<Integer> stringListToIntList(String[] list) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < list.length; i++) {
            int current = Integer.parseInt(list[i]);
            result.add(current);
        }
        return result;
    }
    public static int sum(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();

        int sum = 0;
        while (currentLine != null) {
            List<Integer> current = stringListToIntList(currentLine.split(" +"));
            int currentNext = nextValue(current);
            sum += currentNext;
            currentLine = reader.readLine();
        }
        return sum;
    }


    public static void main(String[] args) {
        List<Integer> test = List.of(10, 13, 16, 21, 30, 45);
        int result = nextValue(test);
        System.out.println(result);
    }
}

