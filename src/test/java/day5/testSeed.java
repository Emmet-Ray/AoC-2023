package day5;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testSeed {

    @Test
    public void testSrtToDst() {
        Seed seed = new Seed();
        Seed.aMap example = new Seed.aMap(98, 50, 2);
        long result = example.srcToDst(98);
        assertEquals(50, result);
        result = example.srcToDst(50);
        assertEquals(-1, result);

        Seed.aMap example1 =new Seed.aMap(50, 52, 48);
        result = example1.srcToDst(50);
        assertEquals(52, result);
        result = example1.srcToDst(49);
        assertEquals(-1, result);
    }
}
