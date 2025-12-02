package day2;

import day1.Position;
import day1.Rotation;

import java.util.ArrayList;
import java.util.Collections;


public class Day2Silver {
    private final ArrayList<String> linesFromFile;

    public Day2Silver(ArrayList<String> lines) {
        this.linesFromFile = lines;
    }

    public void execute() {
        long count = 0;
        long max = -1;

        // Prepare algorithm
        System.out.println("Start: Preparing and reading ranges");
        ArrayList<Range> ranges = new ArrayList<>();
        String[] rangesStr = linesFromFile.getFirst().split(",");
        for (String rangeStr : rangesStr) {
            Range range = Range.from(rangeStr);

            if (range.max() > max) {
                max = range.max();
            }

            ranges.add(range);
        }
        Collections.sort(ranges);

        for (Range range : ranges) {
            System.out.println("Range " + range.min() + "-" + range.max());
        }


        System.out.println("End: Preparing and reading ranges");

        // Calculate all invalid ids
        System.out.println("Start: Calculating invalid ids");
        ArrayList<Id> invalidIds = new ArrayList<>();

        for (long i = 0; i <= max; i++) {
            if (i % 100000000 == 0) {
                System.out.println("[DEBUG] Progress: " + i + " / " + max);
            }

            Id id = Id.from(i);

            if (id.isInvalid()) {
                invalidIds.add(id);
            }
        }
        System.out.println("End: Calculating invalid ids");

        // Execute algorithm
        System.out.println("Start: Executing algorithm");
        for (Range range : ranges) {
            // System.out.println("Range " + range.min() + "-" + range.max());
            for (Id invalidId : invalidIds) {
                if (range.min() > invalidId.value()) {
                    continue;
                }

                if (invalidId.value() > range.max()) {
                    break;
                }

                // System.out.println("\tsolution: " + invalidId.value());
                count += invalidId.value();
            }
        }
        System.out.println("End: Executing algorithm");
        System.out.println("Count: " + count);
    }

    public String name() {
        return "Day 2 Silver";
    }
}