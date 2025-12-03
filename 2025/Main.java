import day2.Day2Silver;
import day3.Day3Gold;
import day3.Day3Silver;

import java.io.*;
import java.util.ArrayList;

public class Main {
    static ArrayList<String> linesFromFile = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("[Info] Reading file ... ");
        readFile();
        System.out.println("[Info] File read.");

        Day3Gold algorithm = new Day3Gold(linesFromFile);
        System.out.println("[Info] Executing algorithm: " + algorithm.name());
        algorithm.execute();
        System.out.println("[Info] Finished.");
    }

    public static void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line = br.readLine();

            while (line != null) {
                linesFromFile.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}