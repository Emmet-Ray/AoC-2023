package day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SumOfCalibrationValues {

    public static int LetterDigit(String input, int startIndex) {
        int leftLength = input.length() - startIndex;
        if (leftLength < 3) {
            return -1;
        }
        String first = input.substring(startIndex, startIndex + 3);
        switch (first) {
            case "one": return 1;
            case "two": return 2;
            case "six": return 6;
        }

        if (leftLength > 3) {
            String second = input.substring(startIndex, startIndex + 4);
            switch (second) {
                case "four": return 4;
                case "five": return 5;
                case "nine": return 9;
            }
        }
        if (leftLength > 4) {
            String third = input.substring(startIndex, startIndex + 5);
            switch (third) {
                case "three": return 3;
                case "seven": return 7;
                case "eight": return 8;
            }
        }
        return -1;
    }

    /**
     *
     * @param input
     * @return the calibration value of the input string
     */
    public static int calibrationValueOfOneString(String input) {
        int firstDigit, lastDigit;
        firstDigit = lastDigit = -1;
        char currentChar = 0;
        for (int i = 0; i < input.length(); i++) {
            currentChar = input.charAt(i);
            if (Character.isDigit(currentChar)) {
                lastDigit = currentChar - '0';
            } else {
                int curDigit = LetterDigit(input, i);
                if (curDigit > 0) {
                    //i += curPair.increaseStep;      <----------------- this line is the misunderstanding line
                    lastDigit = curDigit;
                }
            }

            if (firstDigit == -1 && lastDigit != -1) {
                firstDigit = 10 * lastDigit;
            }
        }
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
