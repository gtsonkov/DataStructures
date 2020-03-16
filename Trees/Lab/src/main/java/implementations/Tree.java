package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {

    private E _value;
    private Tree<E> parent;
    private List<Tree<E>> childern;

    public Tree(E value){
        this._value = value;
        this.parent = null;
        this.childern = new ArrayList<>();
    }

    public Tree(E value, Tree<E>... subtrees){
        this._value = value;
        this.parent = null;
        this.childern = new ArrayList<>();
        for (Tree<E> subtree: subtrees) {
            this.childern.add(subtree);
        }
    }

    @Override
    public List<E> orderBfs() {
        ArrayList<E> result = new ArrayList<>();
        ArrayDeque<Tree<E>> childrenQueue = new ArrayDeque<>();

       childrenQueue.offer(this);

        while (!childrenQueue.isEmpty()){
            Tree<E> current = childrenQueue.poll();
            result.add(current._value);

            for (Tree<E> child : current.childern) {
                childrenQueue.offer(child);
            }
        }
        return result;
    }

    @Override
    public List<E> orderDfs() {
        ArrayList<E> result = new ArrayList<>();

         doDfs(this, result);
        return result;
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {

    }
	
	@Override
    public void removeNode(E nodeKey) {

    }

    @Override
    public void swap(E firstKey, E secondKey) {

    }

    private void doDfs(Tree<E> node, List<E> result){
        for (Tree<E> child : node.childern ){
            doDfs(child,result);
        }

        result.add(node._value);
    }
}