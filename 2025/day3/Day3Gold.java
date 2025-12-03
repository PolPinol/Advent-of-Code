package day3;

import java.util.ArrayList;


public class Day3Gold {
    private final ArrayList<String> linesFromFile;

    public Day3Gold(ArrayList<String> lines) {
        this.linesFromFile = lines;
    }

    public void execute() {
        long count = 0;

        // Prepare algorithm

        for (String line : linesFromFile) {
            // Prepare batteries
            int[] batteries = new int[12];
            for (int i = 0; i < 12; i++) {
                batteries[i] = -1;
            }

            outer:
            for (int i = 0; i < line.length(); i++) {
                int val = Integer.parseInt(String.valueOf(line.charAt(i)));
                //System.out.println("inspecting val: " + val);

                for (int j = 0; j < 12; j++) {
                    int remainingBatteriesToFill = 12 - j;
                    int remainingBatteriesInInput = line.length() - i;
                    //System.out.println("\tAnalyzing bat[j]-" + j + " current val: " +batteries[j] + " remainingBatteriesToFill: " + remainingBatteriesToFill + " remainingBatteriesInInput: " + remainingBatteriesInInput);

                    if (val > batteries[j] && remainingBatteriesToFill <= remainingBatteriesInInput) {
                        batteries[j] = val;
                        //System.out.println("assigning val: " + val + " to bat:" + j + "\n");
                        if (j != 11) {
                            for (int k = j + 1; k < 12; k++) {
                                batteries[k] = -1;
                            }
                        }
                        continue outer;
                    }
                }
            }

            long res = 0;
            for (int i = 0; i < 12; i++) {
                res += (long) (Math.pow(10, 11 - i) * batteries[i]);
            }
            //System.out.println("res: " + res);
            count += res;
        }

        System.out.println("Count: " + count);
    }

    public String name() {
        return "Day 3 Gold";
    }
}