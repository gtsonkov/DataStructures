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
        AddElementToHead(element);
    }

    @Override
    public void addLast(E element) {
        AddElementToTail(element);
}

    @Override
    public void push(E element) {
        AddElementToHead(element);
    }

    @Override
    public void insert(int index, E element) {
        int internIndex = this.head + index;
        ensureIsValid(internIndex);
        CheckCapacity(internIndex);
        for (int i = tail+1; i > internIndex ; i--) {

            this.elements[i] = this.elements[i-1];
        }
        this.elements[internIndex] = element;
        this.tail++;
        this.size++;
    }

    @Override
    public void set(int index, E element) {
        int internIndex = this.head + index;
        ensureIsValid(internIndex);
        this.elements[internIndex] = element;
    }

    @Override
    public E peek() {
        if(this.size != 0){
            return  GetAt(this.head);
        }
        return null;
    }

    @Override
    public E poll() {
        return removeFirst();
    }

    @Override
    public E pop() {
        return removeLast();
    }

    @Override
    public E get(int index) {
        int internIndex = this.head + index;
        ensureIsValid(internIndex);

        return GetAt(internIndex);
    }

    @Override
    public E get(Object object) {
        int indexOf = SearchElement(object);
        if (indexOf == -1){
            return  null;
        }
        return GetAt(indexOf);
    }

    @Override
    public E remove(int index) {
        int internIndex = this.head + index;
        ensureIsValid(internIndex);

        E element = GetAt(internIndex);
        RemoveElement(internIndex);

        return element;
    }

    @Override
    public E remove(Object object) {
        int indexOf = SearchElement(object);
        if (indexOf == -1){
            return  null;
        }
        E element = GetAt(indexOf);
        RemoveElement(indexOf);
        return element;
    }

    @Override
    public E removeFirst() {
        if (!isEmpty()){
            E element = GetAt(this.head);

            this.elements[this.head] = null;
            this.head++;
            this.size--;

            return element;
        }
        return null;
    }

    @Override
    public E removeLast() {
        if (!isEmpty()){
            E element = GetAt(this.tail);

            this.elements[this.tail] = null;
            this.tail--;
            this.size--;

            return element;
        }
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
        return new Iterator<E>() {
            private int index = head;
            @Override
            public boolean hasNext() {
                return index < tail ;
            }

            @Override
            public E next() {
                return GetAt(index++);
            }
        };
    }

    private void AddElementToHead(E Element){
        if (this.size == 0) {
            this.elements[head] = Element;
        } else {
            CheckCapacity(head);
            this.elements[--this.head] = Element;
        }
        size++;
    }

    private void AddElementToTail(E Element){
        if (this.size == 0) {
            this.elements[tail] = Element;
        } else {
            CheckCapacity(tail);
            this.elements[++this.tail] = Element;
        }
        this.size++;
    }

    private void CheckCapacity(int position)
    {
        if (position == this.elements.length - 1 || position == 0){
            GrowUp();
        }
    }

    private void ensureIsValid(int index){

        if (index < this.head || index > this.tail){
            throw new IndexOutOfBoundsException("Index out of bound for index: " + (index - this.head));
        }
    }

    private E GetAt(int index)
    {
        return  (E)this.elements[index];
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

        this.head = begin;
        this.tail = (this.head + this.size) - 1;

        this.elements = temp;
    }

    private int SearchElement(Object object){
        for (int i = this.head; i <= this.tail ; i++) {
            if (this.elements[i].equals(object)){
                return i;
            }
        }
        return  -1;
    }

    private void RemoveElement(int index){
        for (int i = index; i < this.tail ; i++) {
            this.elements[i] = this.elements[i+1];
        }
        elements[this.tail] = null;
        this.tail--;
        this.size--;
    }
}