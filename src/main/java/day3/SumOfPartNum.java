package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SumOfPartNum {

    /**
     *
     * @param c
     * @return
     */
    public static boolean isSymbol(char c) {
        return c != '.' && !Character.isDigit(c);
    }

    public static boolean isValidIndex(int len, int index) {
        return index > -1 && index < len;
    }

    public static boolean isPartNumHelper(int len, int index, String adjacentLine) {
        return isValidIndex(len, index) && isSymbol(adjacentLine.charAt(index));
    }
    /**
     *
     * @param index
     * @param adjacentLine
     * @return
     */
    public static boolean isPartNumCase1(int index, String adjacentLine) {
        if (adjacentLine == null) {
            return false;
        }
        int len = adjacentLine.length();
        return isPartNumHelper(len, index - 1, adjacentLine) ||
                isPartNumHelper(len, index, adjacentLine)||
                isPartNumHelper(len, index + 1, adjacentLine);
    }

    public static boolean isPartNumCase2(String currentLine, int start, int end) {
        int len = currentLine.length();
        return isValidIndex(len, start - 1) && isSymbol(currentLine.charAt(start - 1)) ||
                isValidIndex(len, end) && isSymbol(currentLine.charAt(end));
    }

    /**
     *
     * @param currentLine
     * @param adjacentLine
     * @return
     */
    public static int sumOfOneLine(String currentLine, String adjacentLine, String nextLine) {
        int sum = 0;
        int startIndex = 0;
        int endIndex = 0;
        boolean isPartNum;
        for (int i = 0; i < currentLine.length(); i++) {
            // find the start index of a number
            while (i < currentLine.length() && !Character.isDigit(currentLine.charAt(i))) {
                i++;
            }
            startIndex = i;

            isPartNum = false;
            while (i < currentLine.length() && Character.isDigit(currentLine.charAt(i))) {
                if (!isPartNum) {
                    isPartNum = isPartNumCase1(i, adjacentLine) || isPartNumCase1(i, nextLine);
                }
                i++;
            }
            endIndex = i;

            boolean case2 = isPartNumCase2(currentLine, startIndex, endIndex);
            isPartNum = isPartNum || case2;
            if (isPartNum) {
                sum += Integer.parseInt(currentLine.substring(startIndex, endIndex));
            }
        }
        if (sum != 0) {
            System.out.println(sum);
        }
        return sum;
    }


    /**
     *
     * @param fileName
     * @return
     */
    public static int sumOfFile(String fileName) throws IOException {
        int sum = 0;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String prevLine = null;
        String currentLine = reader.readLine();
        String nextLine = reader.readLine();
        while (currentLine != null) {
            sum += sumOfOneLine(currentLine, prevLine, nextLine);
            prevLine = currentLine;
            currentLine = nextLine;
            nextLine = reader.readLine();
        }
        return sum;
    }


    public static void main(String[] args) {
        String file = "C:\\Users\\Lenovo\\Desktop\\AdventOfCode2023\\src\\main\\resources\\day3\\input.txt";
        try {
            int result = SumOfPartNum.sumOfFile(file);
            System.out.println("result: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
