package solutions;

import java.util.*;
import java.util.stream.Collectors;

public class BinaryTree {
    private int value;
    private BinaryTree left;
    private BinaryTree right;
    private class Pair{
        private int K;
        private int V;
        public Pair(int k, int v){
            this.K = k;
            this.V = V;
        }

        public int getK() {
            return K;
        }

        public int getV() {
            return V;
        }
    }

    public BinaryTree(int key, BinaryTree first, BinaryTree second) {
        this.value = key;
        this.left = first;
        this.right = second;
    }

    public Integer findLowestCommonAncestor(int first, int second) {
        List<Integer> firstPath = findPath(first);
        List<Integer> secondPath = findPath(second);
        int smallerSize = firstPath.size() < secondPath.size() ? firstPath.size() : secondPath.size();
        int i = 0;
        for (; i < smallerSize ; i++) {
            if(!(firstPath.get(i).equals(secondPath.get(i)))){
                break;
            }
        }
        return firstPath.get(i-1);
    }

    private List<Integer> findPath(int element) {
        List<Integer> result = new ArrayList<>();
        findNodePath(this,element,result);
        return result;
    }

    private boolean findNodePath(BinaryTree binaryTree, int element, List<Integer> result) {
        if(binaryTree == null){
            return false;
        }
        if (binaryTree.value == element){
            return true;
        }
        result.add(binaryTree.value);
        boolean resLeft = findNodePath(binaryTree.left,element,result);
        if (resLeft){
            return true;
        }
        boolean resRight = findNodePath(binaryTree.right,element,result);
        if (resRight){
            return true;
        }
        result.remove(Integer.valueOf(binaryTree.value));
        return false;
    }

    public List<Integer> topView() {
        Map<Integer,Pair> offsetToValueLevel = new TreeMap<>();
        buildMap(this,0,1,offsetToValueLevel);

        return offsetToValueLevel
                .values().stream()
                .map(e->e.K)
                .collect(Collectors.toList());
    }

    private void buildMap(BinaryTree binaryTree, int offset, int level, Map<Integer, Pair> offsetToValueLevel) {
        if(binaryTree == null){
            return;
        }
        Pair crrValueLevel = offsetToValueLevel.get(offset);
        if (crrValueLevel == null || level < crrValueLevel.V){
            offsetToValueLevel.put(offset,new Pair(binaryTree.value,level));
        }

        buildMap(binaryTree.left,offset-1,level+1,offsetToValueLevel);
        buildMap(binaryTree.right,offset+1,level+1,offsetToValueLevel);
    }
}
