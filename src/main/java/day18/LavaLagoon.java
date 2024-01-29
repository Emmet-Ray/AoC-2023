package day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LavaLagoon {

    public static int result(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();

        int rowLen = 1;
        int colLen = 1;
        int intermediateRowLen = 0;
        int intermediateColLen = 0;
        int top, bottom;
        int left, right;
        top = left = Integer.MAX_VALUE;
        bottom = right = Integer.MIN_VALUE;

        while (currentLine != null) {
            String[] components = currentLine.split(" +");
            String direction = components[0];
            int distance = Integer.parseInt(components[1]);
            switch (direction) {
                case "R": {
                    intermediateColLen += distance;
                    if (intermediateColLen > right) {
                        right = intermediateColLen;
                    }
                    break;
                }
                case "L": {
                    intermediateColLen -= distance;
                    if (intermediateColLen < left) {
                        left = intermediateColLen;
                    }
                    break;
                }
                case "D": {
                    intermediateRowLen += distance;
                    if (intermediateRowLen > bottom) {
                        bottom = intermediateRowLen;
                    }
                    break;
                }
                case "U": {
                    intermediateRowLen -= distance;
                    if (intermediateRowLen < top) {
                        top = intermediateRowLen;
                    }
                    break;
                }
            }
            currentLine = reader.readLine();
        }
        rowLen = bottom - top + 1;
        colLen = right - left + 1;
        char[][] grid = new char[rowLen][colLen];
        initial(grid);

        System.out.println(top + "|" + bottom);
        System.out.println(left + "|" + right);
        int initialRow = Math.abs(top);
        int initialCol = Math.abs(left);
        System.out.println(initialRow + "|" + initialCol);

        System.out.println(rowLen + "|" + colLen);
        makeTrench(grid, initialRow, initialCol, new BufferedReader(new FileReader(file)));
        showGrid(grid);
        int result = numberOfLava(grid);
        return result;
    }

    private static void makeTrench(char[][] grid, int initialRow, int initialCol, BufferedReader reader) throws IOException{
        String currentLine = reader.readLine();
        int rowIndex = initialRow;
        int colIndex = initialCol;
        while (currentLine != null) {
            String[] components = currentLine.split(" +");
            String direction = components[0];
            int distance = Integer.parseInt(components[1]);
            switch (direction) {
                case "R": {
                    for (int i = 0; i < distance; i++) {
                        grid[rowIndex][++colIndex] = '#';
                    }
                    break;
                }
                case "L": {
                    for (int i = 0; i < distance; i++) {
                        grid[rowIndex][--colIndex] = '#';
                    }
                    break;
                }
                case "D": {
                    for (int i = 0; i < distance; i++) {
                        grid[++rowIndex][colIndex] = '#';
                    }
                    break;
                }
                case "U": {
                    for (int i = 0; i < distance; i++) {
                        grid[--rowIndex][colIndex] = '#';
                    }
                    break;
                }
            }
            currentLine = reader.readLine();
        }
    }

    private static int numberOfLava(char[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (needLava(grid, i, j)) {
                    result++;
                }
            }
        }
        return result;
    }
    private static boolean needLava(char[][] grid, int row, int col) {
        if (grid[row][col] == '#') {
            return true;
        }
        return withinTrenches(grid, row, col);
    }
    private static boolean withinTrenches(char[][] grid, int i, int j) {
        int rowIndex = i;
        int colIndex = j - 1;
        int intersectNum = 0;
        while (colIndex >= 0) {
            if (grid[rowIndex][colIndex] == '#') {
                if (rowIndex > 0 && grid[rowIndex - 1][colIndex] == '#') {
                    intersectNum++;
                }
            }
            colIndex--;
        }
        return intersectNum % 2 == 1;
    }
    private static void initial(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = '.';
            }
        }
    }

    private static void showGrid(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
