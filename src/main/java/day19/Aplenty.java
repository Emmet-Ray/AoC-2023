package day19;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aplenty {

    private Map<String, List<Rule>> workFlows;

    public static int result(String file) throws IOException {
        Aplenty aplenty = new Aplenty(file);
        return aplenty.acceptedSum(file);
    }
    public Aplenty(String file) throws IOException {
        workFlows = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Pattern pattern = Pattern.compile("(?<workFlowName>.*)\\{(?<rules>.*)}");
        String currentLine = reader.readLine();
        while (!currentLine.isEmpty()) {
            Matcher matcher = pattern.matcher(currentLine);
            if (matcher.matches()) {
                String workFlowName = matcher.group("workFlowName");
                String rules = matcher.group("rules");
                workFlows.put(workFlowName, getRules(rules));
            }
            currentLine = reader.readLine();
        }
        //showWorkFlows();
    }

    public int acceptedSum(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();
        while (!currentLine.isEmpty()) {
            currentLine = reader.readLine();
        }

        int result = 0;
        currentLine = reader.readLine();
        Pattern pattern = Pattern.compile("\\{x=(?<x>.*),m=(?<m>.*),a=(?<a>.*),s=(?<s>.*)}");
        while (currentLine != null) {
            Matcher matcher = pattern.matcher(currentLine);
            if (matcher.matches()) {
                int x = Integer.parseInt(matcher.group("x"));
                int m = Integer.parseInt(matcher.group("m"));
                int a = Integer.parseInt(matcher.group("a"));
                int s = Integer.parseInt(matcher.group("s"));
                if (accepted(x, m, a, s)) {
                    result += (x + m + a + s);
                }
            }
            currentLine = reader.readLine();
        }
        return result;
    }

    private boolean accepted(int x, int m, int a, int s) {
        String beginWorkFlow = "in";
        Rule currentRule = null;
        boolean over = false;
        while (true) {
            System.out.print(beginWorkFlow + " -> ");
            List<Rule> currentRules = workFlows.get(beginWorkFlow);
            for (int i = 0; i < currentRules.size(); i++) {
                currentRule = currentRules.get(i);
                String currentWorkFlow = currentRule.getNextWorkFlow();
                if (!currentRule.satisfyCondition(x, m, a, s)) {
                    continue;
                } else {
                    if (currentWorkFlow.equals("A") || currentWorkFlow.equals("R")) {
                        over = true;
                        break;
                    }
                    beginWorkFlow = currentWorkFlow;
                    break;
                }
            }
            if (over) {
                break;
            }
        }
        System.out.print(currentRule.getNextWorkFlow());
        System.out.println();

        if (currentRule.getNextWorkFlow().equals("A")) {
            return true;
        } else {
            return false;
        }
    }

    private List<Rule> getRules(String rules) {
        String[] split = rules.split(",");
        List<Rule> result = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            result.add(new Rule(split[i]));
        }
        return result;
    }

    private void showWorkFlows() {
        for (Map.Entry<String, List<Rule>> entry : workFlows.entrySet()) {
            String key = entry.getKey();
            List<Rule> value = entry.getValue();
            System.out.println(key + "{" + value + "}");
        }
    }

}
