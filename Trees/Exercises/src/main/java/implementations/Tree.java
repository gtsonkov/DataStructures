package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
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
               .map(Tree::getKey)
               .collect((Collectors.toList()));
    }

    private List<Tree<E>> getLeafNodesBFS(){
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        List<Tree<E>> allNodes = new ArrayList<>();

        while (!queue.isEmpty()){
            Tree<E> tree = queue.poll();
            if (tree._children.isEmpty()){
                allNodes.add(tree);
            }

            for (Tree<E> node: tree._children) {
                queue.offer(node);
            }
        }
        return  allNodes;
    }

    @Override
    public List<E> getMiddleKeys() {
        return null;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        return null;
    }

    @Override
    public List<E> getLongestPath() {
        return null;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        return null;
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        return null;
    }
}



