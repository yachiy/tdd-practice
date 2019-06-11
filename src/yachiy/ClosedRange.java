package yachiy;

public class ClosedRange {
    private int lowerEndpoint;
    private int upperEndpoint;

    public ClosedRange(int lowerEndpoint, int upperEndpoint) {
        this.lowerEndpoint = lowerEndpoint;
        this.upperEndpoint = upperEndpoint;
    }

    @Override
    public String toString() {
        return "["+  lowerEndpoint + "," + upperEndpoint + "]" ;
    }
}
