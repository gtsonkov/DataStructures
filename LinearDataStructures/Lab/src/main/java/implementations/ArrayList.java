package implementations;

import interfaces.List;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    public  static final  int InitialSize = 4;

    private Object[] elements;

    private int size;

    public  ArrayList(){
        this.elements = new Object[InitialSize];
        this.size = 0;
    }

    @Override
    public boolean add(E element) {

        if (this.size == elements.length){
            growSize();
        }

        this.elements[this.size] = element;
        this.size++;

        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (!indexInRange(index)) {
            return false;
        }
        if(elements.length == size) {
            growSize();
        }


        shiftRight(index);
        this.elements[index] = element;
        this.size++;
        return true;
    }

    @Override
    public E get(int index) {
        ensureIndex(index);

        return (E)this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        ensureIndex(index);

        E oldElement = (E)this.elements[index];

        this.elements[index] = element;
        return oldElement;
    }

    @Override
    public E remove(int index) {
        ensureIndex(index);

        E oldElement = (E)this.elements[index];

        shiftLeft(index);
        size--;

        ensureCapacity();

        return oldElement;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size ; i++) {
            if (this.elements[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E element) {
        return this.indexOf(element) != -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                    return index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private void ensureIndex(int index) {
        if (!indexInRange(index)) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }
    }

    private  void ensureCapacity() {
        if (this.size < (this.elements.length /3 )){
            shrink();
        }
    }

    private boolean indexInRange(int index){

        return (index >= 0 && index < size);
    }

    private void growSize(){
        Object[] tmp = new Object[this.elements.length*2];
        for (int i = 0; i < this.elements.length ; i++) {
            tmp[i] = elements[i];
        }

        this.elements = tmp;
    }

    private void shiftLeft(int index){
        for (int i = index; i < this.size -1 ; i++) {
            this.elements[i] = this.elements[i+1];
        }
    }

    private void shiftRight(int index) {
        for (int i = size; i > index ; i--) {
            this.elements[i] = this.elements[i-1];
        }
    }

    private  void shrink() {
        Object[] tmp = new Object[(this.elements.length/3)+1];
        for (int i = 0; i < size ; i++) {
            tmp[i] = this.elements[i];
        }

        this.elements = tmp;
    }
}