package jmasters;

import org.junit.jupiter.api.Test;

import jmasters.algorithms.exercises.MakeLinkedListA;
import sdossey.algorithms.tests.TestSinglyLinkedList;

public class TestMakeLinkedListA 
{
    @Test
    public void testListA()
    {   
        TestSinglyLinkedList.testListClass( () -> MakeLinkedListA.factory((Integer)0) );       
    }
    
}
