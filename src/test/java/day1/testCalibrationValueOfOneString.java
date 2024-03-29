package day1;


import org.junit.Test;
import static org.junit.Assert.*;

public class testCalibrationValueOfOneString {


   // the following examples are from the website
    @Test
   public void testExamples() {
       assertEquals(12, SumOfCalibrationValues.calibrationValueOfOneString("1abc2"));
       assertEquals(38, SumOfCalibrationValues.calibrationValueOfOneString("pqr3stu8vwx"));
       assertEquals(15, SumOfCalibrationValues.calibrationValueOfOneString("a1b2c3d4e5f"));
       assertEquals(77, SumOfCalibrationValues.calibrationValueOfOneString("treb7uchet"));
   }

    /**
     * three partition :
     * length 3, 4, 5
     */
   @Test
   public void testLetterDigit() {
       int result = SumOfCalibrationValues.LetterDigit("one", 0);
       assertEquals(1, result);
       result = SumOfCalibrationValues.LetterDigit("twodaf", 0);
       assertEquals(2, result);
       result = SumOfCalibrationValues.LetterDigit("six", 0);
       assertEquals(6, result);

       result = SumOfCalibrationValues.LetterDigit("four", 0);
       assertEquals(4, result);
       result = SumOfCalibrationValues.LetterDigit("five", 0);
       assertEquals(5, result);
       result = SumOfCalibrationValues.LetterDigit("nine", 0);
       assertEquals(9, result);

       result = SumOfCalibrationValues.LetterDigit("three", 0);
       assertEquals(3, result);
       result = SumOfCalibrationValues.LetterDigit("seven", 0);
       assertEquals(7, result);
       result = SumOfCalibrationValues.LetterDigit("eight", 0);
       assertEquals(8, result);

   }

   @Test
    public void testDay1Puzzle2() {
       assertEquals(29, SumOfCalibrationValues.calibrationValueOfOneString("two1nine"));
       assertEquals(83, SumOfCalibrationValues.calibrationValueOfOneString("eightwothree"));
       assertEquals(13, SumOfCalibrationValues.calibrationValueOfOneString("abcone2threexyz"));
       assertEquals(24, SumOfCalibrationValues.calibrationValueOfOneString("xtwone3four"));
       assertEquals(42, SumOfCalibrationValues.calibrationValueOfOneString("4nineeightseven2"));
       assertEquals(14, SumOfCalibrationValues.calibrationValueOfOneString("zoneight234"));
       assertEquals(76, SumOfCalibrationValues.calibrationValueOfOneString("7pqrstsixteen"));

       assertEquals(38, SumOfCalibrationValues.calibrationValueOfOneString("three7938"));
       assertEquals(92, SumOfCalibrationValues.calibrationValueOfOneString("nine92jnhgqzctpgbcbpz"));

       assertEquals(22, SumOfCalibrationValues.calibrationValueOfOneString("two"));
       assertEquals(22, SumOfCalibrationValues.calibrationValueOfOneString("2"));
       assertEquals(23, SumOfCalibrationValues.calibrationValueOfOneString("twothree"));



       // this test case is the tricky one
       assertEquals(28, SumOfCalibrationValues.calibrationValueOfOneString("twothreeight"));
   }
}
