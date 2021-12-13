import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day13Silver {
    private ArrayList<String> input;
    private ArrayList<Integer> num_x;
    private ArrayList<Integer> num_y;
    private ArrayList<Integer[]> fold;
    private Integer[][] map;
    private int max_x;
    private int max_y;

    public Day13Silver() {
        this.input = new ArrayList<>();
        this.num_x = new ArrayList<>();
        this.num_y = new ArrayList<>();
        this.fold = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day13Silver main = new Day13Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).isEmpty()) {
                for (int j = i+1; j < input.size(); j++) {
                    String[] parts = splitString(input.get(j), '=');
                    int y;
                    if (parts[0].charAt(parts[0].length()-1) == 'x') {
                        y = 0;
                    } else {
                        y = 1;
                    }
                    fold.add(new Integer[]{y, atoi(parts[1])});
                }
                break;
            } else {
                String[] parts = splitString(input.get(i), ',');
                num_x.add(atoi(parts[0]));
                num_y.add(atoi(parts[1]));
            }
        }

        this.max_x = Collections.max(num_x)+1;
        this.max_y = Collections.max(num_y)+1;
        this.map = new Integer[max_x][max_y];

        for (int i = 0; i < max_x; i++) {
            for (int j = 0; j < max_y; j++) {
                map[i][j] = 0;
            }
        }

        for (int i = 0; i < num_x.size(); i++) {
            map[num_x.get(i)][num_y.get(i)] = 1;
        }

        // Fold
        for (int k = 0; k < 1; k++) {
            int y = fold.get(k)[0];
            int fold_value = fold.get(k)[1];

            if (y == 0) {
                // Fer fold  x = .
                for (int i = 0; i < max_y; i++) {
                    for (int j = fold_value; j < max_x; j++) {
                        map[max_x - j - 1][i] += map[j][i];
                    }
                }
                max_x = fold_value;
            } else {
                // Fer fold  y = .
                for (int i = fold_value; i < max_y; i++) {
                    for (int j = 0; j < max_x; j++) {
                        map[j][max_y - i - 1] += map[j][i];
                    }
                }
                max_y = fold_value;
            }

            // Solution (Print map)
            int solution = 0;
            for (int i = 0; i < max_y; i++) {
                for (int j = 0; j < max_x; j++) {
                    if (map[j][i] > 0) {
                        solution++;
                    }
                }
            }
            System.out.println(solution);
        }

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
