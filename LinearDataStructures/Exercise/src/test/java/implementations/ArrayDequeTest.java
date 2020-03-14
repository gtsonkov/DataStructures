package implementations;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    private ArrayDeque<Integer> deque;

    @Before
    public void setUp() {
        this.deque = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            this.deque.offer((i));
        }
    }

    @Test
   public void testAddOperations(){
        this.deque.add(1);
        this.deque.add(2);
        this.deque.add(3);
        this.deque.add(4);
        this.deque.add(5);
        this.deque.add(16);
        this.deque.addFirst(0);
        this.deque.addFirst(5);
        this.deque.addFirst(4);
        this.deque.addFirst(3);
        this.deque.addFirst(2);
        this.deque.addFirst(1);
        this.deque.addFirst(16);
    }

    @Test
    public void testArrayDequeueRemove(){
        int test = 6;
        Object obj = (Integer) test;
        int ab = deque.get(obj);
        this.deque.remove(3);
        this.deque.removeFirst();
        this.deque.removeLast();
    }

    @Test
    public void testArrayDequeueForeach(){
        for (Integer part: deque) {
            System.out.println(part);
        }
    }

    @Test
    public  void testInsertOperation(){

        deque.insert(9,100);
        deque.insert(0,200);
        deque.insert(3,300);
        int test = 6;
        Object obj = (Integer) test;
        int result = deque.remove(obj);
    }
}