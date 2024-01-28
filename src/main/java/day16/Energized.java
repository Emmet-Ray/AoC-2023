package day16;

public class Energized {
    private boolean energnized;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    public Energized() {
        energnized = up = down = left = right = false;
    }

    public boolean alreadyTraverse(Direction direction) {
        switch (direction) {
            case UP -> {
                return up;
            }
            case DOWN -> {
                return down;
            }
            case LEFT -> {
                return left;
            }
            case RIGHT -> {
                return right;
            }
        }
        return false;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setEnergnized(boolean energnized) {
        this.energnized = energnized;
    }

    public boolean isEnergnized() {
        return energnized;
    }
    public void initialize() {
        energnized = left = right = up = down = false;
    }
}
