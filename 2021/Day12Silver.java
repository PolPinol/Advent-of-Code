import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Day12Silver {
    private ArrayList<String> input;
    private ArrayList<String> vertices;
    private String[] dictionary;
    private int v;
    private Integer[][] adjList;
    private int solutions;

    public Day12Silver() {
        this.input = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.solutions = 0;
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day12Silver main = new Day12Silver();

        // Read input.txt file and fill input array string
        main.readFile();

        // Execute the algorithm
        main.mainProcess();
    }

    public void mainProcess() {
        // Split the input puzzle and get all vertices
        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).split("-");
            if (!vertices.contains(parts[0])) {
                vertices.add(parts[0]);
            }

            if (!vertices.contains(parts[1])) {
                vertices.add(parts[1]);
            }
        }

        // Size of vertices
        this.v = vertices.size();

        // Fill the dictionary between Integer and String
        fillDictionary();

        // Create a Graph
        initAdjList();

        // Add all edges to the graph
        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).split("-");
            addEdge(searchIndex(parts[0]), searchIndex(parts[1]));
        }

        // Look for index positions of start and end
        int start = searchIndex("start");
        int end = searchIndex("end");

        // Look for all paths from start to end
        printAllPaths(start, end);

        // Print number of solutions
        System.out.println(solutions);
    }

    // Utility method to initialise adjacency list
    private void initAdjList() {
        adjList = new Integer[v][v];

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                adjList[i][j] = 0;
            }
        }
    }

    public void addEdge(int u, int v) {
        adjList[u][v] = 1;
        adjList[v][u] = 1;
    }

    // Prints all paths from 'start' to 'end'
    public void printAllPaths(int s, int d) {
        boolean[] isVisited = new boolean[v];
        ArrayList<Integer> pathList = new ArrayList<>();

        // add source to path[]
        pathList.add(s);

        // Call recursive utility
        printAllPathsRecursive(s, d, isVisited, pathList);
    }

    private void printAllPathsRecursive(int u, int d, boolean[] isVisited, ArrayList<Integer> localPathList) {
        if (u == d) {
            solutions++;
            return;
        }

        // Mark the current node
        if (!isUpperCase(dictionary[u])) {
            isVisited[u] = true;
        }

        // Recur for all the vertices adjacent to current vertex
        for (int i = 0; i < v; i++) {
            if (adjList[u][i] == 1) {
                if (!isVisited[i]) {
                    localPathList.add(i);

                    printAllPathsRecursive(i, d, isVisited, localPathList);

                    localPathList.remove(localPathList.size()-1);
                }
            }
        }

        // Unmark the current node
        isVisited[u] = false;
    }

    private boolean isUpperCase(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                count++;
            }
        }
        return count == s.length();
    }

    private void fillDictionary() {
        this.dictionary = new String[vertices.size()];
        for (int i = 0; i < vertices.size(); i++) {
            dictionary[i] = vertices.get(i);
        }
    }

    private int searchIndex(String s) {
        for (int i = 0; i < dictionary.length; i++) {
            if (dictionary[i].equals(s)) {
                return i;
            }
        }
        System.out.println("ERROR");
        System.exit(-1);
        return -1;
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
