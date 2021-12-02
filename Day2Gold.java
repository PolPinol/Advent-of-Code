import java.io.*;
import java.util.ArrayList;

public class Day2Gold {
    private ArrayList<String> input;
    private ArrayList<Integer> inputInteger;

    public Day2Gold() {
        this.input = new ArrayList<>();
        this.inputInteger = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day2Gold main = new Day2Gold();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        for (int i = 0; i < input.size(); i++) {
            String [] parts = splitString(input.get(i), ' ');

            if (parts[0].equals("forward")) {
                horizontal += atoi(parts[1]);
                depth += aim*atoi(parts[1]);
            } else if (parts[0].equals("down")) {
                aim += atoi(parts[1]);
            } else {
                aim -= atoi(parts[1]);
            }
        }

        System.out.println(horizontal*depth);
    }

    public String[] splitString(String buffer, char delimiter) {
        return buffer.split(String.valueOf(delimiter));
    }

    int atoi(String str) {
        try{
            return Integer.parseInt(str);
        } catch (NumberFormatException ex){
            return -1;
        }
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