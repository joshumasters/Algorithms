package sdossey.algorithms.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TestDoublyLinkedList 
{
    public interface FactoryLambda
    {
        List<Integer> build();
    }

    FactoryLambda factory;
    
    public TestDoublyLinkedList(FactoryLambda factory) {
        this.factory = factory;
    }

    private List<Integer> construct()
    {
        return factory.build();
    }

    public static void testListClass(FactoryLambda factory)    
    {
        TestDoublyLinkedList tests = new TestDoublyLinkedList(factory);
        tests.sizeZeroTests();
        tests.sizeOneTests();
        tests.mainTests();
    }

    public void sizeZeroTests()
    {
        List<Integer> testList = construct();
        assertEquals(testList.size(), 0);
        ListIterator<Integer> cursor = testList.listIterator();
        internalSizeZeroTests(cursor);
    }

    private void internalSizeZeroTests(ListIterator<Integer> cursor)
    {
        assertFalse(cursor.hasNext());
        assertFalse(cursor.hasPrevious());
        assertEquals(0, cursor.nextIndex());
        assertEquals(cursor.previousIndex(), -1);
        Class<? extends Throwable> e = NoSuchElementException.class;
        assertThrows(e, () -> {cursor.next();});
        assertThrows(e, () -> {cursor.previous();});

        e = IllegalStateException.class;
        assertThrows(e, () -> {cursor.set(5);});
        assertThrows(e, () -> {cursor.remove();});        
    }

    public void sizeOneTests()
    {
        List<Integer> testList = construct(); 
        ListIterator<Integer> cursor = testList.listIterator();
        cursor.add(32);
        assertEquals(testList.size(), 1);
        assertFalse(cursor.hasNext());
        assertTrue(cursor.hasPrevious());
        assertEquals(cursor.nextIndex(), 1);
        assertEquals(cursor.previousIndex(), 0);
        Class<? extends Throwable> e = NoSuchElementException.class;
        assertThrows(e, () -> {cursor.next();});
        e = IllegalStateException.class;
        assertThrows(e, () -> {cursor.set(5);});
        assertThrows(e, () -> {cursor.remove();});        

        //Okay, walk back.
        assertEquals(cursor.previous(), 32);
        assertEquals(testList.size(), 1);
        assertTrue(cursor.hasNext());
        assertFalse(cursor.hasPrevious());
        assertEquals(cursor.nextIndex(), 0);
        assertEquals(cursor.previousIndex(), -1);
        cursor.set(5);//set from previous
        assertEquals(cursor.next(), 5);
        assertEquals(cursor.nextIndex(), 1);
        assertEquals(cursor.previousIndex(), 0);
        cursor.set(6); //set from next
        assertEquals(cursor.previous(), 6);
        cursor.set(7);
        cursor.set(8); //Verify you can set more than once.
        assertEquals(cursor.next(), 8);
        cursor.remove(); // remove after next.
        
        internalSizeZeroTests(cursor);
        assertEquals(testList.size(), 0);
        cursor.add(17);
        assertEquals(cursor.previous(),17);
        cursor.remove(); // remove after previous.
        internalSizeZeroTests(cursor);
        assertEquals(testList.size(), 0);        
    }

    public void mainTests()
    {
        List<Integer> testList = construct();
        ListIterator<Integer> cursor = testList.listIterator();
             
        //Test adding at base.
        for(Integer i = 0; i < 100; ++i)
        {
            cursor.add(i);
            assertTrue(cursor.hasPrevious());
            assertEquals(testList.size(), i+1);
            assertEquals(cursor.nextIndex(), i+1);
            assertEquals(cursor.previousIndex(), i);            
        }
                
        //Check list backwards.
        for(Integer j=99; j >=0; --j)
        {
            assertEquals(cursor.nextIndex(), j+1);
            assertEquals(cursor.previousIndex(), j);
            assertEquals(cursor.hasNext(), j!=99);
            assertEquals(cursor.previous(), j);
            assertEquals(cursor.hasPrevious(), j!=0);
        }
        Class<? extends Throwable> e = NoSuchElementException.class;
        ListIterator<Integer> cursorTestA=cursor;
        
        assertThrows(e, () -> {cursorTestA.previous();});
                
        //Check list forwards.
        for(Integer i = 0; i < 100; ++i)
        {
            assertEquals(cursor.nextIndex(), i);
            assertEquals(cursor.previousIndex(), i-1);
            assertEquals(cursor.hasPrevious(), i!=0);
            assertEquals(cursor.next(), i);
            assertEquals(cursor.hasNext(), i!=99);            
        }
        ListIterator<Integer> cursorTestB=cursor;
        assertThrows(e, () -> {cursorTestB.next();});

        //Sanity check
        assertEquals(testList.size(), 100);

        //Now to test remove, 
        cursor = testList.listIterator(50);
        assertEquals(cursor.next(), 50);
        cursor.remove(); //Remove 50
        assertEquals(testList.size(), 99);
        assertEquals(cursor.nextIndex(), 50); // Was 51 before removal
        assertEquals(cursor.previousIndex(), 49); 
        e = IllegalStateException.class;
        ListIterator<Integer> cursorTestC=cursor;
        assertThrows(e, () -> {cursorTestC.set(5);});
        assertThrows(e, () -> {cursorTestC.remove();});        
        assertEquals(cursor.previous(), 49);
        cursor.remove(); //Remove 49 -- remove from previous 
        assertEquals(testList.size(), 98);
        assertEquals(49, cursor.nextIndex());
        assertEquals(cursor.previousIndex(), 48); 
        
        //backup and walk forward over changes.
        assertEquals(cursor.previous(), 48);
        assertEquals(cursor.next(), 48);
        assertEquals(cursor.next(), 51);
        assertEquals(cursor.next(), 52);
        assertEquals(cursor.nextIndex(), 51);
        assertEquals(cursor.previousIndex(), 50); 

        //walk backward over changes.
        assertEquals(cursor.previous(), 52);
        assertEquals(cursor.previous(), 51);
        assertEquals(cursor.previous(), 48);
        assertEquals(cursor.previous(), 47);
        assertEquals(cursor.nextIndex(), 47);
        assertEquals(cursor.previousIndex(), 46); 

        assertEquals(cursor.next(), 47);
        assertEquals(cursor.next(), 48);
        cursor.add(49);
        assertEquals(testList.size(), 99);
        e = IllegalStateException.class;
        ListIterator<Integer> cursorTestD=cursor;
        assertThrows(e, () -> {cursorTestD.set(5);});
        assertThrows(e, () -> {cursorTestD.remove();});        
        cursor.add(50);
        assertEquals(testList.size(), 100);
        assertEquals(cursor.nextIndex(), 51);
        assertEquals(cursor.previousIndex(), 50); 
        assertEquals(cursor.previous(), 50);
        assertEquals(cursor.previous(), 49);
        assertEquals(cursor.next(), 49);
        assertEquals(cursor.nextIndex(), 50);
        assertEquals(cursor.previousIndex(), 49); 
        //OKAY time to test set.
        assertEquals(cursor.next(), 50);
        cursor.set(500);
        cursor.set(5000); //Dual test set.
        assertEquals(cursor.previous(), 5000);
        cursor.set(500);
        cursor.set(50);
        assertEquals(cursor.next(), 50);
        assertEquals(cursor.nextIndex(), 51);
        assertEquals(cursor.previousIndex(), 50); 
        
        //Test bad indexes when getting ListITerator
        e = IndexOutOfBoundsException.class;        
        assertThrows(e, ()->{testList.listIterator(-1);});
        cursor = testList.listIterator(100); //Make sure this is valid;
        assertFalse(cursor.hasNext());
        assertThrows(e, ()->{testList.listIterator(101);});
         
        //Good enough for me.
        
    }
}
