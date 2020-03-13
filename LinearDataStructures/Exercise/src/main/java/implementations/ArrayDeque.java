package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {
    private  final int Def_Capacity = 7;
    private int head;
    private int tail;
    private int size;

    private Object[] elements;

    public ArrayDeque(){
        this.elements = new Object[Def_Capacity];
    }
    @Override
    public void add(E Element) {
        if (size == 0) {
            elements[0] = Element;
        } else {
            CheckSize();

        }
    }

    @Override
    public void offer(E element) {

    }

    @Override
    public void addFirst(E element) {

    }

    @Override
    public void addLast(E element) {

    }

    @Override
    public void push(E element) {

    }

    @Override
    public void insert(int index, E element) {

    }

    @Override
    public void set(int index, E element) {

    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E get(Object object) {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E remove(Object object) {
        return null;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public void trimToSize() {

    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    private void CheckSize()
    {
        if (this.size == this.elements.length - 1){
            GrowUp();
        }
    }

    private void GrowUp(){
        Object[] temp = new Object[(this.elements.length*2)];
        for (int i = 0; i < this.elements.length ; i++) {
            temp[i] = this.elements[i];
        }
        this.elements = temp;
    }
}