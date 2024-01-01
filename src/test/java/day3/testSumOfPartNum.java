package day3;

import day3.SumOfPartNum;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class testSumOfPartNum {

    @Test
    public void testSumOfOneLine() {
        int result = SumOfPartNum.sumOfOneLine("467..114..", "...*......", null);
        assertEquals(467, result);
        result = SumOfPartNum.sumOfOneLine("...*......", "..35..633.", null);
        assertEquals(0, result);
        result = SumOfPartNum.sumOfOneLine("..592.....", "......755.", null);
        assertEquals(0, result);
    }

    @Test
    public void testSumOfFile() {
        String file = "C:\\Users\\Lenovo\\Desktop\\AdventOfCode2023\\src\\main\\resources\\day3\\smallInput.txt";
        try {
            int result = SumOfPartNum.sumOfFile(file);
            assertEquals(4361, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
