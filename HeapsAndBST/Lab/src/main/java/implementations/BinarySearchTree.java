package implementations;

import interfaces.AbstractBinarySearchTree;

import javax.swing.plaf.BorderUIResource;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {
    private Node<E> root;
    private Node<E> left;
    private Node<E> right;

    public BinarySearchTree(){

    }

    public BinarySearchTree(Node<E> tree){
            this.copy(tree);
    }

    private void copy(Node<E> other) {
        if(other != null){
            this.insert(other.value);
            this.copy(other.leftChild);
            this.copy(other.rightChild);
        }
    }

    @Override
    public void insert(E element) {
        Node<E> newNode = new Node<>(element);
        if(this.getRoot() == null){
            this.root = newNode;
        }else{
            Node<E> currRoot = this.root;
            Node<E> prevNode = currRoot;
            while(currRoot != null){
                prevNode = currRoot;

                if(isLess(element,currRoot.value)){
                    currRoot = currRoot.leftChild;
                } else if (isGreater(element,currRoot.value)){
                    currRoot = currRoot.rightChild;
                } else {
                  return;
                }
            }

            if(isLess(element,prevNode.value)){
                prevNode.leftChild = newNode;
            } else if (isGreater(element,prevNode.value)){
                prevNode.rightChild = newNode;
            }
        }
    }

    private boolean isLess(E first, E second) {
        return first.compareTo(second) < 0;
    }

    private boolean isGreater(E first, E second) {
        return first.compareTo(second) > 0;
    }

    @Override
    public boolean contains(E element) {
        Node<E> currNode = this.root;
        while(currNode != null){
            if (isLess(element,currNode.value)){
                currNode = currNode.leftChild;
            } else if(isGreater(element,currNode.value)){
                currNode = currNode.rightChild;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        if (this.root == null){
            return null;
        }
        Node<E> currNode = this.root;
        while(currNode != null){
            if (isLess(element,currNode.value)){
                currNode = currNode.leftChild;
            } else if(isGreater(element,currNode.value)){
                currNode = currNode.rightChild;
            } else {
                return new BinarySearchTree<>(currNode);
            }
        }
        return null;
    }

    @Override
    public Node<E> getRoot() {
        return this.root;
    }

    @Override
    public Node<E> getLeft() {
        return this.left;
    }

    @Override
    public Node<E> getRight() {
        return this.right;
    }

    @Override
    public E getValue() {
        return this.root.value;
    }
}