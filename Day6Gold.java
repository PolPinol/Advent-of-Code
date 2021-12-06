import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Day6Gold {
    private ArrayList<String> input;
    private ArrayList<Integer> inputInteger;

    public Day6Gold() {
        this.input = new ArrayList<>();
        this.inputInteger = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day6Gold main = new Day6Gold();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        String[] parts = splitString(input.get(0), ',');
        double[] fishes = new double[9];

        for (int i = 0; i < parts.length; i++) {
            fishes[atoi(parts[i])]++;
        }

        for (int i = 0; i < 256; i++) {
            double copy_fishes = fishes[8];
            double copy_fishes2;
            for (int j = 7; j >= 0; j--) {
                copy_fishes2 = fishes[j];
                fishes[j] = copy_fishes;
                copy_fishes = copy_fishes2;
            }
            fishes[6] += copy_fishes;
            fishes[8] = copy_fishes;
        }

        double count = 0;
        for (int i = 0; i < 9; i++) {
            count += fishes[i];
        }

        // Format the double to express all numbers in correct format
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);
        System.out.println(df.format(count));
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
