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
        head = Def_Capacity /2;
        tail = head;
    }
    @Override
    public void add(E Element) {
        AddElementToTail(Element);
    }

    @Override
    public void offer(E element) {
        AddElementToTail(element);
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

    private void AddElementToTail(E Element){
        if (this.size == 0) {
            this.elements[tail] = Element;
        } else {
            CheckCapacity(tail);
            this.elements[++this.tail] = Element;
        }
        size++;
    }

    private void CheckCapacity(int position)
    {
        if (position == this.elements.length - 1 | position == 0){
            GrowUp();
        }
    }

    private void GrowUp(){
        Object[] temp = new Object[(this.elements.length*2)+1];

        int middle = (temp.length / 2);
        int begin = middle - (this.size / 2);
        int position = this.head;

        for (int i = begin; position <= this.tail; i++) {
            temp[i] = this.elements[position];
            position++;
        }

        this.tail = (this.head + this.size) - 1;
        this.head = begin;

        this.elements = temp;
    }
}