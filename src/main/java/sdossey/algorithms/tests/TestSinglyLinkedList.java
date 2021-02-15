package sdossey.algorithms.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import sdossey.algorithms.util.AbstractSinglyLinkedList;

import java.util.NoSuchElementException;

public class TestSinglyLinkedList {

    public interface FactoryLambda
    {
        AbstractSinglyLinkedList<Integer> build();
    }

    FactoryLambda factory;
    
    public TestSinglyLinkedList(FactoryLambda factory) {
        this.factory = factory;
    }

    private AbstractSinglyLinkedList<Integer> construct()
    {
        return factory.build();
    }

    public static void testListClass(FactoryLambda factory)    
    {
        TestSinglyLinkedList tests = new TestSinglyLinkedList(factory);
        tests.sizeOneTests();
        tests.mainTests();
    }

    //Specifically tests situations with a list of size one and
    //size 0 to catch special cases.
    public void sizeOneTests()
    {
        AbstractSinglyLinkedList<Integer> testList = construct();
        AbstractSinglyLinkedList<Integer>.Cursor cursorA = testList.getCursor();
     
        //Test hasNext
        assertFalse(cursorA.hasNext());
        cursorA.add(32);
        assertTrue(cursorA.hasNext());    
        assertEquals(testList.size(), 1);
        assertEquals(cursorA.get(), 32);

        cursorA.advance();
        assertFalse(cursorA.hasNext());

        AbstractSinglyLinkedList<Integer>.Cursor cursorB;
        cursorB = testList.getCursor();
        cursorB.remove();
        assertFalse(cursorB.hasNext());
        assertEquals(testList.size(), 0);
        Class<? extends Throwable> e = NoSuchElementException.class;
        assertThrows(e, () -> {cursorB.advance();});
        assertThrows(e, () -> {cursorB.get();});
        assertThrows(e, () -> {cursorB.set(0);});
        assertThrows(e, () -> {cursorB.remove();});

        cursorB.add(32);
        assertTrue(cursorB.hasNext());    
        assertEquals(testList.size(), 1);
        assertEquals(cursorB.get(), 32);
    }

    public void mainTests()
    {
        AbstractSinglyLinkedList<Integer> testList = construct();
        AbstractSinglyLinkedList<Integer>.Cursor cursor = testList.getCursor();
     
        //Test hasNext
        assertFalse(cursor.hasNext());

        //Test adding at base.
        for(Integer i = 0; i < 100; ++i)
        {
            cursor.add(i);
            assertTrue(cursor.hasNext());
            assertEquals(testList.size(), i+1);               
        }
        
        //Check list.
        for(Integer i=99; i >= 0; --i)
        {
            assertEquals(cursor.next(), i);
            assertEquals(cursor.hasNext(), i!=0);
            assertEquals(testList.size(), 100);
        }
        //Test advancement at beginning.
        cursor = testList.getCursor();
        cursor.advance();
        assertEquals(cursor.get(), 98);

        //Test removal at beginning. (hard test)
        cursor = testList.getCursor();
        assertEquals(cursor.remove(), 99);
        assertEquals(cursor.get(), 98);
        assertEquals(testList.size(), 99);
        
        //Test add/removal/advancement/set in the middle of the list.
        cursor = testList.getCursor(49);
        cursor.advance();

        cursor.add(0);
        cursor.advance();
        cursor.add(1);
        cursor.advance();
        cursor.set(2);
        cursor.advance();
        cursor.remove();

        cursor = testList.getCursor();
        //Check for expected results.
        assertEquals(testList.size(), 100); //added 2 removed 1
        int i;
        for(i=98; i>=49; --i)
        {
            assertEquals(i, cursor.next());            
        }

        for(i=0; i<3; ++i)
        {
            Integer value = cursor.next();            
            assertEquals(i, value);
        }
        //We overrode one, and replace another so continuing at 47
        for(i=47; i<=0; --i)
        {
            assertEquals(i, cursor.next());
        }
    }
}
