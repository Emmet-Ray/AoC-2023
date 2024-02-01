package day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Button {
    private static final char FLIP_FLOP = '%';
    private static final char CONJUNCTION = '&';

    private static long lowPulseTimesPerCycyle = 0;
    private static long highPulseTimesPerCycle = 0;


    static Map<String, Module> modules = new HashMap<>();
    static Module broadCaster;

    public static long result(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Pattern pattern = Pattern.compile("[%&]?(?<name>.*) -> (?<targetModules>.*)");
        String currentLine = reader.readLine();
        while (currentLine != null) {
            Matcher matcher = pattern.matcher(currentLine);
            if (matcher.matches()) {
                char type = currentLine.charAt(0);
                String name = matcher.group("name");
                String targetModules = matcher.group("targetModules");
                switch (type) {
                    case FLIP_FLOP -> {
                        FlipFlop flipFlop = new FlipFlop(name, targetModules);
                        modules.put(name, flipFlop);
                        break;
                    }
                    case CONJUNCTION -> {
                        Conjunction conjunction = new Conjunction(name, targetModules);
                        modules.put(name, conjunction);
                        break;
                    }
                    default -> {
                        BroadCast broadCast = new BroadCast(name, targetModules);
                        modules.put(name, broadCast);
                        broadCaster = broadCast;
                        break;
                    }
                }
            }
            currentLine = reader.readLine();
        }

        // the second pass used to record the [input modules] for conjunction modules
        reader = new BufferedReader(new FileReader(file));
        currentLine = reader.readLine();
        while (currentLine != null) {
            Matcher matcher = pattern.matcher(currentLine);
            if (matcher.matches()) {
                String name = matcher.group("name");
                String targetModules = matcher.group("targetModules");
                String[] currentTargetModules = targetModules.split(", ");
                for (String s: currentTargetModules) {
                    Module module = modules.get(s);
                    if (module == null) {
                        continue;
                    }
                    if (module.getModuleType() == ModuleType.CONJUNCTION) {
                        ((Conjunction) module).addInputModules(modules.get(name));
                    }
                }
            }
            currentLine = reader.readLine();
        }

        long result = 0;
        while (!pushButton()) {
            result++;
        }
        return result;
    }

    private static boolean pushButton() {
        lowPulseTimesPerCycyle++;

        Queue<Module> queue = new ArrayDeque<>();
        queue.add(broadCaster);
        while (!queue.isEmpty()) {
            Module currentModule = queue.remove();
            List<String> targets = currentModule.getTargetModules();
            boolean outputPulse = currentModule.getOutPutPulse();
            for (String s: targets) {
                Module currentTarget = modules.get(s);
                if (outputPulse) {
                    highPulseTimesPerCycle++;
                } else {
                    lowPulseTimesPerCycyle++;
                }
                if (currentTarget == null && !outputPulse) {
                    return true;
                } else if (currentTarget == null) {
                    continue;
                }
                switch (currentTarget.getModuleType()) {
                    case FLIP_FLOP -> {
                        FlipFlop flipFlop = (FlipFlop) currentTarget;
                        boolean addToQueue = flipFlop.receivePulse(outputPulse);
                        if (addToQueue) {
                            queue.add(currentTarget);
                        }
                    }
                    case CONJUNCTION -> {
                        Conjunction conjunction = (Conjunction) currentTarget;
                        conjunction.receivePulse(outputPulse);
                        queue.add(currentTarget);
                    }
                }
            }
        }
        return false;
    }

    private static boolean returnToInitialState() {
        for (Module module: modules.values()) {
            switch (module.getModuleType()) {
                case FLIP_FLOP -> {
                    FlipFlop flipFlop = (FlipFlop) module;
                    if (flipFlop.isOn()) {
                        return false;
                    }
                }
                case CONJUNCTION -> {
                    Conjunction conjunction = (Conjunction) module;
                    List<Module> targets = conjunction.getInputModule();
                    for (Module module1: targets) {
                        if (module1.getOutPutPulse()) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
