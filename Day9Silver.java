import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day9Silver {
    private ArrayList<String> input;
    private Integer[][] map;

    public Day9Silver() {
        this.input = new ArrayList<>();

    }

    public static void main(String[] args) {
        // Initiate constructor
        Day9Silver main = new Day9Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        this.map = new Integer[input.size()][input.get(0).length()];

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                map[i][j] = atoi(String.valueOf(input.get(i).charAt(j)));
            }
        }

        int risk = 0;

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (i == 0 && j == 0) {
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1]) {
                        risk += 1 + map[i][j];
                    }
                } else if (i == 0 && j == input.get(i).length()-1) {
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j-1]) {
                        risk += 1 + map[i][j];
                    }
                } else if (i == input.size()-1 && j == 0) {
                    if (map[i][j] < map[i][j+1] && map[i][j] < map[i-1][j]) {
                        risk += 1 + map[i][j];
                    }
                } else if (i == input.size()-1 && j == input.get(i).length()-1) {
                    if (map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1]) {
                        risk += 1 + map[i][j];
                    }
                } else if (i == input.size()-1) {
                    // Last Row
                    if (map[i][j] < map[i][j+1] && map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1]) {
                        risk += 1 + map[i][j];
                    }
                } else if (i == 0) {
                    // First Row
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1] && map[i][j] < map[i][j-1]) {
                        risk += 1 + map[i][j];
                    }
                } else if (j == 0) {
                    // First Column
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1] && map[i][j] < map[i-1][j]) {
                        risk += 1 + map[i][j];
                    }
                } else if (j == input.get(i).length()-1) {
                    // Last Column
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1]) {
                        risk += 1 + map[i][j];
                    }
                } else {
                    if (map[i][j] < map[i+1][j] && map[i][j] < map[i][j+1] && map[i][j] < map[i-1][j] && map[i][j] < map[i][j-1]) {
                        risk += 1 + map[i][j];
                    }
                }
            }
        }
        System.out.println(risk);
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
