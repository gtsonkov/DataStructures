package implementations;

import interfaces.LinkedList;

import javax.swing.*;
import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {

    private Node<E> top;
    private int size;

    private static class Node<E>{

        public E value;
        private Node<E> nexElement;
       public Node(E element){
            this.value = element;
        }
    }
    public SinglyLinkedList()
    {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> tmp = new Node<>(element);

        tmp.nexElement = this.top;
        this.top = tmp;
        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newElement = new Node<>(element);
        if (this.isEmpty()) {
            this.top = newElement;
        } else {

            Node<E> current = this.top;

            while (current.nexElement != null) {
                current = current.nexElement;
            }

            current.nexElement = newElement;
        }
        this.size++;
    }

    @Override
    public E removeFirst() {
        ensureNonEmpty();
        E element = this.top.value;

        this.top = this.top.nexElement;

        this.size--;
        return element;
    }

    @Override
    public E removeLast() {
        ensureNonEmpty();
        E value = null;
        if (this.size == 1){
            value = this.top.value;
            this.top = null;
        } else {
            Node<E> PreLast = this.top;
            Node<E> last = this.top.nexElement;

            while (last.nexElement != null){
                PreLast = last;
                last= PreLast.nexElement;
            }
            value = last.value;
            PreLast.nexElement = null;
        }

        this.size--;
        return value;
    }

    @Override
    public E getFirst() {
        ensureNonEmpty();
        return this.top.value;
    }

    @Override
    public E getLast() {
        ensureNonEmpty();
        Node<E> current = this.top;

        while (current.nexElement != null){
            current = current.nexElement;
        }
        return current.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> _top = top;
            @Override
            public boolean hasNext() {
                return (_top != null);
            }

            @Override
            public E next() {
                E value = _top.value;
                _top = _top.nexElement;
                return value;
            }
        };
    }

    private void ensureNonEmpty(){
        if (isEmpty()){
            throw new IllegalStateException("No elements!");
        }
    }
}