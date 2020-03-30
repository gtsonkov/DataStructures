package implementations;

import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {
    private List<E> elements;

    public MaxHeap(){
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
        int parentIndex = getParentIndex(index);
        while (index > 0 && isLess(index,parentIndex)){
            Collections.swap(this.elements,index,parentIndex);
            index = parentIndex;
        }
    }

    private boolean isLess(int childIndex, int parentIndex) {

        return  getAt(childIndex).compareTo(getAt(parentIndex)) > 0;
    }

    private E getAt(int index){
        return this.elements.get(index);
    }

    private int getParentIndex(int index) {
        return ((index-1)/2);
    }

    @Override
    public E peek() {
        if (size() == 0){
            throw new IllegalStateException("No elements in Heep");
        }
        return this.elements.get(0);
    }
}
