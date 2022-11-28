import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day15Gold {
    private ArrayList<String> input;
    private HashMap<String, Integer> dictionary;
    private HashMap<Integer, ArrayList<Integer[]>> adjMap;
    private int v;
    private int rows;
    private int columns;

    public Day15Gold() {
        this.input = new ArrayList<>();
        this.dictionary = new HashMap<>();
        this.adjMap = new HashMap<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day15Gold main = new Day15Gold();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        int oldRows = input.size();
        int oldColumns = input.get(0).length();
        rows = input.size()*5;
        columns = input.get(0).length()*5;

        Integer[][] map = new Integer[rows][columns];
        int ite = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int value;
                if (i < oldRows && j < oldColumns) {
                    value = atoi(String.valueOf(input.get(i).charAt(j)));
                } else {
                    if (0 <= i-oldRows) {
                        value = map[i-oldRows][j] + 1;
                    } else {
                        value = map[i][j-oldColumns] + 1;
                    }

                    if (value > 9) {
                        value = 1;
                    }
                }

                map[i][j] = value;

                String key = i + "," + j;
                dictionary.put(key, ite);
                ite++;
            }
        }

        v = ite;

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

            ArrayList<Integer[]> adjs = adjMap.get(u);

            for (int j = 0; j < v; j++) {
                boolean contains = false;
                int index = -1;
                for (int k = 0; k < adjs.size(); k++) {
                    if (adjs.get(k)[0] == j) {
                        contains = true;
                        index = k;
                        break;
                    }
                }
                if (!isVisited[j] && contains && dist[u] != Integer.MAX_VALUE && dist[u] + adjs.get(index)[1] < dist[j]) {
                    dist[j] = dist[u] + adjs.get(index)[1];
                }
            }
        }
        System.out.println(dist[end]);
    }

    private int minDistance(int[] dist, boolean[] isVisited) {
        int min = Integer.MAX_VALUE;
        int min_index = 0;

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
        if (adjMap.containsKey(u)) {
            ArrayList<Integer[]> adjList = adjMap.get(u);
            adjList.add(new Integer[]{v, cost});
            adjMap.put(u, adjList);
        } else {
            ArrayList<Integer[]> adjList = new ArrayList<>();
            adjList.add(new Integer[]{v, cost});
            adjMap.put(u, adjList);
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
