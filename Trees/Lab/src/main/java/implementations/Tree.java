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
            subtree.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        ArrayList<E> result = new ArrayList<>();
        if(this._value == null){
            return result;
        }

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
        Tree<E> result = search(this, parentKey);
        if (result == null){
            throw  new IllegalArgumentException();
        }
        result.childern.add(child);
        child.parent = result;
    }
	
	@Override
    public void removeNode(E nodeKey) {
        Tree<E> result = search(this, nodeKey);
        if (result == null){
            throw  new IllegalArgumentException();
        }

        for (Tree<E> child:result.childern){
            child.parent = null;
        }
        result.childern.clear();
        if (result.parent != null){
            Tree<E> parent = result.parent;
            parent.childern.remove(result);
        }else {
            result._value = null;
        }
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E>firstNode = search(this, firstKey);
        Tree<E>secondNode = search(this, secondKey);
        if (firstKey == null || secondKey == null){
            throw new IllegalArgumentException();
        }

        Tree<E> firstParent = firstNode.parent;
        Tree<E> secondParent = secondNode.parent;

        if (firstNode.parent == null){
                swapRoot(secondNode);
                return;
        }

        if (secondNode.parent == null){
                swapRoot(firstNode);
                return;
        }

        int firstIndex = firstParent.childern.indexOf(firstNode);
        int secondIndex = secondParent.childern.indexOf(secondNode);

        firstParent.childern.set(firstIndex,secondNode);
        secondParent.childern.set(secondIndex,firstNode);
    }

    private void doDfs(Tree<E> node, List<E> result){
        for (Tree<E> child : node.childern ){
            doDfs(child,result);
        }

        result.add(node._value);
    }

    private Tree<E> search (Tree<E> tree ,E parenKey){
        ArrayDeque<Tree<E>> childrenQueue = new ArrayDeque<>();

        childrenQueue.offer(tree);

        while (!childrenQueue.isEmpty()){
            Tree<E> current = childrenQueue.poll();
            if (current._value.equals(parenKey)){
                return current;
            }

            for (Tree<E> child : current.childern) {
                childrenQueue.offer(child);
            }
        }
        return null;
    }

    private void swapRoot(Tree<E> node){
        this._value = node._value;
        this.parent = null;
        this.childern = node.childern;
        node.parent = null;
    }
}