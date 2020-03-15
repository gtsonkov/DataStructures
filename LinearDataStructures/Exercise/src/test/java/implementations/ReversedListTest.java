package implementations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReversedListTest {

    private ReversedList<Integer> myList = new ReversedList();
    @Before
    public void setUp() {
        for (int i = 0; i < 10; i++) {
            this.myList.add((i));
        }
    }

    @Test
    public void testGet() {
        System.out.println(this.myList.get(0));
        System.out.println(this.myList.get(9));
        System.out.println(this.myList.get(3));
    }

    @Test
    public void testRemoveAt() {
        int size = this.myList.size();
        System.out.println(size);
        this.myList.removeAt(0);
        size = this.myList.size();
        System.out.println(size);
        this.myList.removeAt(8);
        size = this.myList.size();
        System.out.println(size);
        this.myList.capacity();
        this.myList.removeAt(3);
        this.myList.removeAt(0);
        this.myList.removeAt(0);
        this.myList.removeAt(0);
        this.myList.removeAt(0);
        this.myList.removeAt(0);
        this.myList.removeAt(0);
    }

    @Test
    public void testIterator() {
        int tempElement = -1;
        for (int elment: this.myList) {
            System.out.println(elment);
        }
    }
}