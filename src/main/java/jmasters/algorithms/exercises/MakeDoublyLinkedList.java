
/* 
* Copy this to src/main/java/jmasters/exercises and modify it so that you
* implement a doubly linked list class that implements the full 
* java.util.List interface.
* 
* This class will make use of java.util.AbstractSequentialList:
* 
* https://docs.oracle.com/javase/8/docs/api/java/util/AbstractSequentialList.html
* 
* AbstractSequentialList does a lot of the work for you implementing the 
* various methods of java.util.List. However, you still have to implement
* a ListIterator (which we call Cursor and use an inner class)
* and a size() method. We've already implemented the size method,
* but you'll have to update the internally tracked size with each
* operation.
* 
* To see the interface you need to implement for Cursor, look 
* up the Javadoc for java.util.ListIterator. Complete the 
* "Complete Me!" parts of the code.
* 
* https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html
* 
* There is JUnit test code in:
*  src/main/test/java/jmasters/TestMakeDoublyLinkedList.java
*/
package jmasters.algorithms.exercises;

import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;
//It is okay to add imports if necessary.
import java.util.NoSuchElementException;

import sdossey.algorithms.util.DoublyLinkedListNode;

public class MakeDoublyLinkedList<E> extends AbstractSequentialList<E> {
    DoublyLinkedListNode<E> head;
    DoublyLinkedListNode<E> tail;
    int size = 0;

    public class Cursor<T> implements ListIterator<E> {
        int listIndex;
        DoublyLinkedListNode<E> previous;
        DoublyLinkedListNode<E> lastReturnedNode;

        Cursor() {
            listIndex = 0;
            previous = head;
            lastReturnedNode = null;
        }

        public DoublyLinkedListNode<E> getCurrent() {
            if (previous.getNext() == tail) {
                throw new NoSuchElementException();
            } else {
                return previous.getNext();
            }

        }

        @Override
        public void add(E value) {
            // COMPLETE ME!
            DoublyLinkedListNode<E> node = new DoublyLinkedListNode<E>(previous, previous.getNext(), value);
            DoublyLinkedListNode<E> nextNode = previous.getNext();
            previous.setNext(node);
            nextNode.setPrevious(node);
            previous = node;
            lastReturnedNode = null;
            listIndex++;
            size++;
        }

        @Override
        public boolean hasNext() {
            if (previous.getNext() == tail) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public boolean hasPrevious() {
            // COMPLETE ME!
            if (previous == head) {
                return false;
            } else {
                return true;
            }

        }

        @Override
        public E next() {
            DoublyLinkedListNode<E> node = getCurrent();
            E valueToReturn = node.getValue();
            previous = node;
            lastReturnedNode = node;
            listIndex++;
            return valueToReturn;
            // COMPLETE ME
        }

        @Override
        public int nextIndex() {
            return listIndex;

        }
        // COMPLETE ME!

        @Override
        public E previous() {
            if (previous == head) {
                throw new NoSuchElementException();
            }
            DoublyLinkedListNode<E> node = previous;
            E valueToReturn = node.getValue();
            previous = previous.getPrevious();
            lastReturnedNode = node;
            listIndex--;
            return valueToReturn;
        }

        @Override
        public int previousIndex() {
            // COMPLETE ME!
            return listIndex - 1;
        }

        @Override
        public void remove() {
            // COMPLETE ME!
            if (lastReturnedNode == null) {
                throw new IllegalStateException();
            }
            DoublyLinkedListNode<E> nodeToRemove = lastReturnedNode;
            DoublyLinkedListNode<E> previousNode = nodeToRemove.getPrevious();
            DoublyLinkedListNode<E> nextNode = nodeToRemove.getNext();
            previousNode.setNext(nextNode);
            nextNode.setPrevious(previousNode);
            if (lastReturnedNode == previous) {
                previous = previousNode;
                listIndex--;
            } 
            size--;
            lastReturnedNode = null;
        }

        @Override
        public void set(E value) {
            // COMPLETE ME!
            if (lastReturnedNode == null) {
                throw new IllegalStateException();
            }
            lastReturnedNode.setValue(value);
        }

    }

    public MakeDoublyLinkedList() {
        // Head and tail are both sentinel values.
        head = new DoublyLinkedListNode<E>(null, null, null);
        tail = new DoublyLinkedListNode<E>(null, head, null);
        head.setNext(tail);
        size = 0;
    }

    public static <E> List<E> factory(E type) {
        return new MakeDoublyLinkedList<E>();
    }

    @Override
    public ListIterator<E> listIterator(int offset) {
        Cursor<E> cursor = new Cursor<>();

        /*
         * Note that is legal for the ListIterator to point just past the last element,
         * thus > comparison rather than >= comparison.
         */
        if ((offset < 0) || (offset > this.size())) {
            throw new IndexOutOfBoundsException();
        }
        if (offset > this.size()) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < offset; ++i) {
            cursor.next();
        }
        return cursor;
    }

    @Override
    public int size() {
        return size;
    }

}
