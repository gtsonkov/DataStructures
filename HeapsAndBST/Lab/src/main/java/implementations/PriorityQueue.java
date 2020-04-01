package implementations;

import interfaces.AbstractQueue;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {
    private List<E> elements;

    public PriorityQueue(){
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        heaapifyUp(this.size() -1);
    }

    private void heaapifyUp(int index) {
        while (index > 0 && isLess(getParentIndex(index),index)){
            Collections.swap(this.elements,index,getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    private int getParentIndex(int index) {
        return ((index-1)/2);
    }

    private boolean isLess(int firstIndex, int secondIndex) {

        return  getAt(firstIndex).compareTo(getAt(secondIndex)) < 0;
    }

    private void heapifyDown(int i) {
        while(getLeftChildIndex(i) < this.size() && isLess(i,getLeftChildIndex(i))){
            int rightChildIndex = getRightChildIndex(i);
            int child = getLeftChildIndex(i);
            if(rightChildIndex < this.size() && isLess(child,rightChildIndex)){
                child = rightChildIndex;
            }
            Collections.swap(this.elements,child ,i);
            i = child;
        }
    }

    private int getLeftChildIndex(int index){
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index){
        return 2 * index + 2;
    }

    private void ensureNonEmpty(){
        if (size() == 0){
            throw new IllegalStateException("No elements in Heep");
        }
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return this.elements.get(0);
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E valueToReturn = getAt(0);
        Collections.swap(this.elements,0,this.size()-1);
        this.elements.remove(this.size()-1);
        heapifyDown(0);
        return valueToReturn;
    }

    private E getAt(int index) {
        return this.elements.get(index);
    }
}