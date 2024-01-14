package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CamelCards {

    public static long totalWinnings(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Cards> list = new ArrayList<>();
        String currentLine = reader.readLine();
        while (currentLine != null) {
            String[] split = currentLine.split(" +");
            Cards cards = new Cards(split[0], Integer.parseInt(split[1]));
            list.add(cards);
            currentLine = reader.readLine();
        }
        list.sort(new ComparingCards());
        for (Cards c: list) {
            System.out.println(c);
        }
        long result = 0;
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i).getBid() * (i+1);
        }
        return result;
    }
}
