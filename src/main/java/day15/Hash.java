package day15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hash {

    public static int result(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String oneLine = reader.readLine();
        String[] allStrings = oneLine.split(",");

        final int NUMBER_BOXES = 256;
        List<Lens>[] boxes = new ArrayList[NUMBER_BOXES];
        for (int i = 0; i < NUMBER_BOXES; i++) {
            boxes[i] = new ArrayList<>();
        }
        for(String s: allStrings) {
            String[] split = null;
            if (s.contains("=")) {
                split = s.split("=");
                String currentLabel = split[0];
                int focalLen = Integer.parseInt(split[1]);
                int currentHash = hash(currentLabel);

                List<Lens> targetBox = boxes[currentHash];
                Lens currentLens = null;
                int i;
                for (i = 0; i < targetBox.size(); i++) {
                    currentLens = targetBox.get(i);
                    if (currentLens.getLabel().equals(currentLabel)) {
                        break;
                    }
                }
                if (i < targetBox.size()) {
                    currentLens.setFocalLength(focalLen);
                } else {
                    targetBox.add(new Lens(currentLabel, focalLen));
                }

            } else if (s.contains("-")) {
                split = s.split("-");
                String currentLabel = split[0];
                int currentHash = hash(currentLabel);

                List<Lens> targetBox = boxes[currentHash];
                Lens currentLens = null;
                int i;
                for (i = 0; i < targetBox.size(); i++) {
                    currentLens = targetBox.get(i);
                    if (currentLens.getLabel().equals(currentLabel)) {
                        break;
                    }
                }
                if (i < targetBox.size()) {
                    targetBox.remove(currentLens);
                }
            }
        }

        int result = calculateResult(boxes);
        return result;
    }

    private static int calculateResult(List<Lens>[] boxes) {
        int len = boxes.length;
        int result = 0;
        for (int i = 0; i < len; i++) {
            List<Lens> currentBox = boxes[i];
            for (int j = 0; j < currentBox.size(); j++) {
                int currentFocalLen = currentBox.get(j).getFocalLength();
                result += ((i + 1) * (j + 1) * currentFocalLen);
            }
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
        return result;
    }
}
