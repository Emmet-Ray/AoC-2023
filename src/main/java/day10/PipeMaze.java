package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.PublicKey;

public class PipeMaze {

    private static int timesToSource = 0;
    private static int rowLen;
    private static int colLen;
    private static int sourceRow;
    private static int sourceColumn;

    private static RecordInfo[][] recordInfos;


    private class RecordInfo {
        private boolean isSource;
        private int rowIndex;
        private int columnIndex;
        private boolean visited;

        private char symbol;
        private boolean west = false;
        private boolean east = false;
        private boolean north = false;
        private boolean south = false;

        private int prevRow;
        private int prevCol;

        private int nextRow;
        private int nextCol;


        private int distanceToSource = Integer.MAX_VALUE;

        public RecordInfo(boolean isSourcep, int rowIndexp, int columnIndexp, boolean visitedp, char symbolp) {
            isSource = isSourcep;
            rowIndex = rowIndexp;
            columnIndex = columnIndexp;
            visited = visitedp;
            symbol = symbolp;
            switch (symbolp) {
                case '|': {
                    north = south = true;
                    break;
                }
                case '-': {
                    west = east = true;
                    break;
                }
                case 'L': {
                    north = east = true;
                    break;
                }
                case 'J': {
                    north = west = true;
                    break;
                }
                case '7': {
                    south = west = true;
                    break;
                }
                case 'F': {
                    south = east = true;
                    break;
                }
                case 'S': {
                    //north = south = west = east = true;
                    // F
                    south = east = true;
                    break;
                }
            }
        }

        public boolean isIsSource() {
            return isSource;
        }

        public int getRowIndex() {
            return rowIndex;
        }

        public int getColumnIndex() {
            return columnIndex;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setPrevRow(int prevRow) {
            this.prevRow = prevRow;
        }

        public void setPrevCol(int prevCol) {
            this.prevCol = prevCol;
        }

        public void setNextRow(int nextRow) {
            this.nextRow = nextRow;
        }

        public void setNextCol(int nextCol) {
            this.nextCol = nextCol;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }
    }


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
        recordInfos = new RecordInfo[rowLength][columnLength];

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                if (maze[i][j] == 'S') {
                    recordInfos[i][j] = new RecordInfo(true, i, j, false, maze[i][j]);
                    sourceRow = i;
                    sourceColumn =j;
                }
                recordInfos[i][j] = new RecordInfo(false, i, j, false, maze[i][j]);
            }
        }

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                System.out.printf("%c", recordInfos[i][j].symbol);
            }
            System.out.println();
        }
        System.out.println(sourceRow + ", " + sourceColumn);

        depthFirstSearch(sourceRow, sourceColumn);

        int distance = 0;
        recordInfos[sourceRow][sourceColumn].distanceToSource = distance++;
        int prevRow = recordInfos[sourceRow][sourceColumn].prevRow;
        int prevCol = recordInfos[sourceRow][sourceColumn].prevCol;
        while (!(prevRow == sourceRow && prevCol == sourceColumn)) {
            System.out.println("(" + prevRow + ", " + prevCol + ")");
            recordInfos[prevRow][prevCol].distanceToSource = distance++;
            int newPrevRow = recordInfos[prevRow][prevCol].prevRow;
            int newPrevCol = recordInfos[prevRow][prevCol].prevCol;
            prevRow = newPrevRow;
            prevCol = newPrevCol;
        }

        distance = 1;
        int nextRow = recordInfos[sourceRow][sourceColumn].nextRow;
        int nextCol = recordInfos[sourceRow][sourceColumn].nextCol;
        while (!(nextRow == sourceRow && nextCol == sourceColumn)) {
            if (recordInfos[nextRow][nextCol].distanceToSource >= distance) {
                recordInfos[nextRow][nextCol].distanceToSource = distance;
            }
            distance++;
            int newnextRow = recordInfos[nextRow][nextCol].nextRow;
            int newnextCol = recordInfos[nextRow][nextCol].nextCol;
            nextRow = newnextRow;
            nextCol = newnextCol;
        }


        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < columnLength; j++) {
                //System.out.print(recordInfos[i][j].isVisited() + " ");
                if (recordInfos[i][j].distanceToSource == Integer.MAX_VALUE) {
                    System.out.print('.');
                } else {
                    System.out.print(recordInfos[i][j].distanceToSource);
                }
            }
            System.out.println();
        }
        return 0;
    }

    private static void depthFirstSearch(int startRow, int startColumn) {
        recordInfos[startRow][startColumn].setVisited(true);
        if (over(startRow, startColumn)) {
            return;
        }

        int nextRow = startRow - 1;
        int nextColumn = startColumn;
        if (validIndex(nextRow, nextColumn) && couldBeNextTile(startRow, startColumn, nextRow, nextColumn, Direction.N)) {
            recordInfos[nextRow][nextColumn].setPrevRow(startRow);
            recordInfos[nextRow][nextColumn].setPrevCol(startColumn);
            recordInfos[startRow][startColumn].setNextRow(nextRow);
            recordInfos[startRow][startColumn].setNextCol(nextColumn);

            depthFirstSearch(nextRow, nextColumn);
            if (over(startRow, startColumn)) {
                return;
            }
        }

        nextRow = startRow + 1;
        nextColumn = startColumn;
        if (validIndex(nextRow, nextColumn) && couldBeNextTile(startRow, startColumn, nextRow, nextColumn, Direction.S)) {
            recordInfos[nextRow][nextColumn].setPrevRow(startRow);
            recordInfos[nextRow][nextColumn].setPrevCol(startColumn);
            recordInfos[startRow][startColumn].setNextRow(nextRow);
            recordInfos[startRow][startColumn].setNextCol(nextColumn);

            depthFirstSearch(nextRow, nextColumn);
            if (over(startRow, startColumn)) {
                return;
            }
        }

        nextRow = startRow;
        nextColumn = startColumn - 1;
        if (validIndex(nextRow, nextColumn) && couldBeNextTile(startRow, startColumn, nextRow, nextColumn, Direction.W)) {
            recordInfos[nextRow][nextColumn].setPrevRow(startRow);
            recordInfos[nextRow][nextColumn].setPrevCol(startColumn);
            recordInfos[startRow][startColumn].setNextRow(nextRow);
            recordInfos[startRow][startColumn].setNextCol(nextColumn);

            depthFirstSearch(nextRow, nextColumn);
            if (over(startRow, startColumn)) {
                return;
            }
        }

        nextRow = startRow;
        nextColumn = startColumn + 1;
        if (validIndex(nextRow, nextColumn) && couldBeNextTile(startRow, startColumn, nextRow, nextColumn, Direction.E)) {
            recordInfos[nextRow][nextColumn].setPrevRow(startRow);
            recordInfos[nextRow][nextColumn].setPrevCol(startColumn);
            recordInfos[startRow][startColumn].setNextRow(nextRow);
            recordInfos[startRow][startColumn].setNextCol(nextColumn);

            depthFirstSearch(nextRow, nextColumn);
            if (over(startRow, startColumn)) {
                return;
            }
        }

        recordInfos[startRow][startColumn].setVisited(false);
    }


    private static boolean couldBeNextTile(int currentRow, int currentCol, int nextRow, int nextCol, Direction direction) {
        RecordInfo current = recordInfos[currentRow][currentCol];
        RecordInfo next = recordInfos[nextRow][nextCol];
        if (current.prevRow == nextRow && current.prevCol == nextCol) {
            return false;
        }
        if (current.north && next.south && direction == Direction.N || current.south && next.north && direction == Direction.S) {
            return true;
        }
        if (current.west && next.east && direction == Direction.W || current.east && next.west && direction == Direction.E) {
            return true;
        }
        return false;
    }

    private static boolean validIndex(int row, int col) {
        return row >= 0 && row < rowLen && col >= 0 && col < colLen;
    }
    private static boolean over(int row, int col) {
        if (row == sourceRow && col == sourceColumn) {
            timesToSource++;
        }
        return timesToSource >= 2;
    }

}
