package day19;

public class AcceptedRatings {

    private Range x;
    private Range m;
    private Range a;
    private Range s;
    public AcceptedRatings(int min, int max) {
        x = new Range(min, max);
        m = new Range(min, max);
        a = new Range(min, max);
        s = new Range(min, max);
    }
}
