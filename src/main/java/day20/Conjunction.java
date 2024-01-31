package day20;

import java.util.ArrayList;
import java.util.List;

public class Conjunction extends Module {

    private List<Module> inputModule;
    public Conjunction(String name, String targetModules) {
        super(name, targetModules);
        this.moduleType = ModuleType.CONJUNCTION;
        this.inputModule = new ArrayList<>();
    }

    public void receivePulse(boolean highPulse) {
        boolean newOotPutPulse = false;
        for (Module module: inputModule) {
            if (!module.getOutPutPulse()) {
                newOotPutPulse = true;
            }
        }
        outPutPulse = newOotPutPulse;
    }

    public void addInputModules(Module module) {
        inputModule.add(module);
    }

    public List<Module> getInputModule() {
        return inputModule;
    }
}
