import day4.SumOfScratchCards;
import day5.Seed;
import day6.BoatGame;
import day7.CamelCards;
import day8.CamelMap;
import day9.Day9;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String file = "C:\\Users\\Lenovo\\Desktop\\study\\AdventOfCode2023\\src\\main\\resources\\day9\\input.txt";
        try {
            int result = Day9.sum(file);
            System.out.println(result);
        } catch (IOException e) {

        }
    }
}
