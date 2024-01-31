package day20;

public class BroadCast extends Module {

    public BroadCast(String name, String targetModules) {
        super(name, targetModules);
        this.moduleType = ModuleType.BROADCAST;
    }
}
