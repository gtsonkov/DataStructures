package implementations;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TreeFactory {
    private Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {
        for (String part:input) {
           int[]currKeys = Arrays.stream(part.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        return null;
    }

    private Tree<Integer> getRoot() {
        return null;
    }

    public Tree<Integer> createNodeByKey(int key) {
        return null;
    }

    public void addEdge(int parent, int child) {

    }
}



