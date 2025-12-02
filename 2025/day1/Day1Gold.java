package day1;

import java.util.ArrayList;


public class Day1Gold {
    private final ArrayList<String> linesFromFile;

    public Day1Gold(ArrayList<String> lines) {
        this.linesFromFile = lines;
    }

    public void execute() {
        int count = 0;
        Position dial = new Position(50);

        for (String line : linesFromFile) {
            Rotation rotation = Rotation.from(line);
            count += dial.move(rotation);

            //if (dial.currentPosition() == 0)
                //count++;
        }

        System.out.println("Last position: " + dial.currentPosition());
        System.out.println("Count of 0s: " + count);
    }

    public String name() {
        return "Day 1 Gold";
    }
}