package cs143;

import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests all aspects of EndlessList
 *
 * @author Janos Szamosfalvi
 * @author Son Minh Tran
 * @author Fotsing Takougang
 * @version 1.0 05/11/2017
 */
public class EndlessListTest {

    EndlessList<Integer> list;
    EndlessList<String> strList;

    public EndlessListTest() {
    }

    @Before
    public void setUp() {
        list = new EndlessList<>();
        strList = new EndlessList<>();
    }

    /**
     * Test of addPrev method, of class EndlessList.
     */
    @Test
    public void testAddPrev() {
        //Test for an empty list
        list.addPrev(5);
        assertSame(list.getNext(), list.getPrev());
        assertEquals(list.getNext(), new Integer(5));

        //Test when the list has one element
        list.addPrev(15);
        assertSame(list.getPrev(), 5);
        assertSame(list.getNext(), 15);

        //Test when the list has multiple elements
        list.addPrev(18);
        assertSame(list.getNext(), 15);
        assertSame(list.getNext(), 5);
        assertSame(list.getNext(), 18);
        assertSame(list.getPrev(), 5);
        assertSame(list.getPrev(), 15);
        assertSame(list.getPrev(), 18);
    }

    /**
     * Test of addNext method, of class EndlessList.
     */
    @Test
    public void testAddNext() {
        //Test for an empty list
        list.addNext(5);
        assertSame(list.getNext(), list.getPrev());
        assertEquals(list.getNext(), new Integer(5));

        //Test when the list has one element
        list.addNext(15);
        assertSame(list.getPrev(), 5);
        assertSame(list.getPrev(), 15);

        //Test when the list has multiple elements
        list.addNext(18);
        assertSame(list.getNext(), 5);
        assertSame(list.getNext(), 15);
        assertSame(list.getNext(), 18);
        assertSame(list.getPrev(), 15);
        assertSame(list.getPrev(), 5);
        assertSame(list.getPrev(), 18);
    }

    /**
     * Test of remove method, of class EndlessList.
     */
    @Test
    public void testRemove() {
        //if the list is empty
        assertNull(list.remove());

        //if the list has one element
        list.addNext(15);
        assertSame(list.remove(), 15);
        assertNull(list.remove());

        //If the list has multiple elements
        list.addNext(5);
        list.addNext(10);
        list.addNext(15);
        list.remove();
        assertSame(list.getNext(), 10);
        assertSame(list.getNext(), 5);
        assertSame(list.getPrev(), 10);
        assertSame(list.getPrev(), 5);
    }

    /**
     * Test of getValue method, of class EndlessList.
     */
    @Test
    public void testGetValue() {
        //empty list
        assertNull(list.getValue());

        //non empty list
        list.addNext(5);
        assertSame(list.getValue(), 5);
    }

    /**
     * Test of setValue method, of class EndlessList.
     */
    @Test
    public void testSetValue() {
        //empty list
        assertFalse(list.setValue(12));

        //non empty list
        list.addNext(15);
        assertTrue(list.setValue(12));
    }

    /**
     * Test of getPrev method, of class EndlessList.
     */
    @Test
    public void testGetPrev() {
        //empty list
        assertNull(list.getPrev());

        //list with one element
        list.addNext(15);
        assertSame(list.getPrev(), 15);

        //list with multiple elements
        list.addNext(12);
        assertSame(list.getPrev(), 15);
    }

    /**
     * Test of getNext method, of class EndlessList.
     */
    @Test
    public void testGetNext() {
        //empty list
        assertNull(list.getNext());

        //list with one element
        list.addNext(15);
        assertSame(list.getNext(), 15);

        //list with multiple elements
        list.addNext(12);
        assertSame(list.getNext(), 15);
    }

    /**
     * Test of moveToNext method, of class EndlessList.
     */
    @Test
    public void testMoveToNext() {
        //Empty list
        assertFalse(list.moveToNext(12));

        //List with one element
        list.addNext(12);
        assertTrue(list.moveToNext(12));
        assertFalse(list.moveToNext(15));

        //List with multiple elements
        strList.addNext("John");
        strList.addNext(new String("Linda"));
        String firstLinda = strList.getValue();
        strList.addNext("Mark");
        strList.addNext("James");
        strList.addNext(new String("Linda"));
        String secondLinda = strList.getValue();
        strList.addNext("Johnson");
        assertTrue(strList.moveToNext("Linda"));
        String thisLinda = strList.getValue();
        assertTrue(thisLinda == firstLinda);
        assertFalse(thisLinda == secondLinda);
        strList.moveToNext("Linda");
        thisLinda = strList.getValue();
        assertFalse(thisLinda == firstLinda);
        assertTrue(thisLinda == secondLinda);
        strList.moveToNext("A guy");
        assertEquals(strList.getValue(), "Linda");

    }

    /**
     * Test of moveToPrev method, of class EndlessList.
     */
    @Test
    public void testMoveToPrev() {
        //Empty list
        assertFalse(list.moveToPrev(12));

        //List with one element
        list.addNext(12);
        assertTrue(list.moveToPrev(12));
        assertFalse(list.moveToPrev(15));

        //List with multiple elements
        strList.addNext("John");
        strList.addNext(new String("Linda"));
        String firstLinda = strList.getValue();
        strList.addNext("Mark");
        strList.addNext("James");
        strList.addNext(new String("Linda"));
        String secondLinda = strList.getValue();
        strList.addNext("Johnson");
        assertTrue(strList.moveToPrev("Linda"));
        String thisLinda = strList.getValue();
        assertFalse(thisLinda == firstLinda);
        assertTrue(thisLinda == secondLinda);
        strList.moveToPrev("Linda");
        thisLinda = strList.getValue();
        assertTrue(thisLinda == firstLinda);
        assertFalse(thisLinda == secondLinda);
        strList.moveToPrev("A guy");
        assertEquals(strList.getValue(), "Linda");
    }

    /**
     * Exhaustive testing of all iterator methods with lists containing 
     * 0, 1, 2, or 3 entries.
     */
    @Test
    public void testIterator() {
        EndlessList<String> cl = new EndlessList<>();   // Circular List 
        
        System.out.println("Testing list with zero entry");
        System.out.println(" - attempt to access");
        Iterator itr = cl.iterator();
        assertFalse(itr.hasNext()); 
        assertNull(itr.next()); 
        itr.remove(); 
        assertFalse(itr.hasNext()); 
        assertNull(itr.next()); 
        
        System.out.println("Testing list with one entry");
        cl.addNext("A"); 
        System.out.println(" - reading");
        itr = cl.iterator();
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "A"); 
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());    
        
        System.out.println(" - deletion");
        itr = cl.iterator();
        assertTrue(itr.next() == "A"); 
        itr.remove(); 
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());   
        
        System.out.println(" - verify deletion");
        itr = cl.iterator();
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());   
        
        System.out.println("Testing list with two entries");
        cl.addNext("1"); 
        cl.addNext("2"); 
        System.out.println(" - traverse list");
        itr = cl.iterator();
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "2"); 
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "1"); 
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());    
        
        System.out.println(" - delete all");
        itr = cl.iterator();
        assertTrue(itr.next() == "2"); 
        itr.remove(); 
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "1"); 
        itr.remove(); 
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());   
        
        System.out.println(" - verify deletion");
        itr = cl.iterator();
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());   
        
        System.out.println(" - delete 1st");
        cl.addNext("1"); 
        cl.addNext("2"); 
        itr = cl.iterator();
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "2"); 
        itr.remove(); 
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "1"); 
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());  
        System.out.println(" - verify remainder");
        itr = cl.iterator();
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "1"); 
        // clean the list 
        itr = cl.iterator();
        while (itr.hasNext()) { 
            itr.next();
            itr.remove();
        } 
        
        System.out.println(" - delete 2nd");
        cl.addNext("1"); 
        cl.addNext("2"); 
        itr = cl.iterator();
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "2"); 
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "1"); 
        itr.remove(); 
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());  
        System.out.println(" - verify remainder");
        itr = cl.iterator();
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "2"); 
        // clean the list 
        itr = cl.iterator();
        while (itr.hasNext()) { 
            itr.next();
            itr.remove();
        } 
  
        System.out.println("Testing list with three entries");
        System.out.println(" - traverse list");
        cl.addNext("x"); 
        cl.addNext("y"); 
        cl.addNext("z"); 
        itr = cl.iterator();
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "z"); 
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "x"); 
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "y"); 
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());    
        
        System.out.println(" - renove all");
        itr = cl.iterator();
        assertTrue(itr.hasNext());
        assertTrue(itr.next() == "z");
        itr.remove(); 
        assertTrue(itr.hasNext());
        assertTrue(itr.next() == "x"); 
        itr.remove(); 
        assertTrue(itr.hasNext());
        assertTrue(itr.next() == "y"); 
        itr.remove(); 
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());    
        System.out.println(" - verify deletion");
        itr = cl.iterator();
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());   
        
        System.out.println(" - remove 1st");
        cl.addNext("x"); 
        cl.addNext("y"); 
        cl.addNext("z"); 
        itr = cl.iterator();
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "z"); 
        itr.remove(); 
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "x"); 
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "y"); 
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());  
        // clean the list 
        itr = cl.iterator();
        while (itr.hasNext()) { 
            itr.next();
            itr.remove();
        }   
        
        System.out.println(" - remove 2nd");
        cl.addNext("x"); 
        cl.addNext("y"); 
        cl.addNext("z"); 
        itr = cl.iterator();
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "z"); 
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "x"); 
        itr.remove(); 
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "y"); 
        assertFalse(itr.hasNext()); 
        assertNull(itr.next()); 
        // clean the list 
        itr = cl.iterator();
        while (itr.hasNext()) { 
            itr.next();
            itr.remove();
        } 
        
        System.out.println(" - remove 3rd");
        cl.addNext("x"); 
        cl.addNext("y"); 
        cl.addNext("z"); 
        itr = cl.iterator();
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "z"); 
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "x"); 
        assertTrue(itr.hasNext()); 
        assertTrue(itr.next() == "y"); 
        itr.remove(); 
        assertFalse(itr.hasNext()); 
        assertNull(itr.next());     
        // clean the list 
        itr = cl.iterator();
        while (itr.hasNext()) { 
            itr.next();
            itr.remove();
        }                                   
    }

    /**
     * Test of Iterator hasNext() method
     */
    @Test
    public void testIteratorHasNext() {
        Iterator<Integer> itr = list.iterator();
        assertFalse(itr.hasNext());

        list.addNext(15);
        list.addNext(20);
        list.addNext(25);
        list.addNext(30);
        itr = list.iterator();
        assertTrue(itr.hasNext());
    }

    /**
     * Test of Iterator hasNext() method
     */
    @Test
    public void testIteratorNext() {
        Iterator<Integer> itr = list.iterator();
        assertNull(itr.next());

        list.addNext(15);
        list.addNext(20);
        list.addNext(25);
        list.addNext(30);
        itr = list.iterator();
        assertSame(itr.next(), 30);
        assertSame(itr.next(), 15);
        assertSame(itr.next(), 20);
        assertSame(itr.next(), 25);
        assertNull(itr.next());
    }

    /**'
     * Test of Iterator remove() method
     */
    @Test
    public void testIteratorRemove() {
        //1: Test of remove when the list is empty
        boolean crashed = false;
        Iterator<Integer> itr;
        try {
            itr = list.iterator();
            itr.remove();
        } catch (Exception e) {
            crashed = true;
        }
        assertFalse(crashed);

        list.addNext(15);
        list.addNext(20);
        list.addNext(25);
        list.addNext(30);
        itr = list.iterator();
        itr.next();
        itr.remove();
        itr.next();
        itr.next();
        itr.next();
        assertNull(itr.next());

        //2: Test when removing the starting element of the iterator
        Iterator itr2 = list.iterator();
        int count = 0;
        while (itr2.hasNext()) {
            System.out.println(itr2.next());
            if (count == 0) {
                itr2.remove();
            }
            count++;
        }
        assertEquals(count, 3);

        //3: Test when removing and element different from the starting element
        //      of the iterator
        list = new EndlessList<>();
        list.addNext(20);
        list.addNext(30);
        list.addNext(35);
        list.addNext(40);
        Iterator<Integer> itr3 = list.iterator();
        assertTrue(itr3.hasNext());
        assertSame(40, itr3.next());
        assertSame(20, itr3.next());
        itr3.remove();
        assertSame(30, itr3.next());
    }

}
