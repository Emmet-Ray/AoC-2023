package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class PipeMaze {

    private static int rowLen;
    private static int colLen;

    private static Coordinate source;
    private static Coordinate prev;
    private static int sourceRow;
    private static int sourceColumn;
    private static int prevRow;
    private static int prevCol;


    public static int farthestSteps(final String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        rowLen = (int) reader.lines().count();
        reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();
        colLen = currentLine.length();
        char[][] maze = new char[rowLen][colLen];
        int rowIndex = 0;
        while (currentLine != null) {
            maze[rowIndex] = currentLine.toCharArray();
            rowIndex++;
            currentLine = reader.readLine();
        }

        return new PipeMaze().helperForFarthestSteps(maze);
    }

    private int helperForFarthestSteps(final char[][] maze) {
        int rowLength = maze.length;
        int columnLength = maze[0].length;

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                if (maze[i][j] == 'S') {
                    source = new Coordinate(i, j);
                }
            }
        }

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                System.out.printf("%c", maze[i][j]);
            }
            System.out.println();
        }
        //System.out.println(sourceRow + ", " + sourceColumn);

        // find the next step from source
        Coordinate next = new Coordinate(source.getRow(), source.getCol() + 1);
        prev = new Coordinate(source.getRow(), source.getCol());
        updatePrevCoordinate(source);

        int result = (1 + aStep(maze, next)) / 2;
        return result;
    }

    private static int times = 0;

    private static int aStep(char[][] maze, Coordinate current) {
        char c = maze[current.getRow()][current.getCol()];

        int steps = 0;
        while (c != 'S') {
            //System.out.print(c + "  ");
            NextTiles nextTiles = NextTiles.nextTiles(maze, current);
            Coordinate first = nextTiles.getFirst();
            if (validCoordinate(first)) {
                updatePrevCoordinate(current);
                current = first;
            } else {
                updatePrevCoordinate(current);
                current = nextTiles.getSecond();
            }
            c = maze[current.getRow()][current.getCol()];
            steps++;
        }
        System.out.println(c);
        return steps;
        /*
        // recursive version would result in StackOver Flow
        NextTiles nextTiles = NextTiles.nextTiles(maze, current);
        Coordinate first = nextTiles.getFirst();

        if (validCoordinate(first)) {
            updatePrevCoordinate(current);
            return 1 + aStep(maze, first);
        } else {
            Coordinate second = nextTiles.getSecond();
            updatePrevCoordinate(current);
            return 1 + aStep(maze, second);
        }
         */
    }


    private static void updatePrevCoordinate(Coordinate coordinate) {
        prev.setRow(coordinate.getRow());
        prev.setCol(coordinate.getCol());
    }

    private static boolean validCoordinate(Coordinate coordinate) {
        return !prev.equals(coordinate) && coordinate.getRow() >= 0 && coordinate.getRow() < rowLen &&
                coordinate.getCol() >= 0 && coordinate.getCol() < colLen;
    }
}
