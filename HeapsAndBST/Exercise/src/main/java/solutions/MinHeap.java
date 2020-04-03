package solutions;

import interfaces.Decrease;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {
    private List<E> data ;

    public  MinHeap(){
        this.data = new ArrayList<>();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void add(E element) {
        this.data.add(element);
        heapifyUp(this.size()-1);
    }

    private boolean isLess(int indexFirst, int indexSecond) {
        return this.data.get(indexFirst).compareTo(this.data.get(indexSecond)) < 0;
    }

    private int getParentIndexFor(int index) {
        return (index-1) /2;
    }

    @Override
    public E peek() {
        enshureNonEmpty();
        return this.data.get(0);
    }

    private void enshureNonEmpty() {
        if (this.data.size() == 0){
            throw  new IllegalStateException("Empty Heap");
        }
    }

    @Override
    public E poll() {
        enshureNonEmpty();
        Collections.swap(this.data, 0, data.size()-1);
        E toRemove = this.data.remove(this.data.size()-1);
        heapyfyDown(0);
        return toRemove;
    }

    private void heapyfyDown(int index) {
        int leftChildIndex = getLeftChildIndex(index);
        while(leftChildIndex < this.data.size()){
            int swapIndex = leftChildIndex;
            int rightChildIndex = getRightChildIndex(index);
            if(rightChildIndex<this.size()){
                swapIndex = isLess(leftChildIndex,rightChildIndex) ? leftChildIndex : rightChildIndex;
            }

            if (isLess(index,swapIndex)){
                break;
            }

            Collections.swap(this.data,index, swapIndex);
            index = swapIndex;
            leftChildIndex = getLeftChildIndex(index);
        }
    }

    private void heapifyUp(int index) {
        int parentIndex = this.getParentIndexFor(index);

        while (index > 0 && isLess(index, parentIndex)){
            Collections.swap(this.data, index, parentIndex);
            index = parentIndex;
            parentIndex = this.getParentIndexFor(index);
        }
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    @Override
    public void decrease(E element) {
        int elementIndex = this.data.indexOf(element);
        E heapElement = this.data.get(elementIndex);
        heapElement.decrease();
        this.heapifyUp(elementIndex);
    }
}