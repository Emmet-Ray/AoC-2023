package day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SumOfCalibrationValues {

    public static class Pair {
        public Pair(int increaseStep, int digit) {
            this.increaseStep = increaseStep;
            this.digit = digit;
        }
        int increaseStep;
        int digit;

        public void printPair() {
            System.out.println("increaseStep: " + increaseStep + " digit: " + digit);
        }
    }

    public static Pair LetterDigit(String input, int startIndex) {
        int leftLength = input.length() - startIndex;
        if (leftLength < 3) {
            return new Pair(0, -1);
        }
        String first = input.substring(startIndex, startIndex + 3);
        switch (first) {
            case "one": return new Pair(2, 1);
            case "two": return new Pair(2, 2);
            case "six": return new Pair(2, 6);
        }

        if (leftLength > 3) {
            String second = input.substring(startIndex, startIndex + 4);
            switch (second) {
                case "four": return new Pair(3, 4);
                case "five": return new Pair(3, 5);
                case "nine": return new Pair(3, 9);
            }
        }
        if (leftLength > 4) {
            String third = input.substring(startIndex, startIndex + 5);
            switch (third) {
                case "three": return new Pair(4, 3);
                case "seven": return new Pair(4, 7);
                case "eight": return new Pair(4, 8);
            }
        }
        return new Pair(0, -1);
    }

    /**
     *
     * @param input
     * @return the calibration value of the input string
     */
    public static int calibrationValueOfOneString(String input) {
        System.out.println(input);
        int firstDigit, lastDigit;
        firstDigit = lastDigit = -1;
        char currentChar = 0;
        for (int i = 0; i < input.length(); i++) {
            currentChar = input.charAt(i);
            if (Character.isDigit(currentChar)) {
                lastDigit = currentChar - '0';
            } else {
                Pair curPair = LetterDigit(input, i);
                if (curPair.increaseStep != 0) {
                    //i += curPair.increaseStep;      <----------------- this line is the misunderstanding line
                    lastDigit = curPair.digit;
                }
            }

            if (firstDigit == -1 && lastDigit != -1) {
                firstDigit = 10 * lastDigit;
            }
        }
        System.out.println(firstDigit + lastDigit);
        return firstDigit + lastDigit;
    }

    public static int sumOfCalibrationValues(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int result = 0;
        String currentLine = reader.readLine();
        while (currentLine != null) {
            result += calibrationValueOfOneString(currentLine);
            currentLine = reader.readLine();
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            int result = sumOfCalibrationValues("C:\\Users\\Lenovo\\Desktop\\AdventOfCode2023\\src\\main\\resources\\day1\\input.txt");
            System.out.println("result: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
