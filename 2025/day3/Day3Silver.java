package day3;

import day2.Id;
import day2.Range;

import java.util.ArrayList;
import java.util.Collections;


public class Day3Silver {
    private final ArrayList<String> linesFromFile;

    public Day3Silver(ArrayList<String> lines) {
        this.linesFromFile = lines;
    }

    public void execute() {
        long count = 0;

        // Prepare algorithm

        for (String line : linesFromFile) {
            int first = -1;
            int second = -1;

            for (int i = 0; i < line.length(); i++) {
                int val = Integer.parseInt(String.valueOf(line.charAt(i)));
                boolean isLastDigit = i == line.length() - 1;

                if (val > first && !isLastDigit) {
                    first = val;
                    second = -1;
                    continue;
                }

                if (val > second) {
                    second = val;
                }
            }

            int res = first * 10 + second;
            //System.out.println("res: " + res);
            count += res;
        }

        System.out.println("Count: " + count);
    }

    public String name() {
        return "Day 3 Silver";
    }
}