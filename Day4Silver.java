import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day4Silver {
    public static final int DROP = -1;

    private ArrayList<String> input;
    private ArrayList<Integer> numbers;
    private ArrayList<Integer[][]> bingos;

    public Day4Silver() {
        this.input = new ArrayList<>();
        this.numbers = new ArrayList<>();
        this.bingos = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day4Silver main = new Day4Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        readBingo();

        for (int i = 0; i < numbers.size(); i++) {
            // Drop number and check for each bingo if its won
            for (int j = 0; j < bingos.size(); j++) {
                dropNumber(numbers.get(i), bingos.get(j));
                checkBingo(numbers.get(i), bingos.get(j));
            }
        }
    }

    private void checkBingo(int numberWon, Integer[][] bingo) {
        // Check for rows
        for (int i = 0; i < 5; i++) {
            if (bingo[i][0] == DROP && bingo[i][1] == DROP && bingo[i][2] == DROP && bingo[i][3] == DROP && bingo[i][4] == DROP) {
                int sum = sumUnreadNumbers(bingo);
                System.out.println(sum*numberWon);
                System.exit(0);
            }
        }

        // Check for columns
        for (int i = 0; i < 5; i++) {
            if (bingo[0][i] == DROP && bingo[1][i] == DROP && bingo[2][i] == DROP && bingo[3][i] == DROP && bingo[4][i] == DROP) {
                int sum = sumUnreadNumbers(bingo);
                System.out.println(sum*numberWon);
                System.exit(0);
            }
        }
    }

    private int sumUnreadNumbers(Integer[][] bingo) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (bingo[i][j] != DROP) {
                    sum += bingo[i][j];
                }
            }
        }

        return sum;
    }

    private void dropNumber(int num, Integer[][] bingo) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (bingo[i][j] == num) {
                    bingo[i][j] = DROP;
                    return;
                }
            }
        }
    }

    void readBingo() {
        // Read all numbers
        String [] parts = splitString(input.get(0), ',');
        for (int i = 0; i < parts.length; i++) {
            numbers.add(Integer.parseInt(parts[i]));
        }

        // Read the bingos that play the game
        for (int i = 2; i < input.size(); i = i + 6) {
            // Save each Bingo 5x5
            Integer[][] bingoAux = new Integer[5][5];
            for (int j = 0; j < 5; j++) {
                // Split the string with spaces
                String [] parts2 = input.get(i+j).split("\\s+");

                // We need to read different if String starts with space
                if (parts2[0].equals("")) {
                    for (int k = 1; k < 6; k++) {
                        bingoAux[j][k-1] = Integer.valueOf(parts2[k]);
                    }
                } else {
                    for (int k = 0; k < 5; k++) {
                        bingoAux[j][k] = Integer.valueOf(parts2[k]);
                    }
                }
            }
            bingos.add(bingoAux);
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
