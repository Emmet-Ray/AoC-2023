package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TiltPlatform {

    public static int result(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int rowLen = (int) reader.lines().count();
        reader = new BufferedReader(new FileReader(file));

        String currentLine = reader.readLine();
        int colLen = currentLine.length();
        int rowIndex = 0;
        char[][] platform = new char[rowLen][colLen];
        while (currentLine != null) {
            platform[rowIndex] = currentLine.toCharArray();
            rowIndex++;
            currentLine = reader.readLine();
        }

        int result = helper(platform);
        return result;
    }

    private static int helper(char[][] platform) {
        int rowLen = platform.length;
        int colLen = platform[0].length;
        int[][] finalRow = new int[rowLen][colLen];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                finalRow[i][j] = -1; // initialize
                findFinalRow(platform, finalRow, i, j);
            }
        }
        int result = 0;

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                // the formula to calculate the result
                if (platform[i][j] == 'O') {
                    int current = rowLen - finalRow[i][j];
                    result += current;
                }
            }
        }
        return result;
    }

    private static void findFinalRow(char[][] platform, int[][] finalRow, int currentRow, int currentCol) {
        char currentSymbol = platform[currentRow][currentCol];
        if (currentSymbol == '.') {
            return;
        }
        if (currentSymbol == '#') {
            finalRow[currentRow][currentCol] = currentRow;
            return;
        }
        int i = currentRow;
        while (i >= 0 && finalRow[i][currentCol] == -1) {
            i--;
        }
        if (i < 0) {
            finalRow[currentRow][currentCol] = 0;
        } else {
            finalRow[currentRow][currentCol] = finalRow[i][currentCol] + 1;
        }

    }
}
