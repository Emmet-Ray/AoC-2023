package day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Hash {

    public static int result(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String oneLine = reader.readLine();
        String[] allStrings = oneLine.split(",");
        int result = 0;
        for(String s: allStrings) {
            int currentHash = hash(s);
            result += currentHash;
        }
        return result;
    }

    private static int hash(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            result += c;
            result *= 17;
            result %= 256;
        }
        System.out.println(s + "  " + result);
        return result;
    }
}
