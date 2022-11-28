import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Stack;

public class Day10Gold {
    private ArrayList<String> input;

    public Day10Gold() {
        this.input = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day10Gold main = new Day10Gold();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        ArrayList<Double> solutions = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            Stack<Character> stack = new Stack<>();
            boolean incomplete = true;
            for (int j = 0; j < input.get(i).length(); j++) {
                char c = input.get(i).charAt(j);
                if (c == '{' || c == '[' || c == '(' || c == '<') {
                    stack.push(c);
                } else {
                    char top = stack.peek();
                    if (c == '}' && top == '{') {
                        stack.pop();
                    } else if (c == ']' && top == '[') {
                        stack.pop();
                    } else if (c == ')' && top == '(') {
                        stack.pop();
                    } else if (c == '>' && top == '<') {
                        stack.pop();
                    } else {
                        if (c == ')') {
                            incomplete = false;
                            break;
                        } else if (c == ']') {
                            incomplete = false;
                            break;
                        } else if (c == '}') {
                            incomplete = false;
                            break;
                        } else if (c == '>') {
                            incomplete = false;
                            break;
                        }
                    }
                }
            }
            if (incomplete) {
                solutions.add(calculatePoints(stack));
            }
        }

        Collections.sort(solutions);
        double index = (double) solutions.size() / (double) 2;
        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340);
        System.out.println(df.format(solutions.get((int) index)));
    }

    private double calculatePoints(Stack<Character> stack) {
        double score = 0;
        ArrayList<Character> chars = new ArrayList<>();

        while (!stack.isEmpty()) {
            char top = stack.pop();
            chars.add(top);
        }

        for (int i = 0; i < chars.size(); i++) {
            score = score*5 + points(chars.get(i));
        }

        return score;
    }

    private int points(char c) {
        if (c == '(') {
            return 1;
        } else if (c == '[') {
            return 2;
        } else if (c == '{') {
            return 3;
        } else {
            return 4;
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
