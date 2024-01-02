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
     *
     * @param card
     * @return number of my winning cards
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
        return numOfMyWinningCards;
    }



    private static final int NUM_OF_WINNING_CARDS = 10;
    /**
     * @param file
     * @return
     */
    public static int Cards(String file) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int sum = 0;
        String card = reader.readLine();
        int curWinningCardNum = 0;
        int[] copies = new int[NUM_OF_WINNING_CARDS];
        while (card != null) {
            int curRealNum = 1 + copies[0];
            sum += curRealNum;
            for (int i = 0; i < NUM_OF_WINNING_CARDS - 1; i++) {
                copies[i] = copies[i + 1];
            }
            // don't miss this line
            copies[NUM_OF_WINNING_CARDS - 1] = 0;

            curWinningCardNum = oneCard(card);

            for (int i = 0; i < curWinningCardNum; i++) {
                copies[i] += curRealNum;
            }
            card = reader.readLine();
        }
        return sum;
    }
}
