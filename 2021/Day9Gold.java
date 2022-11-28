import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day9Gold {
    private ArrayList<String> input;
    private Integer[][] map;
    private int rows;
    private int columns;
    private ArrayList<Integer> basins;

    public Day9Gold() {
        this.input = new ArrayList<>();
        this.basins = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day9Gold main = new Day9Gold();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        rows = input.size();
        columns = input.get(0).length();
        this.map = new Integer[rows][columns];

        resetMap();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                resetMap();
                if (i == 0 && j == 0) {
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1]) {
                        basin(i, j);
                    }
                } else if (i == 0 && j == input.get(i).length()-1) {
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1]) {
                        basin(i, j);
                    }
                } else if (i == input.size()-1 && j == 0) {
                    if (map[i][j] < map[i][j+1] && map[i][j] < map[i-1][j]) {
                        basin(i, j);
                    }
                } else if (i == input.size()-1 && j == input.get(i).length()-1) {
                    if (map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1]) {
                        basin(i, j);
                    }
                } else if (i == input.size()-1) {
                    // Last Row
                    if (map[i][j] < map[i][j+1] && map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1]) {
                        basin(i, j);
                    }
                } else if (i == 0) {
                    // First Row
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1] && map[i][j] < map[i][j-1]) {
                        basin(i, j);
                    }
                } else if (j == 0) {
                    // First Column
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1] && map[i][j] < map[i-1][j]) {
                        basin(i, j);
                    }
                } else if (j == input.get(i).length()-1) {
                    // Last Column
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1]) {
                        basin(i, j);
                    }
                } else {
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1] && map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1]) {
                        basin(i, j);
                    }
                }
            }
        }

        Collections.sort(basins);
        System.out.println(basins.get(basins.size()-1)*basins.get(basins.size()-2)*basins.get(basins.size()-3));
    }

    private void resetMap() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = atoi(String.valueOf(input.get(i).charAt(j)));
            }
        }
    }

    private void basin(int row, int column) {
        ArrayList<Integer[]> queue = new ArrayList<>();

        int size = 1;
        map[row][column] = 9;

        queue.add(new Integer[]{row + 1, column});
        queue.add(new Integer[]{row, column + 1});
        queue.add(new Integer[]{row - 1, column});
        queue.add(new Integer[]{row, column - 1});

        while (queue.size() != 0) {
            int rowQ = queue.get(0)[0];
            int columnQ = queue.get(0)[1];
            if (rowQ < rows && columnQ < columns && rowQ >= 0 && columnQ >= 0) {
                if (isFlowingPoint(rowQ, columnQ)) {
                    queue.add(new Integer[]{rowQ + 1, columnQ});
                    queue.add(new Integer[]{rowQ, columnQ + 1});
                    queue.add(new Integer[]{rowQ - 1, columnQ});
                    queue.add(new Integer[]{rowQ, columnQ - 1});
                    size++;
                    map[rowQ][columnQ] = 9;
                }
            }
            queue.remove(0);
        }

        basins.add(size);
    }

    private boolean isFlowingPoint(int row, int column) {
        return map[row][column] != 9;
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
