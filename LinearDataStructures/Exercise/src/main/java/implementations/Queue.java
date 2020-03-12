package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> top;
    private Node<E> last;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node(E element) {
            this.element = element;
        }
    }

    public Queue() {
    }

    @Override
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.top == null) {
            this.top = newNode;
            this.last = newNode;
        } else {
            this.last.next = newNode;
            this.last = newNode;
        }
        this.size++;
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E element = this.top.element;
        if (this.size == 1) {
            this.top = null;
            this.last = null;
        } else {
            Node<E> next = this.top.next;
            this.top.next = null;
            this.top = next;
        }
        this.size--;
        return element;
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return this.top.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = top;

            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override
            public E next() {
                E element = this.current.element;
                this.current = this.current.next;
                return element;
            }
        };
    }

    private void ensureNonEmpty() {
        if (this.size == 0) {
            throw new IllegalStateException("Illegal operation on empty stack");
        }
    }
}
