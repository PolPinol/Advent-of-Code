import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Day5Silver {
    private ArrayList<String> input;
    private HashMap<String, Integer> map;

    public Day5Silver() {
        this.input = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day5Silver main = new Day5Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        for (int i = 0; i < input.size(); i++) {
            //Split init and end
            String[] parts = splitString(input.get(i), ' ');

            //x1,y1
            String[] x1y1 = splitString(parts[0], ',');
            int x1 = atoi(x1y1[0]);
            int y1 = atoi(x1y1[1]);

            //x2,y2
            String[] x2y2 = splitString(parts[2], ',');
            int x2 = atoi(x2y2[0]);
            int y2 = atoi(x2y2[1]);

            if (x1 == x2) {
                // Rotate y1 <= y2
                if (y1 <= y2) {
                    for (int j = y1; j <= y2; j++) {
                        String key = x1 + "," + j;
                        if (map.containsKey(key)) {
                            int overlap = map.get(key);
                            overlap++;
                            map.put(key, overlap);
                        } else {
                            map.put(key, 1);
                        }
                    }
                } else {
                    for (int j = y2; j <= y1; j++) {
                        String key = x1 + "," + j;
                        if (map.containsKey(key)) {
                            int overlap = map.get(key);
                            overlap++;
                            map.put(key, overlap);
                        } else {
                            map.put(key, 1);
                        }
                    }
                }
            } else if (y1 == y2) {
                // Rotate x1 <= x2
                if (x1 <= x2) {
                    for (int j = x1; j <= x2; j++) {
                        String key = j + "," + y1;
                        if (map.containsKey(key)) {
                            int overlap = map.get(key);
                            overlap++;
                            map.put(key, overlap);
                        } else {
                            map.put(key, 1);
                        }
                    }
                } else {
                    for (int j = x2; j <= x1; j++) {
                        String key = j + "," + y1;
                        if (map.containsKey(key)) {
                            int overlap = map.get(key);
                            overlap++;
                            map.put(key, overlap);
                        } else {
                            map.put(key, 1);
                        }
                    }
                }
            }
        }

        System.out.println(map.values().stream().filter(integer -> integer >= 2).mapToInt(integer -> integer).count());
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
