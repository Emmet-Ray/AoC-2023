package day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SumOfCalibrationValues {

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
            if (firstDigit == -1 && Character.isDigit(currentChar)) {
                firstDigit = 10 * (currentChar - '0');
                lastDigit = currentChar - '0';
            } else if (Character.isDigit(currentChar)) {
                lastDigit = currentChar - '0';
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
