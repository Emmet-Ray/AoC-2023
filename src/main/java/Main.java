import day13.PointOfIncidence;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String file = "C:\\Users\\Lenovo\\Desktop\\study\\AdventOfCode2023\\src\\main\\resources\\day13\\input.txt";
        try {
            int result = PointOfIncidence.result(file);
            System.out.println(result);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
