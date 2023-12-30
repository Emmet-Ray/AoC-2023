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
}
