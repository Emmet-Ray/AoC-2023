import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PointOfIncidence {



    public static int result(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String currentLine = reader.readLine();
        int sum = 0;

        while (currentLine != null) {
            List<String> oneUnit = new ArrayList<>();
            while (currentLine != null && !currentLine.isEmpty()) {
                oneUnit.add(currentLine);
                currentLine = reader.readLine();
            }
            char[][] currentBlock = oneBlock(oneUnit);
            sum += theNumber(currentBlock);

            currentLine = reader.readLine();
        }

        return sum;
    }

    private static int theNumber(char[][] block) {
        int rowNumber = block.length;
        int columnNumber = block[0].length;

        // check row by row
        for (int i = 0; i < rowNumber-1; i++) {
            int current = checkRowSymmetry(block, i, i+1);
            if (current != -1) {
                System.out.println(i + 1);
                return 100 * (i + 1);
            }
        }
        // check col by col
        for (int i = 0; i < columnNumber-1; i++) {
            int current = checkColumnSymmetry(block, i, i + 1);
            if (current != -1) {
                System.out.println(i + 1);
                return i + 1;
            }
        }
        return -1;
    }

    private static int checkColumnSymmetry(char[][] block, int first, int second) {
        int columnNumber = block[0].length;
        int leftIndex = first;
        int rightIndex = second;
        while (true) {
            for (int i = 0; i < block.length; i++) {
                if (block[i][leftIndex] != block[i][rightIndex]) {
                    return -1;
                }
            }

            leftIndex--;
            rightIndex++;
            if (leftIndex < 0 || rightIndex >= columnNumber) {
                return 1;
            }
        }
    }

    private static int checkRowSymmetry(char[][] block, int first, int second) {
        int aboveIndex = first;
        int belowIndex = second;
        while (true) {
            for (int i = 0; i < block[0].length; i++) {
               if (block[aboveIndex][i] != block[belowIndex][i]) {
                   return -1;
               }
            }
            aboveIndex--;
            belowIndex++;
            if (aboveIndex == -1 || belowIndex == block.length) {
                return 1;
            }
        }
    }

    private static char[][] oneBlock(List<String> block) {
        String first = block.get(0);
        int columnLen = first.length();
        int rowLen = block.size();
        char[][] result = new char[rowLen][columnLen];

        for (int i = 0; i < block.size(); i++) {
            result[i] = block.get(i).toCharArray();
        }
        return result;
    }
}
