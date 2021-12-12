import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day12Gold {
    private ArrayList<String> input;
    private ArrayList<String> vertices;
    private String[] dictionary;
    private int v;
    private Integer[][] adjList;
    private int n_solutions;
    private ArrayList<String> solutions;

    public Day12Gold() {
        this.input = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.solutions = new ArrayList<>();
    }

    public static void main(String[] args) {
        // Initiate constructor
        Day12Gold main = new Day12Gold();

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

        // Look for not upperCase cases that can repeat twice
        ArrayList<Integer> twice = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            if (!isUpperCase(vertices.get(i)) && !(vertices.get(i).equals("start")) && !(vertices.get(i).equals("end"))) {
                twice.add(searchIndex(vertices.get(i)));
            }
        }

        // Look for all paths from start to end
        printAllPaths(start, end, twice);

        // Print number of solutions
        System.out.println(n_solutions);
    }

    // Prints all paths from 'start' to 'end'
    public void printAllPaths(int s, int d, ArrayList<Integer> twice) {
        ArrayList<Integer> pathList = new ArrayList<>();

        // Add source to path[]
        pathList.add(s);

        // Call Recursive for all cases for not upper case strings that cap repeat twice
        int[] isVisited;
        for (int i = 0; i < twice.size(); i++) {
            isVisited = new int[v];
            for (int j = 0; j < v; j++) {
                if (j == twice.get(i)) {
                    isVisited[j] = 0;
                } else {
                    isVisited[j] = 1;
                }
            }
            isVisited[s] = 1;
            isVisited[d] = 1;
            printAllPathsUtilGold(s, d, isVisited, pathList);
        }

        Set<String> set = new HashSet<>(solutions);
        n_solutions = set.size();
    }

    private void printAllPathsUtilGold(int u, int d, int[] isVisited, ArrayList<Integer> localPathList) {
        if (u == d) {
            savePath(localPathList);
            return;
        }

        // Mark the current node
        if (!isUpperCase(dictionary[u])) {
            isVisited[u]++;
        }

        // Recur for all the vertices adjacent to current vertex
        for (int i = 0; i < v; i++) {
            if (adjList[u][i] == 1) {
                if (isVisited[i] < 2) {
                    // store current node
                    // in path[]
                    localPathList.add(i);
                    printAllPathsUtilGold(i, d, isVisited, localPathList);

                    // remove current node
                    // in path[]
                    localPathList.remove(localPathList.size()-1);
                }
            }
        }

        // Unmark the current node
        if (!isUpperCase(dictionary[u])) {
            isVisited[u]--;
        }
    }

    private void savePath(List<Integer> localPathList) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < localPathList.size(); i++) {
            if (i != localPathList.size()-1) {
                s.append(dictionary[localPathList.get(i)]).append(",");
            } else {
                s.append(dictionary[localPathList.get(i)]);
            }
        }
        solutions.add(s.toString());
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

    int atoi(String str) {
        try{
            return Integer.parseInt(str);
        } catch (NumberFormatException ex){
            return -1;
        }
    }

    public void sortArrayList(ArrayList<Integer> arrayList) {
        Collections.sort(arrayList);
    }

    public String[] splitString(String buffer, char delimiter) {
        return buffer.split(String.valueOf(delimiter));
    }

    private ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<>();
        for (String stringValue : stringArray) {
            try {
                result.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }
        }
        return result;
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
