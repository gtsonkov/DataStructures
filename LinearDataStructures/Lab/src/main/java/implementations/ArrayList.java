package implementations;

import interfaces.List;

import java.util.Arrays;
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
            resize();
        }

        this.elements[this.size] = element;
        this.size++;

        return true;
    }

    @Override
    public boolean add(int index, E element) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private void resize(){
        Object[] tmp = new Object[this.elements.length*2];
        for (int i = 0; i < elements.length ; i++) {
           tmp[i] = elements[i];
        }

        this.elements = tmp;
    }
}