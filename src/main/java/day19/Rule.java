package day19;

public class Rule {
    private String condition;
    private String nextWorkFlow;

    public Rule(String rule) {
        if (rule.contains(":")) {
            String[] split = rule.split(":");
            condition = split[0];
            nextWorkFlow = split[1];
        } else {
            condition = "";
            nextWorkFlow = rule;
        }
    }

    public boolean satisfyCondition(int x, int m, int a, int s) {
        if (condition.isEmpty()) {
            return true;
        }
        char part = condition.charAt(0);
        char comparator = condition.charAt(1);
        int value = Integer.parseInt(condition.substring(2));
        switch (part) {
            case 'x': {
                if (comparator == '>') {
                    return x > value;
                } else {
                    return x < value;
                }
            }
            case 'm': {
                if (comparator == '>') {
                    return m > value;
                } else {
                    return m < value;
                }
            }
            case 'a': {
                if (comparator == '>') {
                    return a > value;
                } else {
                    return a < value;
                }
            }
            case 's': {
                if (comparator == '>') {
                    return s > value;
                } else {
                    return s < value;
                }
            }
        }
        // unreachable
        return false;
    }

    public String getCondition() {
        return condition;
    }

    public String getNextWorkFlow() {
        return nextWorkFlow;
    }

    @Override
    public String toString() {
        if (condition.isEmpty()) {
            return nextWorkFlow;
        } else {
            return condition + ":" + nextWorkFlow;
        }
    }
}
