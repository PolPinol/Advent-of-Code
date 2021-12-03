import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Day3Gold {
    private ArrayList<String> input;
    private ArrayList<Integer> inputInteger;
    private ArrayList<Integer>[] newInputInteger;
    int lengthWord;
    String oxygenRate;
    String co2Rate;

    public Day3Gold() {
        this.input = new ArrayList<>();
        this.inputInteger = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day3Gold main = new Day3Gold();

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

        for (int k = 0; k < lengthWord; k++) {
            findEpsilonGamma(newInputInteger[k], k, 1);

            if (input.size() == 1) {
                break;
            }

            // Back to fins the array in columns
            for (int i = 0; i < lengthWord; i++) {
                this.newInputInteger[i] = new ArrayList<>();
            }

            for (int i = 0; i < input.size(); i++) {
                for (int j = 0; j < lengthWord; j++) {
                    newInputInteger[j].add(atoi(String.valueOf(input.get(i).charAt(j))));
                }
            }
        }
        oxygenRate = input.get(0);

        // Get again the input
        readFile();

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

        for (int k = 0; k < lengthWord; k++) {
            findEpsilonGamma(newInputInteger[k], k, 0);

            if (input.size() == 1) {
                break;
            }

            // Back to fins the array in columns
            for (int i = 0; i < lengthWord; i++) {
                this.newInputInteger[i] = new ArrayList<>();
            }

            for (int i = 0; i < input.size(); i++) {
                for (int j = 0; j < lengthWord; j++) {
                    newInputInteger[j].add(atoi(String.valueOf(input.get(i).charAt(j))));
                }
            }
        }
        co2Rate = input.get(0);

        System.out.println(Integer.parseInt(oxygenRate,2) * Integer.parseInt(co2Rate,2));
    }

    void findEpsilonGamma(ArrayList<Integer> arrayList, int position, int oxigenOrCo2) {
        int count1 = 0;
        int count0 = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) == 1) {
                count1++;
            } else {
                count0++;
            }
        }

        if (oxigenOrCo2 == 1) {
            if (count1 >= count0) {
                // More 1s than 0s
                for (int i = 0; i < input.size(); i++) {
                    if (input.get(i).charAt(position) == '0') {
                        input.remove(i);
                        i--;
                    }
                }
            } else {
                // More 0s than 1s
                for (int i = 0; i < input.size(); i++) {
                    if (input.get(i).charAt(position) == '1') {
                        input.remove(i);
                        i--;
                    }
                }
            }
        } else {
            if (count0 > count1) {
                // More 1s than 0s
                for (int i = 0; i < input.size(); i++) {
                    if (input.get(i).charAt(position) == '0') {
                        input.remove(i);
                        i--;
                    }
                }
            } else {
                // More 0s than 1s
                for (int i = 0; i < input.size(); i++) {
                    if (input.get(i).charAt(position) == '1') {
                        input.remove(i);
                        i--;
                    }
                }
            }
        }
    }

    int atoi(String str) {
        try{
            return Integer.parseInt(str);
        } catch (NumberFormatException ex){
            return -1;
        }
    }

    public void sortArrayList(ArrayList<Integer> arrayList) {
        Collections.sort(arrayList);
    }

    public String[] splitString(String buffer, char delimiter) {
        return buffer.split(String.valueOf(delimiter));
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
