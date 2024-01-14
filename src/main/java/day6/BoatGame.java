package day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class BoatGame {

    /**
     *
     * @param file input file with fixed format
     * @return same as method name
     */
    public static int numberOfWaysToWin(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String timeLine = reader.readLine();
        String distanceLine = reader.readLine();
        String[] time = timeLine.split(": +");
        String[] distance = distanceLine.split(": +");
        String[] splitTime = time[1].split(" +");
        String[] splitDistance = distance[1].split(" +");

        int result = 1;
        for (int i = 0; i < splitTime.length; i++) {
            int currentTime = Integer.parseInt(splitTime[i]);
            int currentRecord = Integer.parseInt(splitDistance[i]);
            result *= waysToWin(currentTime, currentRecord);
        }
        return result;
    }

    private static int waysToWin(int time, int recordDistance) {
        int count = 0;
        // i stands for hold up time
        for (int i = 0; i < time; i++) {
            int distance = i * (time - i);
            if (distance > recordDistance) {
                count++;
            }
        }
        return count;
    }
}
