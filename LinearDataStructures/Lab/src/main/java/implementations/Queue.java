package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {

    private Node<E> top;
    private int size;

    private static class Node<E>{

        public E value;
        private Node<E> nexElement;
        Node(E element){
            this.value = element;
        }
    }

   public Queue(){
        this.top = null;
        this.size = 0;
    }

    @Override
    public void offer(E element) {
        Node<E> tmp = new Node<>(element);

        if (this.isEmpty()){
            this.top = tmp;
            size++;
            return;
        }
            //else
        Node<E> current = this.top;

        while(current.nexElement != null){
            current = current.nexElement;
        }
        current.nexElement = tmp;
        this.size++;
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E element = this.top.value;
        if (this.size == 1){
            this.top = null;
        } else {
            Node<E> tmp = this.top.nexElement;
            this.top.nexElement = null;
            this.top = tmp;
        }
        this.size--;
        return  element;
    }

    @Override
    public E peek() {
        ensureNonEmpty();

        return this.top.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = top;
            @Override
            public boolean hasNext() {
                return (current != null);
            }

            @Override
            public E next() {
                E currElement = current.value;
                current = current.nexElement;
                return currElement;
            }
        };
    }

    private void ensureNonEmpty() {
        if (this.isEmpty()){
            throw new IllegalStateException("No elements in this queue");
        }
    }
}
