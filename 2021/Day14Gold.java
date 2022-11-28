import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Day14Gold {
    private ArrayList<String> input;
    private HashMap<String, String> dictionary;
    private HashMap<String, Double> steps;
    private ArrayList<String> letters;
    private ArrayList<Double> countLetters;
    private int days;

    public Day14Gold() {
        this.input = new ArrayList<>();
        this.dictionary = new HashMap<>();
        this.steps = new HashMap<>();
        this.days = 40;
        this.letters = new ArrayList<>();
        this.countLetters = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day14Gold main = new Day14Gold();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        // Read input
        String template = input.get(0);
        String init = String.valueOf(template.charAt(0));
        String end = String.valueOf(template.charAt(template.length()-1));

        for (int i = 2; i < input.size(); i++) {
            String[] parts = input.get(i).split(" -> ");
            dictionary.put(parts[0], parts[1]);
            steps.put(parts[0], (double) 0);

            if (!letters.contains(parts[1])) {
                letters.add(parts[1]);
            }
        }

        for (int i = 0; i < template.length()-1; i++) {
            put(String.valueOf(template.charAt(i)) + template.charAt(i + 1), steps, 1);
        }

        for (int i = 0; i < days; i++) {
            HashMap<String, Double> day = new HashMap<>();
            ArrayList<String> keys = new ArrayList<>(steps.keySet());
            for (int j = 0; j < keys.size(); j++) {
                double count = steps.get(keys.get(j));
                if (count != 0) {
                    String mid = dictionary.get(keys.get(j));
                    String left = keys.get(j).charAt(0) + mid;
                    String right = mid + keys.get(j).charAt(1);

                    put(left, day, count);
                    put(right, day, count);
                }
            }

            fillHashMapDay(day);
        }

        ArrayList<String> keys = new ArrayList<>(steps.keySet());
        for (int i = 0; i < letters.size(); i++) {
            double count = 0;
            for (int j = 0; j < keys.size(); j++) {
                String pair = letters.get(i) + letters.get(i);
                if (keys.get(j).equals(pair)) {
                    count += steps.get(keys.get(j))*2;
                } else if (keys.get(j).contains(letters.get(i))) {
                    count += steps.get(keys.get(j));
                }
            }
            countLetters.add(count);
        }

        for (int i = 0; i < letters.size(); i++) {
            double count = countLetters.get(i);
            if (letters.get(i).equals(init) || letters.get(i).equals(end)) {
                countLetters.set(i, (count+1)/2);
            } else {
                countLetters.set(i, (count)/2);
            }
        }

        format(Collections.max(countLetters) - Collections.min(countLetters));
    }

    void format(double solution) {
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);
        System.out.println(df.format(solution));
    }

    private void fillHashMapDay(HashMap<String, Double> day) {
        // Put all count at 0
        ArrayList<String> keys = new ArrayList<>(steps.keySet());

        for (int i = 0; i < keys.size(); i++) {
            steps.put(keys.get(i), day.getOrDefault(keys.get(i), (double) 0));
        }
    }

    void put(String s, HashMap<String, Double> map, double plus) {
        if (map.containsKey(s)) {
            double count = map.get(s) + plus;
            map.remove(s);
            map.put(s, count);
        } else {
            map.put(s, plus);
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
