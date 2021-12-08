import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day8Gold {
    private ArrayList<String> input;
    private String[] dictionary;

    public Day8Gold() {
        this.input = new ArrayList<>();
        this.dictionary = new String[10];
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day8Gold main = new Day8Gold();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        int count = 0;
        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).split(" \\| ");

            // to decoder the numbers
            String[] parts0 = splitString(parts[0], ' ');

            // find the output of the problem
            String[] parts1 = splitString(parts[1], ' ');

            for (int j = 0; j < parts0.length; j++) {
                if (parts0[j].length() == 2) {
                    dictionary[1] = parts0[j];
                } else if (parts0[j].length() == 4)  {
                    dictionary[4] = parts0[j];
                } else if (parts0[j].length() == 3)  {
                    dictionary[7] = parts0[j];
                } else if (parts0[j].length() == 7)  {
                    dictionary[8] = parts0[j];
                }
            }

            for (int j = 0; j < parts0.length; j++) {
                String aux = parts0[j] + dictionary[7];
                if (equalsStrings(withoutDuplicated(aux), dictionary[8]) && parts0[j].length() == 6) {
                    dictionary[6] = parts0[j];
                    break;
                }
            }

            for (int j = 0; j < parts0.length; j++) {
                String aux = parts0[j] + dictionary[4];
                if (equalsStrings(withoutDuplicated(aux), dictionary[8]) && parts0[j].length() == 6 && !equalsStrings(parts0[j], dictionary[6])) {
                    dictionary[0] = parts0[j];
                    break;
                }
            }

            for (int j = 0; j < parts0.length; j++) {
                if (parts0[j].length() == 6 && !equalsStrings(parts0[j], dictionary[6]) && !equalsStrings(parts0[j], dictionary[0])) {
                    dictionary[9] = parts0[j];
                    break;
                }
            }

            for (int j = 0; j < parts0.length; j++) {
                String aux = parts0[j] + dictionary[9];
                if (equalsStrings(withoutDuplicated(aux), dictionary[8]) && parts0[j].length() == 5) {
                    dictionary[2] = parts0[j];
                    break;
                }
            }

            for (int j = 0; j < parts0.length; j++) {
                String aux = parts0[j] + dictionary[6];
                if (equalsStrings(withoutDuplicated(aux), dictionary[8]) && parts0[j].length() == 5 && !equalsStrings(parts0[j], dictionary[2]))  {
                    dictionary[3] = parts0[j];
                    break;
                }
            }

            for (int j = 0; j < parts0.length; j++) {
                if (parts0[j].length() == 5 && !equalsStrings(parts0[j], dictionary[2]) && !equalsStrings(parts0[j], dictionary[3])) {
                    dictionary[5] = parts0[j];
                    break;
                }
            }

            int number = 0;
            for (int j = 0; j < parts1.length; j++) {
                number += Math.pow(10, parts1.length - j - 1)*decoder(parts1[j]);
            }
            count += number;
        }
        System.out.println(count);
    }

    private boolean equalsStrings(String firstStr, String secondStr) {
        char[] first = firstStr.toCharArray();
        char[] second = secondStr.toCharArray();
        Arrays.sort(first);
        Arrays.sort(second);
        return Arrays.equals(first, second);
    }

    private String withoutDuplicated(String string) {
        char[] chars = string.toCharArray();
        Set<Character> charSet = new LinkedHashSet<Character>();
        for (char c : chars) {
            charSet.add(c);
        }

        StringBuilder sb = new StringBuilder();
        for (Character character : charSet) {
            sb.append(character);
        }
        return sb.toString();
    }

    private int decoder(String s) {
        for (int i = 0; i < dictionary.length; i++) {
            if (equalsStrings(s, dictionary[i])) {
                return i;
            }
        }
        return -1;
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
