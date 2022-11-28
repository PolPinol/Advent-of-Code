import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Day3Silver {
    private ArrayList<String> input;
    private ArrayList<Integer> inputInteger;
    private ArrayList[] newInputInteger;
    int lengthWord;
    int gamma;
    int epsilon;
    double gammaRate;
    double epsilonRate;

    public Day3Silver() {
        this.input = new ArrayList<>();
        this.inputInteger = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day3Silver main = new Day3Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        lengthWord = input.get(0).length();

        this.newInputInteger = new ArrayList[lengthWord];

        for (int i = 0; i < lengthWord; i++) {
            this.newInputInteger[i] = new ArrayList<>();
        }

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < lengthWord; j++) {
                newInputInteger[j].add(atoi(String.valueOf(input.get(i).charAt(j))));
            }
        }

        gammaRate = 0;
        epsilonRate = 0;
        for (int j = 0; j < lengthWord; j++) {
            findEpsilonGamma(newInputInteger[j]);
            epsilonRate += Math.pow(10, lengthWord-j-1)*epsilon;
            gammaRate += Math.pow(10, lengthWord-j-1)*gamma;
        }

        // Format the double to express all numbers in correct format
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);

        // Print the result
        System.out.println(Integer.parseInt(String.valueOf(df.format(epsilonRate)),2) * Integer.parseInt(String.valueOf(df.format(gammaRate)),2));
    }

    void findEpsilonGamma(ArrayList<Integer> arrayList) {
        int count1 = 0;
        int count0 = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) == 1) {
                count1++;
            } else {
                count0++;
            }
        }

        if (count1 > count0) {
            gamma = 1;
            epsilon = 0;
        } else {
            gamma = 0;
            epsilon = 1;
        }
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
