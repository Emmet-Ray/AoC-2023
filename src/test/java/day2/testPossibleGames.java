package day2;

import org.junit.Test;

import static org.junit.Assert.*;

public class testPossibleGames {

    @Test
    public void testPossibleRound() {
        assertTrue(!PossibleGames.possibleRound("8 green, 6 blue, 20 red"));
        assertTrue(!PossibleGames.possibleRound("8 green, 16 blue, 10 red"));
        assertTrue(!PossibleGames.possibleRound("18 green, 6 blue, 10 red"));

        assertTrue(PossibleGames.possibleRound("5 green, 6 blue, 7 red"));
        assertTrue(PossibleGames.possibleRound("12 red, 13 green, 14 blue"));
        assertTrue(PossibleGames.possibleRound(""));
    }


    @Test
    public void testPossibleGame() {
        assertEquals(0, PossibleGames.possibleGame("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"));
        assertEquals(0, PossibleGames.possibleGame("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"));

        assertEquals(1, PossibleGames.possibleGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"));
        assertEquals(2, PossibleGames.possibleGame("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"));
        assertEquals(5, PossibleGames.possibleGame("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"));
    }
}
