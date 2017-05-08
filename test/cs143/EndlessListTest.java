package cs143;

import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * J-unit for testing the EndlessList methods
 *
 * @author Son Minh Tran
 * @version 5/5/2017
 */
public class EndlessListTest {

    //fields
    EndlessList<Integer> numList;
    Node play = new Node(1,null, null);

    public EndlessListTest() {
    }

    @Before
    public void setUp() {
        numList = new EndlessList<>();
    }

    /**
     * Test of addPrev method, of class EndlessList.
     */
    @Test
    public void testAddPrev() {
        numList.addPrev(1);
        assertSame(1, numList.getValue());
        assertSame(1, numList.getNext());
        numList.getPrev();
        assertSame(1, numList.getPrev());
        
        numList.addPrev(2);
        assertSame(2, numList.getValue());
        assertSame(1, numList.getNext());
        numList.getPrev();
        assertSame(1, numList.getPrev());
        numList.getNext();
        
        numList.addPrev(3);
        assertSame(3, numList.getValue());
        assertSame(2, numList.getNext());
        numList.getPrev();
        assertSame(1, numList.getPrev());
    }

    /**
     * Test of addNext method, of class EndlessList.
     */
    @Test
    public void testAddNext() {
        numList.addNext(1);
        assertSame(1, numList.getValue());
        assertSame(1, numList.getNext());
        numList.getPrev();
        assertSame(1, numList.getPrev());
        
        numList.addNext(2);
        assertSame(2, numList.getValue());
        assertSame(1, numList.getNext());
        numList.getPrev();
        assertSame(1, numList.getPrev());
        numList.getNext();
        
        numList.addNext(3);
        assertSame(3, numList.getValue());
        assertSame(2, numList.getPrev());
        numList.getNext();
        assertSame(1, numList.getNext());
    }

    /**
     * Test of remove method, of class EndlessList.
     */
    @Test
    public void testRemove() {
        assertNull(numList.remove());
        numList.addNext(1);
        assertTrue(numList.remove() == 1);
        assertTrue(numList.getNext() == null);
        numList.addNext(12);
        numList.addNext(10);
        numList.addNext(8);
        numList.addNext(36);
        numList.addNext(10);
        numList.addNext(37);
        numList.addNext(25);
        assertTrue(numList.remove() == 25);
        assertTrue(numList.getNext() == 10);
        
        
    }

    /**
     * Test of getValue method, of class EndlessList.
     */
    @Test
    
    public void testGetValue() {
        assertNull(numList.getValue());
        numList.addNext(1);
        assertSame(1, numList.getValue());
        numList.addNext(2);
        assertSame(2, numList.getValue());
        numList.addNext(3);
        assertSame(3, numList.getValue());
        
    }

    /**
     * Test of setValue method, of class EndlessList.
     */
    @Test
    public void testSetValue() {
        assertFalse(numList.setValue(2));
        numList.addNext(1);
        assertTrue(numList.setValue(2));
        assertSame(2, numList.getValue());
    }

    /**
     * Test of getPrev method, of class EndlessList.
     */
    @Test
    public void testGetPrev() {
        assertNull(numList.getPrev());
        numList.addNext(1);
        assertSame(1, numList.getPrev());
        numList.addNext(12);
        numList.addNext(24);
        numList.addNext(10);
        numList.addNext(35);
        assertSame(10, numList.getPrev());
        assertSame(24, numList.getPrev());
    }

    /**
     * Test of getNext method, of class EndlessList.
     */
    @Test
    public void testGetNext() {
        assertNull(numList.getNext());
        numList.addNext(1);
        assertSame(1, numList.getNext());
        numList.addNext(12);
        numList.addNext(24);
        numList.addNext(10);
        numList.addNext(35);
        assertSame(1, numList.getNext());
        assertSame(12, numList.getNext());
    }

    /**
     * Test of moveToNext method, of class EndlessList.
     */
    @Test
    public void testMoveToNext() {
        numList.addNext(12);
        numList.addNext(10);
        numList.addNext(8);
        assertFalse(numList.moveToNext(2));
        numList.addNext(36);
        numList.addNext(10);
        numList.addNext(37);
        numList.addNext(25);
        assertTrue(numList.moveToNext(10));
        assertTrue(numList.getPrev() == 12);
        
    }

    /**
     * Test of moveToPrev method, of class EndlessList.
     */
    @Test
    public void testMoveToPrev() {
        numList.addNext(12);
        numList.addNext(10);
        numList.addNext(8);
        assertFalse(numList.moveToPrev(2));
        numList.addNext(36);
        numList.addNext(10);
        numList.addNext(37);
        numList.addNext(25);
        assertTrue(numList.moveToPrev(10));
        assertTrue(numList.getPrev() == 36);
    }

    /**
     * Test of iterator method, of class EndlessList.
     */
    @Test
    public void testIterator() {
        assertNull(numList.iterator().next());
        assertFalse(numList.iterator().hasNext());
        numList.addNext(1);
        assertTrue(numList.iterator().hasNext());
        numList.addNext(2);
        numList.addNext(3);
        numList.addNext(4);
        Iterator it = numList.iterator();
        assertTrue((Integer)it.next() == 4);
        assertTrue((Integer) it.next() == 1);
        assertTrue(it.hasNext());
        assertTrue((Integer) it.next() == 2);
        assertTrue((Integer) it.next() == 3);
        assertFalse(it.hasNext());
        it.next();
        assertFalse(it.hasNext());
        Iterator it2 = numList.iterator();
        
        
        
        
        
    }
}