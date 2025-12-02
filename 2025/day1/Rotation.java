package day1;

public class Rotation {
    private final Direction dir;
    private final int num;

    private Rotation(Direction dir, int num) {
        this.dir = dir;
        this.num = num;
    }

    static Rotation from(String line) {
        char rotation = line.charAt(0);
        return new Rotation(rotation == 'L' ? Direction.LEFT : Direction.RIGHT, Integer.parseInt(line.split(String.valueOf(rotation))[1]));
    }

    public Direction dir() {
        return dir;
    }

    public int num() {
        return num;
    }
}
