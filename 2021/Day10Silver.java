import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Day10Silver {
    private ArrayList<String> input;
    private ArrayList<Integer> inputInteger;
    private ArrayList<Integer> newInputInteger;

    public Day10Silver() {
        this.input = new ArrayList<>();
        this.inputInteger = new ArrayList<>();
        this.newInputInteger = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day10Silver main = new Day10Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        int solution = 0;
        for (int i = 0; i < input.size(); i++) {
            int points = 0;
            Stack<Character> stack = new Stack<>();
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
                            points = 3;
                            break;
                        } else if (c == ']') {
                            points = 57;
                            break;
                        } else if (c == '}') {
                            points = 1197;
                            break;
                        } else {
                            points = 25137;
                            break;
                        }
                    }
                }
            }
            solution += points;
        }
        System.out.println(solution);
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
