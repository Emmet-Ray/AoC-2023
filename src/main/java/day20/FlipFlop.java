package day20;

import java.util.List;

public class FlipFlop extends Module {
    private boolean on;

    public FlipFlop(String name, String targetModules) {
        super(name, targetModules);
        this.on = false;
        this.moduleType = ModuleType.FLIP_FLOP;
    }

    public boolean receivePulse(boolean highPulse) {
        if (!highPulse) {
            this.on = !on;
            if (this.on) {
                this.outPutPulse = true;
            } else {
                this.outPutPulse = false;
            }
            return true;
        }
        return false;
    }

    public boolean isOn() {
        return on;
    }
}
