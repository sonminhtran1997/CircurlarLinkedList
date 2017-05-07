package cs143;

import java.util.HashSet;
import java.util.Iterator;

/**
 * This data class represents an ordered collection in an endless circular list.
 *
 * @param <E> the type of values stored in the list
 * @author
 * @author
 * @author
 * @author
 */
public class EndlessList<E> implements Iterable<E> {

    //fields
    private Node cursor;

    public EndlessList() {
    }

    ;
    public EndlessList(Node first) {
        this.cursor = first;
    }

    /**
     * Adds a value before the current one and moves the cursor to the new
     * value. If the list is empty the value is simply added and becomes the
     * current one.
     *
     * @param value the value to add to the list
     */
    public void addPrev(E value) {
        // TODO write method body //
        Node newNode = new Node(value);
        if (cursor == null) {
            cursor = newNode;
            cursor.setNext(newNode);
            cursor.setPrev(newNode);
        } else {
            newNode.setNext(cursor);
            newNode.setPrev(cursor.getPrev());
            cursor.getPrev().setNext(newNode);
            cursor.setPrev(newNode);
            cursor = newNode;
        }
    }

    /**
     * Adds a value after the current one and moves the cursor to the new value.
     * If the list is empty the value is simply added and becomes the current
     * one.
     *
     * @param value the value to add to the list
     */
    public void addNext(E value) {
        // TODO write method body //
        Node newNode = new Node(value);
        if (cursor == null) {
            cursor = newNode;
            cursor.setNext(cursor);
            cursor.setPrev(cursor);
        } else {
            newNode.setPrev(cursor);
            newNode.setNext(cursor.getNext());
            cursor.getNext().setPrev(newNode);
            cursor.setNext(newNode);
            cursor = newNode;
        }
    }

    /**
     * Removes the current value from the list and moves the cursor to the next
     * value, returning the removed value. Returns a null if the list is empty.
     * If this is the last value in the list the cursor becomes null.
     *
     * @return the value removed
     */
    public E remove() {
        // TODO write method body //
        Node remove;
        Node prev;
        if (cursor == null) {
            return null;
        } else {
            remove = cursor;
            prev = cursor.getPrev();
            cursor = cursor.getNext();
            prev.setNext(cursor);
            cursor.setPrev(prev);
            return (E) remove.getValue();
        }
    }

    /**
     * Returns the value at the current cursor position. Returns a null if the
     * list is empty.
     *
     * @return the value
     */
    public E getValue() {
        // TODO write method body //
        if (cursor == null) {
            return null;
        } else {
            return (E) cursor.getValue();
        }
    }

    /**
     * Changes the current value at the current cursor position. Returns false
     * if the list is empty and true if the change is made.
     *
     * @param value the new value
     * @return true if successful, false if not
     */
    public boolean setValue(E value) {
        if (cursor == null) {
            return false;
        } else {
            cursor.setValue(value);
            return true;
        }
    }

    /**
     * Moves the cursor to the previous value in the list and returns that
     * value. Returns a null if the list is empty.
     *
     * @return the value
     */
    public E getPrev() {
        // TODO write method body //
        if (cursor == null) {
            return null;
        } else {
            cursor = cursor.getPrev();
            return (E) cursor.getValue();
        }
    }

    /**
     * Moves the cursor to the next value in the list and returns that value.
     * Returns null if the list is empty.
     *
     * @return the value
     */
    public E getNext() {
        // TODO write method body //
        if (cursor == null) {
            return null;
        } else {
            cursor = cursor.getNext();
            return (E) cursor.getValue();
        }
    }

    /**
     * Moves the cursor to the next occurrence of the given value, moving
     * forward in the list. If the value is not found the cursor remains at the
     * same position in the list.
     *
     * @param value the value to search for
     * @return true if the value is found, false if not
     */
    public boolean moveToNext(E value) {
        // TODO write method body //
        boolean found = false;
        Node tempCursor = cursor;
        while (!found) {
            cursor = cursor.getNext();
            if (cursor == tempCursor) {
                return false;
            }
            if (cursor.getValue().equals(tempCursor.getValue())) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Moves the cursor to the next occurrence of the given value, moving
     * backwards in the list. If the value is not found the cursor remains at
     * the same position in the list.
     *
     * @param value the value to search for
     * @return true if the value is found, false if not
     */
    public boolean moveToPrev(E value) {
        // TODO write method body //
        boolean found = false;
        Node tempCursor = cursor;
        while (!found) {
            cursor = cursor.getPrev();
            if (cursor == tempCursor) {
                return false;
            }
            if (cursor.getValue().equals(tempCursor.getValue())) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Provides and EndlessList iterator.
     *
     * @return the iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new EndlessListIterator();

    }

    /**
     * Private class used to create an EndlessList iterator.
     */
    private class EndlessListIterator implements Iterator<E> {

        //fields
        private final Node START = cursor;
        private boolean handledStart = false;

        /**
         * Reports if the current node has not been reported yet by calling
         * next(). Returns false if the list is empty.
         *
         * @return true if the current node has not been reported, false if it
         * has
         */
        @Override
        public boolean hasNext() {
//            // TODO write method body //
//            if (START.equals(null)) {
//                return false;
//            } else if (cursor.getNext().equals()) {
//
//            }
            return true;
        }

        /**
         * Returns the current value in the list and moves to the next.
         *
         * @return the current value, or null if the list is empty
         */
        @Override
        public E next() {
            // TODO write method body //
            if (cursor == null) {
                return null;
            } else {
                Node temp = cursor;
                cursor = cursor.getNext();
                return (E) temp.getValue();
            }
        }

        /**
         * Removes the last value returned from next(). This assumes that next
         * is called before each remove. If this is the only value in the list
         * the cursor becomes null.
         */
        @Override
        public void remove() {
            // TODO write method body //
        }

    }

}
