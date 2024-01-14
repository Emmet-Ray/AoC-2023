package day7;

import java.security.KeyStore;
import java.util.*;
import java.util.Map.Entry;
public class ComparingCards implements Comparator {

    private static final Map<Character, Integer> cardToValue = Map.ofEntries(
            Map.entry('2', 0),
            Map.entry('3', 1),
            Map.entry('4', 2),
            Map.entry('5', 3),
            Map.entry('6', 4),
            Map.entry('7', 5),
            Map.entry('8', 6),
            Map.entry('9', 7),
            Map.entry('T', 8),
            Map.entry('J', -1), // change to -1 less than value of '2'
            Map.entry('Q', 10),
            Map.entry('K', 11),
            Map.entry('A', 12)
    );

    private static HandType handType(String handOfCard) {
        Map<Character, Integer> map = new HashMap();
        int numberOfJokers = 0;
        for (int i = 0; i < handOfCard.length(); i++) {
            char currentCard = handOfCard.charAt(i);
            if (currentCard == 'J') {
                numberOfJokers++;
                continue;
            }
            if (map.containsKey(currentCard)) {
                map.put(currentCard, map.get(currentCard) + 1);
            } else {
                map.put(currentCard, 1);
            }
        }
        if (numberOfJokers != 0) {
            // maximize the rank of the hand
            char targetCard = 'J';
            int maxNumber = 0;
            for (Map.Entry<Character, Integer> entry: map.entrySet()) {
                char currentCard = entry.getKey();
                int currentNumber = entry.getValue();
                if (currentNumber > maxNumber) {
                    targetCard = currentCard;
                    maxNumber = currentNumber;
                } else if (currentNumber == maxNumber) {
                    if (compareCard(currentCard, targetCard) > 0){
                        targetCard = currentCard;
                    }
                }
            }
            if (targetCard == 'J') {
                map.put(targetCard, numberOfJokers);
            } else {
                map.put(targetCard, map.get(targetCard) + numberOfJokers);
            }
        }

        int numberOfKind = map.size();
        switch (numberOfKind) {
            case 1: return HandType.FIVE;
            case 2: {
                for (Map.Entry<Character, Integer> entry: map.entrySet()) {
                    int number = entry.getValue();
                    if (number == 4) {
                        return HandType.FOUR;
                    }
                }
                return HandType.FULL_HOUSE;
            }
            case 3: {
                for (Map.Entry<Character, Integer> entry: map.entrySet()) {
                    int number = entry.getValue();
                    if (number == 3) {
                        return HandType.THREE;
                    }
                }
                return HandType.TWO_PAIR;
            }
            case 4: return HandType.ONE_PAIR;
            case 5: return HandType.HIGH_CARD;
        }

        // unreachable
        throw new UnsupportedOperationException();
    }

    private static int compareCard(char card1, char card2) {
        return cardToValue.get(card1) - cardToValue.get(card2);
    }

    @Override
    public int compare(Object o1, Object o2) {
        Cards c1 = (Cards) o1;
        Cards c2 = (Cards) o2;
        HandType type1 = ComparingCards.handType(c1.getHandOfCards());
        HandType type2 = ComparingCards.handType(c2.getHandOfCards());

        int compare = type1.compareTo(type2);
        if (compare == 0) {
            String cards1 = c1.getHandOfCards();
            String cards2 = c2.getHandOfCards();
            for (int i = 0; i < cards1.length(); i++) {
                int result = ComparingCards.compareCard(cards1.charAt(i), cards2.charAt(i));
                if (result != 0) {
                    return result;
                }
            }
            return 0;
        } else {
            return compare;
        }
    }
}
