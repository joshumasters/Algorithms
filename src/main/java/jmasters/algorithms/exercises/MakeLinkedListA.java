package jmasters.algorithms.exercises;

import java.util.NoSuchElementException;

import sdossey.algorithms.util.AbstractSinglyLinkedList;
import sdossey.algorithms.util.SinglyLinkedListNode;

public class MakeLinkedListA<E> extends AbstractSinglyLinkedList<E> {
    // Define a linked list
    // This linked list is gonna be of the form.
    // head -> [] -> [] -> []-> [] -> null
    SinglyLinkedListNode<E> head;

    // Private constructor, we use factory method below to build.
    // This is for esoteric reasons having to do with how we are
    // testing stuff, we want to be able to build new ones easily.
    private MakeLinkedListA() {
        head = null;
    }

    public static <E> AbstractSinglyLinkedList<E> factory(E type) {
        return new MakeLinkedListA<E>();
    }

    public class Cursor extends AbstractSinglyLinkedList<E>.Cursor {
        // Storing the current location of the list is
        // tricky. In order to do certain operations we
        // need the previous node, so best to store the current
        // location as a previous node. If the current location
        // is the head of the list, that can be signalled by
        // setting previous = null.
        SinglyLinkedListNode<E> previous;

        Cursor() {
            // set the cursor to the very beginng of list (head).
            previous = null;
        }

        // This is a helper function you should write
        // to the current node (like for a set or get)
        // given the value of previous. In most cases
        // this will be previous.next, but you have to handle
        // the case where previous == null, which means
        // we are before the head of list.
        @SuppressWarnings("unused") // Tjos just prevents a compiler error.
        private SinglyLinkedListNode<E> getCurrent() {
            // COMPLETE ME!
            if (previous == null) {
                return head;
            } else {
                return previous.next;
            }

        }

        @Override
        public void advance() {
            SinglyLinkedListNode<E> current = getCurrent();
            if (current == null) {
                throw new NoSuchElementException();
            } else {
                previous = current;
            }

            // COMPLETE ME!
        }

        @Override
        public E get() {
            // COMPLETE ME!
            SinglyLinkedListNode<E> current = getCurrent();
            if (current == null) {
                throw new NoSuchElementException();
            } else {
                return current.value;
            }
        }

        @Override
        public E set(E value) {
            // COMPLETE ME!
            // Add getCurrent = null check
            if (getCurrent() == null){
                throw new NoSuchElementException();
            } else {
                E currentValue = getCurrent().value;
                E oldValue = currentValue;
                getCurrent().value = value;
                return oldValue;
            }
            
        }

        @Override
        public void add(E newValue) {
            SinglyLinkedListNode<E> node = new SinglyLinkedListNode<E>(newValue, getCurrent());
            if (previous == null) {
                head = node;
            } else {
                previous.next = node;
            }

            // COMPLETE ME!
        }

        @Override
        public E remove() {
            SinglyLinkedListNode<E> current = getCurrent();
            if (current == null) {
                throw new NoSuchElementException();
            } else {
                // check if previous is null
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                return current.value;

            }

        }

        @Override
        public boolean hasNext() {
            if (getCurrent() == null) {
                return false;
            } else {
                return true;
            }

        }
    }

    @Override
    public AbstractSinglyLinkedList<E>.Cursor getCursor() {
        return new Cursor();
    }
}
