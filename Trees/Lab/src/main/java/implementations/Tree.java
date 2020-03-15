package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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
        return null;
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
}



