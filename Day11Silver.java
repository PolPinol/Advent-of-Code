import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day11Silver {
    private ArrayList<String> input;
    private int rows;
    private int columns;
    private int days;
    private Integer[][] map;

    public Day11Silver() {
        this.input = new ArrayList<>();
        this.rows = 10;
        this.columns = 10;
        this.days = 100;
        map = new Integer[rows][columns];
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day11Silver main = new Day11Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        int flashed = 0;

        // Read the string and parse to the Integer[][]
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = atoi(String.valueOf(input.get(i).charAt(j)));
            }
        }

        for (int i = 0; i < days; i++) {
            // First sum +1 to all
            for (int k = 0; k < rows; k++) {
                for (int j = 0; j < columns; j++) {
                    map[k][j]++;
                }
            }

            ArrayList<Integer[]> queue = new ArrayList<>();
            for (int k = 0; k < rows; k++) {
                for (int j = 0; j < columns; j++) {
                    if (map[k][j] > 9) {
                        queue.add(new Integer[]{k, j});
                    }
                }
            }

            while (!queue.isEmpty()) {
                int k = queue.get(0)[0];
                int j = queue.get(0)[1];

                if (map[k][j] > 9) {
                    // Do flash
                    map[k][j] = 0;
                    flashed++;

                    // Sum +1 to all sides and diagonals and add to queue to check new flash
                    // Sides
                    if (k+1 < rows) {
                        if (map[k+1][j] != 0) {
                            map[k+1][j]++;
                            queue.add(new Integer[]{k+1, j});
                        }
                    }

                    if (j+1 < columns) {
                        if (map[k][j+1] != 0) {
                            map[k][j+1]++;
                            queue.add(new Integer[]{k, j+1});
                        }
                    }

                    if (0 <= k-1) {
                        if (map[k-1][j] != 0) {
                            map[k-1][j]++;
                            queue.add(new Integer[]{k-1, j});
                        }
                    }

                    if (0 <= j-1) {
                        if (map[k][j-1] != 0) {
                            map[k][j-1]++;
                            queue.add(new Integer[]{k, j-1});
                        }
                    }

                    // Diagonals
                    if (k+1 < rows && j+1 < columns) {
                        if (map[k+1][j+1] != 0) {
                            map[k+1][j+1]++;
                            queue.add(new Integer[]{k+1, j+1});
                        }
                    }

                    if (0 <= k-1 && 0 <= j-1) {
                        if (map[k-1][j-1] != 0) {
                            map[k-1][j-1]++;
                            queue.add(new Integer[]{k-1, j-1});
                        }
                    }

                    if (0 <= k-1 && j+1 < columns) {
                        if (map[k-1][j+1] != 0) {
                            map[k-1][j+1]++;
                            queue.add(new Integer[]{k-1, j+1});
                        }
                    }

                    if (k+1 < rows && 0 <= j-1) {
                        if (map[k+1][j-1] != 0) {
                            map[k+1][j-1]++;
                            queue.add(new Integer[]{k+1, j-1});
                        }
                    }
                }

                queue.remove(0);
            }
        }
        System.out.println(flashed);
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
