package day2;

public class Range implements Comparable<Range> {

    private final long min;
    private final long max;

    static Range from(String rangeStr) {
        String[] range = rangeStr.split("-");
        long min = Long.parseLong(range[0].trim());
        long max = Long.parseLong(range[1].trim());

        return new Range(min, max);
    }

    private Range(long min, long max) {
        this.min = min;
        this.max = max;
    }

    public long max() {
        return max;
    }

    public long min() {
        return min;
    }

    @Override
    public int compareTo(Range other) {
        int byMin = Long.compare(this.min, other.min);
        if (byMin != 0) {
            return byMin;
        }
        return Long.compare(this.max, other.max);
    }
}
