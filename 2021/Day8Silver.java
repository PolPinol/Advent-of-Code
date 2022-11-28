import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day8Silver {
    private ArrayList<String> input;

    public Day8Silver() {
        this.input = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day8Silver main = new Day8Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        int count = 0;
        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).split("\\| ");
            String[] parts2 = splitString(parts[1], ' ');

            for (int j = 0; j < parts2.length; j++) {
                if (parts2[j].length() == 2 || parts2[j].length() == 4 || parts2[j].length() == 3 || parts2[j].length() == 7) {
                    count++;
                }
            }
        }
        System.out.println(count);
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
