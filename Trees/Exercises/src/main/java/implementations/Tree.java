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

    private List<Tree<E>> getAllLeafs(Tree<E> root) {

       return  getLeafNodesBFS(root)
                .stream()
                .filter(tree -> tree._children.isEmpty())
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

    private List<Tree<E>> getLeafNodesBFS(Tree<E> root){
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(root);
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
        int[] currSum = new int [1];
        List<E> tmpList = new ArrayList<>();
        List<List<E>> myList = new ArrayList<>();
        getPathWithSum(this,sum,myList);
        return myList;
    }

    private void getPathWithSum(Tree<E> tree, int sum, List<List<E>> myList) {
        List<Tree<E>> allLeafs = getAllLeafs(tree);
        for (Tree<E> node: allLeafs) {
            int currSum = (int) node.getKey();
            List<E> current = new ArrayList<>();
            Tree<E> parent = node.parent;
            current.add(node.getKey());
            while (parent != null){
                E currNodeKey = parent.getKey();
                currSum += (int) currNodeKey;
                current.add(currNodeKey);
                Tree<E> tmp = parent.parent;
                parent = tmp;

                if (currSum == sum){
                    if (tmp != null){
                        if ((int) tmp.getKey() == 0){
                            current.add(tmp.getKey());
                            Collections.reverse(current);
                            myList.add(current);
                            return;
                        }
                    }
                    Collections.reverse(current);
                    myList.add(current);
                }
            }
        }
    }


    @Override
    //TODO return parts from the tree, witch match the sense.
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        List<Tree<E>> result = new ArrayList<>();
        int[] total = new int[1];
        total[0] = 0;
        //subTreeSumBFS(this,sum,result);
        Deque<Tree<E>> deque = new ArrayDeque<>();
        deque.offer(this);
        Tree<E> tempNode = null;
        while (!deque.isEmpty()){
            Tree<E>currentNode = deque.poll();

            for (Tree<E> node:currentNode._children) {
                deque.offer(node);
                subTreeSumDFS(node,sum,result,total,tempNode);
            }
        }

        return result;
    }
    
    private void subTreeSumBFS(Tree<E> tree, int sum, List<Tree<E>> result){
        Deque<Tree<E>> trees = new ArrayDeque<>();

        trees.offer(tree);
        Tree<E> currParent = tree;

        int currSum = (int) tree.getKey();

        while (!trees.isEmpty()){
            Tree<E> current = trees.poll();
            List<Tree<E>> newChildren = new ArrayList<>();
            for (Tree<E> child : current._children) {
                newChildren.add(child);
                trees.offer(child);
                currSum += (int) child.getKey();
                if (currSum == sum){
                    Tree<E> newTree = tree;
                    current._children = newChildren;
                    result.add(newTree);
                    sum = 0;
                }
            }
        }
        result.size();
    }
    
    private void subTreeSumDFS(Tree<E> tree, int sum, List<Tree<E>> result, int[] total, Tree<E>tempNode){
        int currentSum = (int) tree.getKey();

        for (Tree<E> child: tree._children) {
            currentSum += (int) child.getKey();
            subTreeSumDFS(child,sum,result,total,tempNode);
        }

        total[0] += (int) tree.getKey();

        if (currentSum == sum || total[0] == sum){
            result.add(tree.parent);
            total[0] = 0;
        }
    }
}