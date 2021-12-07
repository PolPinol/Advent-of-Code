import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day7Silver {
    private ArrayList<String> input;
    private ArrayList<Integer> inputInteger;

    public Day7Silver() {
        this.input = new ArrayList<>();
        this.inputInteger = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day7Silver main = new Day7Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        String [] parts = splitString(input.get(0), ',');
        for (int i = 0; i < parts.length; i++) {
            inputInteger.add(atoi(parts[i]));
        }


        int maxNum = Collections.max(inputInteger);
        int countMin = Integer.MAX_VALUE;
        for (int i = maxNum; i >= 0; i--) {
            int count = 0;
            for (int j = 0; j < inputInteger.size(); j++) {
                count += Math.abs(inputInteger.get(j) - i);
            }
            if (count < countMin) {
                countMin = count;
            }
            System.out.println(count);
        }

        System.out.println(countMin);
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
