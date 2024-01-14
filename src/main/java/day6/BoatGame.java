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
    public static long numberOfWaysToWin(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String timeLine = reader.readLine();
        String distanceLine = reader.readLine();
        String[] time = timeLine.split(": +");
        String[] distance = distanceLine.split(": +");
        String[] splitTime = time[1].split(" +");
        String[] splitDistance = distance[1].split(" +");

        long result = 1;
        StringBuilder realTime = new StringBuilder();
        StringBuilder realDistance = new StringBuilder();
        for (int i = 0; i < splitTime.length; i++) {
            realTime.append(splitTime[i]);
            realDistance.append(splitDistance[i]);
        }
        result = waysToWin(Long.parseLong(realTime.toString()), Long.parseLong(realDistance.toString()));
        return result;
    }

    private static long waysToWin(long time, long recordDistance) {
        long count = 0;
        // i stands for hold up time
        for (long i = 0; i < time; i++) {
            long distance = i * (time - i);
            if (distance > recordDistance) {
                count++;
            }
        }
        return count;
    }
}
