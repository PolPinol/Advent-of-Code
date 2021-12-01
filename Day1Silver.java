import java.io.*;
import java.util.ArrayList;

public class Day1Silver {
    private ArrayList<String> input;
    private ArrayList<Integer> inputInteger;

    public Day1Silver() {
        this.input = new ArrayList<>();
        this.inputInteger = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day1Silver main = new Day1Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        int count = 0;

        inputInteger = getIntegerArray(input);

        for (int i = 0; i < inputInteger.size()-1; i++) {
            if (inputInteger.get(i) < inputInteger.get(i+1)) {
                count++;
            }
        }

        System.out.println("Count: " + count);
    }

    private ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<>();
        for (String stringValue : stringArray) {
            try {
                result.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }
        }
        return result;
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