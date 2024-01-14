import day4.SumOfScratchCards;
import day5.Seed;
import day6.BoatGame;
import day7.CamelCards;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String file = "C:\\Users\\Lenovo\\Desktop\\study\\AdventOfCode2023\\src\\main\\resources\\day7\\input.txt";
        try {
            long result = CamelCards.totalWinnings(file);
            System.out.println(result);
        } catch (IOException e) {

        }
    }
}
