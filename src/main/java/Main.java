import day13.PointOfIncidence;
import day14.TiltPlatform;
import day15.Hash;
import day16.Contraption;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String file = "C:\\Users\\Lenovo\\Desktop\\study\\AdventOfCode2023\\src\\main\\resources\\day16\\input.txt";
        try {
            int result = Contraption.result(file);
            System.out.println("result:  " + result);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
