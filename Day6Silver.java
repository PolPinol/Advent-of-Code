import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day6Silver {
    private ArrayList<String> input;
    private ArrayList<Integer> inputInteger;

    public Day6Silver() {
        this.input = new ArrayList<>();
        this.inputInteger = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day6Silver main = new Day6Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        String[] parts = splitString(input.get(0), ',');

        for (int i = 0; i < parts.length; i++) {
            inputInteger.add(atoi(parts[i]));
        }

        // Brute Force for Silver
        for (int i = 0; i < 80; i++) {
            int size = inputInteger.size();
            for (int j = 0; j < size; j++) {
                int fish = inputInteger.get(j);
                if (fish == 0) {
                    inputInteger.set(j, 6);
                    inputInteger.add(8);
                } else {
                    fish--;
                    inputInteger.set(j, fish);
                }
            }
        }
        System.out.println(inputInteger.size());
    }

    int atoi(String str) {
        try{
            return Integer.parseInt(str);
        } catch (NumberFormatException ex){
            return -1;
        }
    }

    public String[] splitString(String buffer, char delimiter) {
        return buffer.split(String.valueOf(delimiter));
    }

    public void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line = br.readLine();

            while (line != null) {
                input.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
