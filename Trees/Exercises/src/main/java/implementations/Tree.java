package implementations;

import interfaces.AbstractTree;

import java.util.*;
import java.util.stream.Collectors;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> _children;

    public Tree(E key){
        this.key = key;
        this._children = new ArrayList<>();
       //for (int i = 0; i < children.length ; i++) {
       //    children[i].setParent(this);
       //    this._children.add(children[i]);
       //}
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this._children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder sb = new StringBuilder();
        traverseTreeWithRecurtion(sb,0,this);
        return sb.toString().trim();
    }

    private void traverseTreeWithRecurtion(StringBuilder sb, int ident, Tree<E> eTree) {

        sb
                .append(this.getPadding(ident))
                .append(eTree.getKey())
                .append(System.lineSeparator());

        for (Tree<E> child : eTree._children ) {
            traverseTreeWithRecurtion(sb,(ident+2),child);
        }
    }

    private String getPadding(int size){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <size ; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public List<E> getLeafKeys() {
       return getLeafNodesBFS()
               .stream()
               .filter(tree -> tree._children.isEmpty())
               .map(Tree::getKey)
               .collect((Collectors.toList()));
    }

    private List<Tree<E>> getLeafNodesBFS(){
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        List<Tree<E>> allNodes = new ArrayList<>();

        while (!queue.isEmpty()){
            Tree<E> tree = queue.poll();

                allNodes.add(tree);

            for (Tree<E> node: tree._children) {
                queue.offer(node);
            }
        }
        return  allNodes;
    }

    @Override
    public List<E> getMiddleKeys() {
        return getLeafNodesBFS()
                .stream()
                .filter(tree -> !(tree._children.isEmpty()) && (tree.parent != null))
                .map(Tree::getKey)
                .collect((Collectors.toList()));
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        int[] maxPhat = new int[1];
        int currPhat = 0;
        List<Tree<E>> mostDeepLeft = new ArrayList<>();
        getDeepestLeftmostNodeDFS(mostDeepLeft,maxPhat,currPhat,this);
        return mostDeepLeft.get(0);
    }

    private Tree<E> getDeepestLeftmostNodeBFS() {
        List<Tree<E>> trees = this.getLeafNodesBFS();
        int stepToTheTop = 0;
        Tree<E> bottomLeaf = null;
        for (Tree<E> tree: trees) {
            if (isLeaf(tree)) {
                Tree<E> parent = tree.getParent();
                int currSteps = 0;
                while (parent != null) {
                    currSteps++;
                    parent = parent.getParent();
                }
                if (currSteps > stepToTheTop) {
                    stepToTheTop = currSteps;
                    bottomLeaf = tree;
                }
            }
        }
        return bottomLeaf;
    }
    private void getDeepestLeftmostNodeDFS(List<Tree<E>> mostDeepLeft, int[] maxPhat, int currPhat, Tree<E> tree) {

        if (currPhat > maxPhat[0]){
            maxPhat[0] = currPhat;
            mostDeepLeft.add(0,tree);
        }
        for (Tree<E> node: tree._children) {
            getDeepestLeftmostNodeDFS(mostDeepLeft, maxPhat,currPhat+1,node);
        }
    }

    private boolean isLeaf(Tree<E> tree) {
        return tree._children.isEmpty();
    }

    @Override
    public List<E> getLongestPath() {
        Tree<E> lastLeaf = getDeepestLeftmostNode();
        Deque<Tree<E>> tree = new ArrayDeque<>();
        tree.push(lastLeaf);
        List<E> result = new ArrayList<>();
        while (tree.getFirst().getParent() != null){
            Tree<E> parent = tree.getFirst().getParent();
            if (parent != null) {
                tree.push(parent);
            }
        }
        while (!tree.isEmpty()){
            result.add(tree.pop().getKey());
        }
        return result;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<List<Tree<E>>> allPhat = new ArrayList<>();
        Tree<E> currTree = this;
        List<List<E>> result = new ArrayList<>();
        List<Tree<E>> currList = new ArrayList<>();
        getPathWithSumDFS(allPhat,sum,0,currTree,currList);
        for (List<Tree<E>> list : allPhat) {
            List<E> currKeys = new ArrayList<>();
            Collections.reverse(list);
            for (Tree<E> node:list) {
                currKeys.add(node.getKey());
            }
            result.add(currKeys);
        }

        return result;
    }

    private void getPathWithSumDFS(List<List<Tree<E>>> mostDeepLeft, int sum, int currPhat, Tree<E> tree, List<Tree<E>> currList) {

        if (currPhat == sum){
            mostDeepLeft.add(currList);
        }

        for (Tree<E> node: tree._children) {
            currList.add(tree);
            getPathWithSumDFS(mostDeepLeft, sum, currPhat += (int)tree.key, node,currList);
            if (currPhat != sum){
                currList.remove(tree);
            }
        }
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        return null;
    }
}