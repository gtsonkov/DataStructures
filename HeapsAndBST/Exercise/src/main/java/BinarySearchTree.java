import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.function.Consumer;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
    public BinarySearchTree(){

    }

    public BinarySearchTree(E element){
        this.root = new Node<>(element);
    }

    public BinarySearchTree(Node <E> node){
        this.root = new Node<>(node);
    }

    private Node<E> root;

    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;
        public int count;

		public Node(E value) {
            this.value = value;
             count = 1;
        }

        public Node(Node<E> node){
		    this.value = node.getValue();
            this.count = node.count;
            if (node.getLeft() != null){
		        this.leftChild = node.leftChild;
            }
            if (node.getLeft() != null){
                this.rightChild = node.rightChild;
            }
        }

        public Node<E> getLeft() {
            return this.leftChild;
        }

        public Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }
    }
	
	public void eachInOrder(Consumer<E> consumer) {
            nodeInOrder(this.root, consumer);
    }

    private void nodeInOrder(Node<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }
        nodeInOrder(node.getLeft(),consumer);
        consumer.accept(node.value);
        nodeInOrder(node.getRight(),consumer);
    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void insert(E element) {
        if(this.root == null){
            this.root = new Node<>(element);
        } else {
            insertInTo(this.root, element);
        }
    }

    private void insertInTo(Node<E> node, E element) {
        if(isGreater(element,node.getValue())){
            if (node.getRight() == null){
                node.rightChild = new Node<>(element);
            } else {
                insertInTo(node.getRight(), element);
            }
        } else if(isSmaller(element,node.value) ){
            if (node.getLeft() == null){
                node.leftChild = new Node<>(element);
            } else {
                insertInTo(node.getLeft(),element);
            }
         } else {
            //Do Nothing
        }
        node.count++;
    }

    public boolean contains(E element) {
        Node<E> current = this.root;

        while(current != null){
            if (isEqual(element,current.getValue())){
                return true;
            }else if(isSmaller(element,current.getValue())){
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return false;
    }
    public BinarySearchTree<E> search(E element) {
        Node<E> found = new Node<>(containsNode(this.root,element));
        return null;
    }

    private Node<E> containsNode(Node<E> root, E element) {
        Node<E> current = this.root;

        while(current != null){
            if (isEqual(element,current.getValue())){
                return current;
            }else if(isSmaller(element,current.getValue())){
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return null;
    }

    public List<E> range(E first, E second) {
        List<E> result = new ArrayList<>();
        if (this.root == null){
            return  result;
        }
        // nodePreOrderRange(this.root,result,first,second);
        Deque<Node<E>> queue = new ArrayDeque<>();
        queue.offer(this.root);

        while (!queue.isEmpty()){
            Node<E> curr = queue.poll();

            if(curr.getLeft() != null){
                queue.offer(curr.getLeft());
            }
            if(curr.getRight() != null){
                queue.offer(curr.getRight());
            }

            if(isSmaller(first, curr.getValue()) && isGreater(second,curr.getValue())){
                result.add(curr.getValue());
            } else if(isEqual(first,curr.getValue()) || isEqual(second,curr.getValue())){
                result.add(curr.getValue());
            }
        }

       return result;
    }

    private void nodePreOrderRange(Node<E> node,List result,E min, E max ) {
        if (node == null) {
            return;
        }
        if (isEqual(node.getValue(),min) || isEqual(node.getValue(),max)){
            result.add(node.getValue());
        }
        if (isGreater(node.getValue(),min) && (isSmaller(node.getValue(),max))){
            result.add(node.getValue());
        }
        nodePreOrderRange(node.getLeft(),result,min,max);
        nodePreOrderRange(node.getRight(),result,min,max);
    }
    public void deleteMin() {
        if(this.root == null){
            throw  new IllegalStateException("Can not delete from empty tree");
        }
        Node<E> current = this.root;
        if(current.getLeft() == null){
            this.root = this.root.getRight();
            return;
        }
        while (current.getLeft().getLeft() != null){
            current.count--;
            current= current.getLeft();
        }
        current.count--;
        current.rightChild = current.leftChild.rightChild;
    }

    public void deleteMax() {
        if(this.root == null){
            throw  new IllegalStateException("Can not delete from empty tree");
        }
        Node<E> current = this.root;
        if(current.getRight() == null){
            this.root = this.root.getLeft();
            return;
        }
        while (current.getRight().getRight() != null){
            current.count--;
            current= current.getRight();
        }
        current.count--;
        current.leftChild = current.rightChild.leftChild;
    }

    public int count() {
        if(this.root == null){
            return 0;
        }
        return this.root.count;
    }

    public int rank(E element) {
        return nodeRank(this.root,element);
    }

    private int nodeRank(Node<E> node, E element) {
        if(this.root == null){
            return 0;
        }
        if(isSmaller(element,node.getValue())){
            return nodeRank(node.getLeft(), element);
        }else if(isEqual(element,node.getValue())){
            return node.getLeft() == null ? 0 : node.getLeft().count;
        }
        return getNodeLeftCount(node.getLeft()) +1 + nodeRank(node.getRight(),element);
    }

    private int getNodeLeftCount(Node<E> node){
        return node==null ? 0 : node.count;
    }

    public E ceil(E element) {
        if (this.root == null){
            return null;
        }
        Node<E> current = this.root;
        Node<E> nearestBigger = null;

        while (current != null){
            if (isSmaller(element, current.getValue())){
                nearestBigger = current;
                current = current.getLeft();
            }else if(isGreater(element,current.getValue())){
                current = current.getRight();
            } else {
                Node<E> right = current.getRight();
                if (right != null && nearestBigger != null){
                    nearestBigger = isSmaller(right.getValue(),nearestBigger.getValue()) ? right : nearestBigger;
                } else if (nearestBigger == null){
                    nearestBigger = right;
                }
                break;
            }
        }
        return nearestBigger == null ? null : nearestBigger.getValue();
    }

    public E floor(E element) {
        if (this.root == null){
            return null;
        }
        Node<E> current = this.root;
        Node<E> nearestSmaller = null;
        while(current != null) {
            if (isGreater(element, current.getValue())) {
                nearestSmaller = current;
                current = current.getRight();
            } else if (isSmaller(element, current.getValue())) {
                current = current.getLeft();
            } else {
                Node<E> left = current.getLeft();
                 if(nearestSmaller == null){
                    nearestSmaller = left;
                }else if(left!=null && nearestSmaller != null){
                    nearestSmaller = isGreater(left.getValue(),nearestSmaller.getValue()) ? left: nearestSmaller;
                }
                 break;
            }
        }
       return nearestSmaller == null ? null : nearestSmaller.getValue();
    }

    private boolean isGreater(E element, E value) {
        return element.compareTo(value) > 0;
    }
    private boolean isSmaller(E element, E value) {
        return element.compareTo(value) < 0;
    }
    private boolean isEqual(E element, E value) {
        return element.compareTo(value)==0;
    }
}