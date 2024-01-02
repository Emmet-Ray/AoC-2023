package day4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testSumOfScratchCards {

    /**
     *  partitioning dimension:
     *  winning number: 0, 1, all winning number
     *  winning number place:
     */

    @Test
    public void testOneCard() {
        int result = SumOfScratchCards.oneCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53");
        assertEquals(8, result);
        // all winning number
        result = SumOfScratchCards.oneCard("Card 1: 41 48 83 86 17 | 83 86 41 31 17  9 48 53");
        assertEquals(16, result);
        // 0
        result = SumOfScratchCards.oneCard("Card 1: 41 48 83 86 17 | 3 6  6 1 7  9 8 53");
        assertEquals(0, result);
        // 1
        result = SumOfScratchCards.oneCard("Card 1: 41 48 83 86 17 | 83 6  6 1 7  9 8 53");
        assertEquals(1, result);
    }
}
