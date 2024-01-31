package day20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Module {
    String name;
    List<String> targetModules;
    ModuleType moduleType;

    boolean outPutPulse;

    public Module(String name, String targetModules) {
        this.name = name;
        this.targetModules = new ArrayList<>();
        this.outPutPulse = false;
        String[] modules = targetModules.split(", ");
        this.targetModules.addAll(Arrays.asList(modules));
    }
    public String getName() {
        return name;
    }

    public List<String> getTargetModules() {
        return targetModules;
    }

    public ModuleType getModuleType() {
        return moduleType;
    }

    public boolean getOutPutPulse() {
        return outPutPulse;
    }

    @Override
    public String toString() {
        return moduleType + " " + name + " -" + outPutPulse +  " -> " + targetModules;
    }

}
