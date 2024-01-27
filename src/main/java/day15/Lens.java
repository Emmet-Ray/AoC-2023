package day15;

public class Lens {

    private String label;
    private int focalLength;

    public Lens(String label, int focalLength) {
        this.label = label;
        this.focalLength = focalLength;
    }

    public String getLabel() {
        return label;
    }

    public int getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(int focalLength) {
        this.focalLength = focalLength;
    }
}
