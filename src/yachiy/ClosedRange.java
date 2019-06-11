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

    public boolean contains(int value) {
        return lowerEndpoint <= value && value <= upperEndpoint;
    }

    public boolean contains(ClosedRange other) {
        return lowerEndpoint <= other.lowerEndpoint && other.upperEndpoint <= upperEndpoint;
    }

    public boolean isEqual(ClosedRange other) {
        return lowerEndpoint == other.lowerEndpoint && upperEndpoint == other.upperEndpoint;
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]", lowerEndpoint, upperEndpoint);
    }

}
