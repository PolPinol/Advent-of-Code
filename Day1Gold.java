import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1Gold {
    private ArrayList<String> input;
    private ArrayList<Integer> inputInteger;
    private ArrayList<Integer> newInputInteger;

    public Day1Gold() {
        this.input = new ArrayList<>();
        this.inputInteger = new ArrayList<>();
        this.newInputInteger = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day1Gold main = new Day1Gold();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        int count = 0;

        inputInteger = getIntegerArray(input);

        // Transform the input as the requirements
        for (int i = 0; i < inputInteger.size()-2; i++) {
            int newLetter = inputInteger.get(i) + inputInteger.get(i+1) + inputInteger.get(i+2);
            newInputInteger.add(newLetter);
        }


        // Day1Silver algorithm for new input with integers
        for (int i = 0; i < newInputInteger.size()-1; i++) {
            if (newInputInteger.get(i) < newInputInteger.get(i+1)) {
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
