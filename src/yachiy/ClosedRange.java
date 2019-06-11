package yachiy;

public class ClosedRange {
    private int lowerEndpoint;
    private int upperEndpoint;

    public ClosedRange(int lowerEndpoint, int upperEndpoint) {
        if (lowerEndpoint > upperEndpoint) {
            throw new IllegalArgumentException(String.format("%s>%s", lowerEndpoint, upperEndpoint));
        }
        this.lowerEndpoint = lowerEndpoint;
        this.upperEndpoint = upperEndpoint;
    }

    public boolean contains(int i) {
        return lowerEndpoint <= i && i <= upperEndpoint;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]", lowerEndpoint, upperEndpoint);
    }


}
