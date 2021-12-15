import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Day15Silver {
    private ArrayList<String> input;
    private HashMap<String, Integer> dictionary;
    private int[][] adjList;
    private int v;
    private int rows;
    private int columns;

    public Day15Silver() {
        this.input = new ArrayList<>();
        this.dictionary = new HashMap<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day15Silver main = new Day15Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        Integer[][] map = new Integer[input.size()][input.get(0).length()];
        
        rows = input.size();
        columns = input.get(0).length();


        int ite = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = atoi(String.valueOf(input.get(i).charAt(j)));
                String key = i + "," + j;
                dictionary.put(key, ite);
                ite++;
            }
        }

        v = ite;
        initAdjList();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (i+1 < rows) {
                    addEdge(searchIndex(i, j), searchIndex(i+1, j), map[i+1][j]);
                }

                if (j+1 < columns) {
                    addEdge(searchIndex(i, j), searchIndex(i, j+1), map[i][j+1]);
                }

                if (0 <= i-1) {
                    addEdge(searchIndex(i, j), searchIndex(i-1, j), map[i-1][j]);
                }

                if (0 <= j-1) {
                    addEdge(searchIndex(i, j), searchIndex(i, j-1), map[i][j-1]);
                }
            }
        }

        dijkstra(0, searchIndex(rows-1, columns-1));
    }

    private void dijkstra(int start, int end) {
        int[] dist = new int[v];
        boolean[] isVisited = new boolean[v];

        for (int i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE;
            isVisited[i] = false;
        }

        dist[start] = 0;

        for (int i = 0; i < v-1; i++) {
            int u = minDistance(dist, isVisited);

            isVisited[u] = true;

            for (int j = 0; j < this.v; j++) {
                if (!isVisited[j] && adjList[u][j] > 0 && dist[u] != Integer.MAX_VALUE && dist[u] + adjList[u][j] < dist[j]) {
                    dist[j] = dist[u] + adjList[u][j];
                }
            }
        }

        System.out.println(dist[end]);
    }

    private int minDistance(int[] dist, boolean[] isVisited) {
        int min = Integer.MAX_VALUE;
        int min_index=0;

        for (int i = 0; i < this.v; i++) {
            if (!isVisited[i] && dist[i] <= min) {
                min = dist[i];
                min_index = i;
            }
        }

        return min_index;
    }

    int searchIndex(int x, int y) {
        String key = x + "," + y;
        return dictionary.get(key);
    }

    public void addEdge(int u, int v, int cost) {
        adjList[u][v] = cost;
    }

    private void initAdjList() {
        adjList = new int[v][v];

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                adjList[i][j] = 0;
            }
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
