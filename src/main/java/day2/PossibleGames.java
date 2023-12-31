package day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * this could be done more concise by refactoring.
*/
public class PossibleGames {


    private static final int redNum = 12;
    private static final int greenNum = 13;
    private static final int blueNum = 14;

    static int redMin = Integer.MIN_VALUE;
    static int greenMin = Integer.MIN_VALUE;
    static int blueMin = Integer.MIN_VALUE;

    public static int possibleGame(String game) {
        String[] IDRound = game.split(": ");
        String[] id = IDRound[0].split(" ");
        int gameID = Integer.parseInt(id[1]);
        String[] Rounds = IDRound[1].split("; ");
        int result = 0;
        for (String round: Rounds) {
            aRound(round);
        }
        result = redMin * greenMin * blueMin;
        redMin = greenMin = blueMin = Integer.MIN_VALUE;
        return result;
    }

    public static void aRound(String round) {
        String[] result = round.split(", ");
        for (String numberColor: result) {
            if (numberColor.isEmpty()) {
                continue;
            }
            String[] split = numberColor.split(" ");
            int num = Integer.parseInt(split[0]);
            switch (split[1]) {
                case "red": {
                    if (num > redMin) {
                        redMin = num;
                    }
                    break;
                }
                case "green": {
                    if (num > greenMin) {
                        greenMin = num;
                    }
                    break;
                }
                case "blue": {
                    if (num > blueMin) {
                        blueMin = num;
                    }
                    break;
                }
            }
        }
    }

    /**
     *
     * @param round
     * @return true if possible based on the condition above, false otherwise.
     */
    public static boolean possibleRound(String round) {
        String[] result = round.split(", ");
        for (String numberColor: result) {
            System.out.println(numberColor);
            if (numberColor.equals("")) {
                continue;
            }
            String[] split = numberColor.split(" ");
            int num = Integer.parseInt(split[0]);
            switch (split[1]) {
                case "red": {
                    if (num > redNum) {
                        return false;
                    }
                    break;
                }
                case "green": {
                    if (num > greenNum) {
                        return false;
                    }
                    break;
                }
                case "blue": {
                    if (num > blueNum) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }


    /**
     *
     * @param file
     * @return
     */
    public static int sumOfPossibleGameID(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int sum = 0;
        String line = reader.readLine();
        while (line != null) {
            sum += possibleGame(line);
            line = reader.readLine();
        }
        return sum;
    }

    public static void main(String[] args) {
        try {
            int sum = sumOfPossibleGameID("C:\\Users\\Lenovo\\Desktop\\AdventOfCode2023\\src\\test\\java\\day2\\puzzle1_input.txt");
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
