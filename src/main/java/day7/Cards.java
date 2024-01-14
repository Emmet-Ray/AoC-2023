package day7;

import java.util.Comparator;

public class Cards{
    private String handOfCards;
    private int bid;

    public Cards(String handOfCards, int bid) {
        this.handOfCards = handOfCards;
        this.bid = bid;
    }

    public int getBid() {
        return bid;
    }

    public String getHandOfCards() {
        return handOfCards;
    }

    @Override
    public String toString() {
        return handOfCards + "  " + bid;
    }
}
