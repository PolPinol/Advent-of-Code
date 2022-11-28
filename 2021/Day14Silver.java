import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day14Silver {
    private ArrayList<String> input;
    private HashMap<String, String> dictionary;
    private HashMap<String, Integer> countLetters;
    private int days;

    public Day14Silver() {
        this.input = new ArrayList<>();
        this.dictionary = new HashMap<>();
        this.countLetters = new HashMap<>();
        this.days = 10;
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day14Silver main = new Day14Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        // Read input
        String template = input.get(0);

        for (int i = 2; i < input.size(); i++) {
            String[] parts = input.get(i).split(" -> ");
            dictionary.put(parts[0], parts[1]);
        }

        ArrayList<String> letters = new ArrayList<String>(dictionary.values());
        for (int i = 0; i < letters.size(); i++) {
            if (!countLetters.containsKey(letters.get(i))) {
                countLetters.put(letters.get(i), 0);
            }
        }

        for (int i = 0; i < days; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < template.length()-1; j++) {
                String pair = String.valueOf(template.charAt(j)) + String.valueOf(template.charAt(j+1));

                s.append(template.charAt(j));

                if (i == days-1) {
                    count(template.charAt(j));
                }

                if (dictionary.containsKey(pair)) {
                    s.append(dictionary.get(pair));
                    if (i == days-1) {
                        count(dictionary.get(pair));
                    }
                }
            }
            s.append(template.charAt(template.length()-1));
            if (i == days-1) {
                count(template.charAt(template.length()-1));
            }
            template = s.toString();
        }

        ArrayList<Integer> count = new ArrayList<>(countLetters.values());
        System.out.println(Collections.max(count) - Collections.min(count));
    }

    void count(Character c) {
        int count = countLetters.get(String.valueOf(c));
        countLetters.remove(String.valueOf(c));
        countLetters.put(String.valueOf(c), ++count);
    }

    void count(String s) {
        int count = countLetters.get(s);
        countLetters.remove(s);
        countLetters.put(s, ++count);
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
