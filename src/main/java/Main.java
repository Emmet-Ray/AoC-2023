import day4.SumOfScratchCards;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String file = "C:\\Users\\Lenovo\\Desktop\\AdventOfCode2023\\src\\main\\resources\\day4\\input.txt";
        try {
            int result = SumOfScratchCards.Cards(file);
            System.out.println(result);
        } catch (IOException e) {

        }
    }
}
