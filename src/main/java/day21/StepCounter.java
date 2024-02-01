package day21;

import day16.Energized;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class StepCounter {

    private char[][] grid;
    private Coordinate start = null;

    public StepCounter(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int rowLen = (int) reader.lines().count();
        reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();
        int colLen = currentLine.length();

        grid = new char[rowLen][colLen];
        int rowIndex = 0;
        while (currentLine != null) {
            grid[rowIndex] = currentLine.toCharArray();
            if (start == null) {
                for (int i = 0; i < colLen; i++) {
                    if (grid[rowIndex][i] == 'S') {
                        start = new Coordinate(rowIndex, i);
                    }
                }
            }
            rowIndex++;
            currentLine = reader.readLine();
        }
    }

    public static int result(String file) throws IOException {
        StepCounter stepCounter = new StepCounter(file);
        int steps = 500;
        return stepCounter.countReachableAfter(steps);
    }

    private int countReachableAfter(int steps) {
        Queue<Coordinate> queue = new ArrayDeque<>();
        queue.add(start);
        for (int i = 0; i < steps; i++) {
            Queue<Coordinate> newQueue = new ArrayDeque<>();
            for (Coordinate coordinate: queue) {
                for (Coordinate neighbor: coordinate) {
                    addToQueue(neighbor, newQueue);
                }
            }
            queue = newQueue;
        }
        return queue.size();
    }

    private void addToQueue(Coordinate coordinate, Queue<Coordinate> queue) {
        if (isGardenPlot(coordinate) && !queue.contains(coordinate)) {
            queue.add(coordinate);
        }
    }

    private boolean isGardenPlot(Coordinate coordinate) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int row = positiveRemainder(coordinate.getRow(), rowLen);
        int col = positiveRemainder(coordinate.getCol(), colLen);
        char currentSymbol = grid[row][col];
        return currentSymbol == '.' || currentSymbol == 'S';
    }

    private static int positiveRemainder(int dividend, int divisor) {
        int remainder = dividend % divisor;
        if (remainder < 0) {
            remainder = (remainder + divisor) % divisor;
        }
        return remainder;
    }

    private boolean validCoordinate(Coordinate coordinate) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        boolean validRow = row >= 0 && row < rowLen;
        boolean validCol = col >= 0 && col < colLen;
        return validRow && validCol;
    }
}
