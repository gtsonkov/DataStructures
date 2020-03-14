package implementations;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testArrayDequeue(){
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(2);
        deque.add(3);
        deque.add(4);
        deque.add(5);
        deque.add(16);
        deque.addFirst(0);
        deque.addFirst(5);
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addFirst(0);
        int test = 1;
        Object ss = (Integer) test;
        int a = deque.remove(ss);
        deque.insert(11,100);
    }
}