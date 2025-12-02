package day1;

public class Position {
    private static final int MAX = 100;

    private int pos;

    public Position(int start) {
        this.pos = start;
    }

    public int currentPosition() {
        return pos;
    }

    // Returns the number of times it has moved from 0
    public int move(Rotation rot) {
        int count = 0;
        if (rot.dir() == Direction.LEFT) {
            if (rot.num() >= pos && pos != 0)
                count++;

            count += (-1)*(pos - rot.num()) / MAX;

            System.out.println("[L" +rot.num()+ "] \t" + this.pos + " -> " + Math.floorMod(pos - rot.num(), MAX) + " count: " + count);
            this.pos = Math.floorMod(pos - rot.num(), MAX);
        } else {
            count += (pos + rot.num()) / MAX;

            System.out.println("[R" +rot.num()+ "] \t" + this.pos + " -> " + Math.floorMod(pos + rot.num(), MAX) + " count: " + count);

            this.pos = Math.floorMod(pos + rot.num(), MAX);
        }

        return count;
    }
}
