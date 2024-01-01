package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SumOfPartNum {

    public static boolean isValidIndex(int len, int index) {
        return index > -1 && index < len;
    }

    public static boolean isStar(char c) {
        return c == '*';
    }

    public static int oneCase(String s, int index) {
        if (s == null) {
            return 0;
        }

        if (!isValidIndex(s.length(), index) || !Character.isDigit(s.charAt(index))) {
            return 0;
        }

        int startIndex = index - 1;
        int endIndex = index + 1;
        while (isValidIndex(s.length(), startIndex) && Character.isDigit(s.charAt(startIndex))) {
            startIndex--;
        }
        while (isValidIndex(s.length(), endIndex) && Character.isDigit(s.charAt(endIndex))) {
            endIndex++;
        }
        return Integer.parseInt(s.substring(startIndex + 1, endIndex));
    }

    // the redundancy is because I need to trace both [counter] & [result].
    // of course, I could create a class as return type
    // My goal is to calculate the result
    public static int sumOfOneLineHelper(String current, int index, String prev, String next) {
        int counter = 0;
        int result = 1;

        int cur = oneCase(current, index - 1);
        if (cur > 0) {
            result *= cur;
            counter++;
        }
        cur = oneCase(current, index + 1);
        if (cur > 0) {
            result *= cur;
            counter++;
        }

        cur = oneCase(prev, index);
        if (cur > 0) {
            result *= cur;
            counter++;
        } else {
            cur = oneCase(prev, index - 1);
            if (cur > 0) {
                result *= cur;
                counter++;
            }
            cur = oneCase(prev, index + 1);
            if (cur > 0) {
                result *= cur;
                counter++;
            }
        }

        cur = oneCase(next, index);
        if (cur > 0) {
            result *= cur;
            counter++;
        } else {
            cur = oneCase(next, index - 1);
            if (cur > 0) {
                result *= cur;
                counter++;
            }
            cur = oneCase(next, index + 1);
            if (cur > 0) {
                result *= cur;
                counter++;
            }
        }

        if (counter == 2) {
            return result;
        }
        return 0;
    }

    /**
     *
     * @param currentLine
     * @param prevLine
     * @param nextLine
     * @return
     */
    public static int sumOfOneLine(String currentLine, String prevLine, String nextLine) {
        int sum = 0;

        int len = currentLine.length();
        for (int i = 0; i < len; i++) {
            while (isValidIndex(len, i) && !isStar(currentLine.charAt(i))) {
                i++;
            }
            sum += sumOfOneLineHelper(currentLine, i, prevLine, nextLine);
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
