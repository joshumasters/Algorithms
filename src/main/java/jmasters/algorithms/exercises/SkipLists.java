package jmasters.algorithms.exercises;

import java.util.AbstractSequentialList;
import java.util.ListIterator;

public class SkipLists<T extends Comparable<T>> extends AbstractSequentialList<T> {

    class SkipListNode<T extends Comparable<T>> {
        public SkipListNode<T>[] links;
        public int[] skipCounts; // Only necessary if looking up by index.
        public T data;


        public SkipListNode(SkipListNode<T>[] links, int[] skipCounts, T data) {
            this.links = links;
            this.skipCounts = skipCounts;
            this.data = data;
        }

        
    }

    

    public ListIterator<T> listIterator(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }
}
