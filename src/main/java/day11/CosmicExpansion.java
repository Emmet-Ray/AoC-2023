package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CosmicExpansion {

    private static char[][] universe;

    private static int rowLen;
    private static int colLen;
    private static boolean[] rowWidthExpaned;
    private static boolean[] colWidthExpaned;


    public static long sumOfShortestPaths(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));


        rowLen = (int) reader.lines().count();
        reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();
        colLen = currentLine.length();
        universe = new char[rowLen][colLen];
        int rowIndex = 0;
        while (currentLine != null) {
            universe[rowIndex] = currentLine.toCharArray();
            rowIndex++;
            currentLine = reader.readLine();
        }

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                System.out.print(universe[i][j]);
            }
            System.out.println();
        }

        long result = helper();
        return result;
    }

    private static long helper() {

        List<Coordinate> galaxies = new ArrayList<>();
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                char c = universe[i][j];
                if (isGalaxy(c)) {
                    galaxies.add(new Coordinate(i, j));
                }
            }
        }
        setUpRowWidth();
        setUpColWidth();

        long result = 0;
        for (int i = 0; i < galaxies.size(); i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                result += shortestPath(galaxies, i, j);
            }
        }

        return result;
    }

    private static long shortestPath(List<Coordinate> galaxies, int firstIndex, int secondIndex) {
        Coordinate firstGalaxy = galaxies.get(firstIndex);
        Coordinate secondGalaxy = galaxies.get(secondIndex);

        final int expansionTimes = 1000000;

        // the base case excluding expansion
        long result = Math.abs(firstGalaxy.getRow() - secondGalaxy.getRow()) + Math.abs(firstGalaxy.getCol() - secondGalaxy.getCol());

        int lowRow = Math.min(firstGalaxy.getRow(), secondGalaxy.getRow());
        int hightRow = Math.max(firstGalaxy.getRow(), secondGalaxy.getRow());
        for (int i = lowRow; i < hightRow; i++) {
            if (rowWidthExpaned[i]) {
                result += (expansionTimes - 1);
            }
        }

        int lowCol = Math.min(firstGalaxy.getCol(), secondGalaxy.getCol());
        int hightCol = Math.max(firstGalaxy.getCol(), secondGalaxy.getCol());
        for (int i = lowCol; i < hightCol; i++) {
            if (colWidthExpaned[i]) {
                result += (expansionTimes - 1);
            }
        }
        System.out.println(firstGalaxy + "--" + secondGalaxy + ": " + result);
        return result;
    }

    private static boolean isGalaxy(char c) {
        return c == '#';
    }
    private static void setUpRowWidth() {
        rowWidthExpaned = new boolean[rowLen];
        for (int i = 0; i < rowLen; i++) {
            int j;
            for (j = 0; j < colLen; j++) {
                if (isGalaxy(universe[i][j])) {
                    rowWidthExpaned[i] = false;
                    break;
                }
            }
            if (j == colLen) {
                rowWidthExpaned[i] = true;
            }
        }
    }
    private static void setUpColWidth() {
        colWidthExpaned = new boolean[colLen];
        for (int i = 0; i < colLen; i++) {
            int j;
            for (j = 0; j < rowLen; j++) {
                if (isGalaxy(universe[j][i])) {
                    colWidthExpaned[i] = false;
                    break;
                }
            }
            if (j == rowLen) {
                colWidthExpaned[i] = true;
            }
        }
    }


}
