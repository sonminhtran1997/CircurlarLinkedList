package cs143;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * J-unit for testing the EndlessList methods
 *
 * @author Phuc Hong Le
 * @version 5/5/2017
 */
public class EndlessListTest {

    //fields
    EndlessList<Integer> numList;

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
        
    }

    /**
     * Test of getPrev method, of class EndlessList.
     */
    @Test
    public void testGetPrev() {
        
    }

    /**
     * Test of getNext method, of class EndlessList.
     */
    @Test
    public void testGetNext() {
        
    }

    /**
     * Test of moveToNext method, of class EndlessList.
     */
    @Test
    public void testMoveToNext() {
        
    }

    /**
     * Test of moveToPrev method, of class EndlessList.
     */
    @Test
    public void testMoveToPrev() {
        
    }

    /**
     * Test of iterator method, of class EndlessList.
     */
    @Test
    public void testIterator() {
        
    }
}