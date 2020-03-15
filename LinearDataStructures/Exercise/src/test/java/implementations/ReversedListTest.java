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

    @Test
    public void testReamoveAt_extra() {
        ReversedList<Integer> myList_extra = new ReversedList();
        myList_extra.add(1);
        myList_extra.add(2);
        myList_extra.add(3);
        myList_extra.add(4);
        myList_extra.add(5);
        myList_extra.removeAt(1);
        for (int elment: myList_extra) {
            System.out.println(elment);
        }
    }
}