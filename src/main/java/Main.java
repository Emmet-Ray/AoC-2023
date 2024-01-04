import day4.SumOfScratchCards;
import day5.Seed;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String file = "C:\\Users\\Lenovo\\Desktop\\AdventOfCode2023\\src\\main\\resources\\day5\\input.txt";
        try {
            long result = Seed.smallestLocation(file);
            System.out.println(result);
        } catch (IOException e) {

        }
    }
}
