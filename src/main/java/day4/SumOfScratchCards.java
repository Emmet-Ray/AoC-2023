package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SumOfScratchCards {


    /**
     * return the points [card] winning
     * @param card
     * @return
     */
    public static int oneCard(String card) {
        String[] split1 = card.split(": ");
        String[] split2 = split1[1].split(" \\| ");
        String[] winningNumber = split2[0].split(" ");
        String[] myNumber = split2[1].split(" ");

        Set<Integer> winNumber = new HashSet<>();
        int numOfMyWinningCards = 0;
        for (String s: winningNumber) {
            if (s.isEmpty()) {
                continue;
            }
            winNumber.add(Integer.parseInt(s));
        }
        for (String s: myNumber) {
            if (s.isEmpty()) {
                continue;
            }
            int curNumber = Integer.parseInt(s);
            if (winNumber.contains(curNumber)) {
                numOfMyWinningCards++;
            }
        }
        return numOfMyWinningCards == 0 ? 0 : (int)Math.pow(2, numOfMyWinningCards - 1);
    }

    /**
     * read cards from file
     * @param file
     * @return sum of winning points
     */
    public static int Cards(String file) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int sum = 0;
        String card = reader.readLine();
        while (card != null) {
            sum += oneCard(card);
            card = reader.readLine();
        }
        return sum;
    }

    public static void main(String[] args) {
        String file = "C:\\Users\\Lenovo\\Desktop\\AdventOfCode2023\\src\\main\\resources\\day4\\smallInput.txt";
        try {
            int result = Cards(file);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
