package solutions;
import java.util.*;

public class CookiesProblem {
    public Integer solve(int requiredSweetness, int[] cookiesSweetness) {
        //Queue<Integer> cookies = new PriorityQueue<>();
        MinHeap_intern<Integer> cookies = new MinHeap_intern<>();
        for (int cookie: cookiesSweetness ) {
            cookies.add(cookie);
        }
        int currMin = cookies.peek();
        int steps = 0;
        while (currMin<requiredSweetness && cookies.size()>1){
            int leastSweetCookie = cookies.poll();
            int secondSweetCookie = cookies.poll();

            int combinedSweet = leastSweetCookie + (2* secondSweetCookie);
            cookies.add(combinedSweet);
            currMin = cookies.peek();
            steps++;
        }
        return currMin > requiredSweetness ? steps : -1;
    }
}

class MinHeap_intern<E extends Comparable<E>>{
    private List<E> data ;

    public MinHeap_intern(){
        this.data = new ArrayList<>();
    }

    public int size() {
        return data.size();
    }

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

    public E peek() {
        enshureNonEmpty();
        return this.data.get(0);
    }

    private void enshureNonEmpty() {
        if (this.data.size() == 0){
            throw  new IllegalStateException("Empty Heap");
        }
    }

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
}