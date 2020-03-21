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
           int parentKey = currKeys[0];
           int childKey = currKeys[1];
           this.addEdge(parentKey,childKey);
        }
        return getRoot();
    }

    private Tree<Integer> getRoot() {
        for (Tree<Integer>node: nodesByKeys.values()) {
            if (node.getParent() == null){
                return node;
            }
        }
        return  null;
    }

    public Tree<Integer> createNodeByKey(int key) {
        this.nodesByKeys.putIfAbsent(key, new Tree<>(key));
        return this.nodesByKeys.get(key);
    }

    public void addEdge(int parent, int child) {
        Tree<Integer> parentByKey = this.createNodeByKey(parent);
        Tree<Integer> childByKey = this.createNodeByKey(child);
        childByKey.setParent(parentByKey);
        parentByKey.addChild(childByKey);
    }
}



