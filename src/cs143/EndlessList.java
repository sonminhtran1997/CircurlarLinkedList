package cs143;

import java.util.Iterator;

/**
 * This data class represents an ordered collection in an endless circular list.
 *
 * @param <E> the type of values stored in the list
 * @author Janos Szamosfalvi
 * @author Son Minh Tran
 * @author Fotsing Takougang
 * @version 1.1 05/11/2017
 */
public class EndlessList<E> implements Iterable<E> {

    //fields
    private Node cursor;

    /**
     * Adds a value before the current one and moves the cursor to the new
     * value. If the list is empty the value is simply added and becomes the
     * current one.
     *
     * @param value the value to add to the list
     */
    public void addPrev(E value) {
        Node tmp;
        if (cursor == null) {
            tmp = new Node(value);
            tmp.setPrev(tmp);
            tmp.setNext(tmp);
        } else {
            tmp = new Node(value, cursor.getPrev(), cursor);
            tmp.getPrev().setNext(tmp);     // sets prev node.next to tmp 
            cursor.setPrev(tmp);            // sets cursor.prev to tmp        
        }
        cursor = tmp;                       // lastly, move the cursor
    }

    /**
     * Adds a value after the current one and moves the cursor to the new value.
     * If the list is empty the value is simply added and becomes the current
     * one.
     *
     * @param value the value to add to the list
     */
    public void addNext(E value) {
        Node tmp;
        if (cursor == null) {               // if the list is empty
            tmp = new Node(value);
            tmp.setNext(tmp);
            tmp.setPrev(tmp);
        } else {
            tmp = new Node(value, cursor, cursor.getNext());
            tmp.getNext().setPrev(tmp);     // sets next node.next to tmp 
            cursor.setNext(tmp);            // sets cursor.next to tmp       
        }
        cursor = tmp;
    }

    /**
     * Removes the current value from the list and moves the cursor to the next
     * value, returning the removed value. Returns a null if the list is empty.
     * If this is the last value in the list the cursor becomes null.
     *
     * @return the value removed
     */
    public E remove() {
        if (cursor == null) {
            return null;
        }
        Node<E> removedNode = cursor;
        if (cursor.getPrev() == cursor) {
            cursor = null;
        } else {
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            cursor = cursor.getNext();
        }
        return (E) removedNode.getValue();
    }

    /**
     * Returns the value at the current cursor position. Returns a null if the
     * list is empty.
     *
     * @return the value
     */
    public E getValue() {
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
        }
        cursor.setValue(value);
        return true;
    }

    /**
     * Moves the cursor to the previous value in the list and returns that
     * value. Returns a null if the list is empty.
     *
     * @return the value
     */
    public E getPrev() {
        if (cursor == null) {
            return null;
        }
        cursor = cursor.getPrev();
        return (E) cursor.getValue();
    }

    /**
     * Moves the cursor to the next value in the list and returns that value.
     * Returns null if the list is empty.
     *
     * @return the value
     */
    public E getNext() {
        if (cursor == null) {
            return null;
        }
        cursor = cursor.getNext();
        return (E) cursor.getValue();
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
        if (cursor == null) {
            return false;
        }
        
        Node startPos = cursor;
        do {
            cursor = cursor.getNext();
            if (cursor.getValue().equals(value)) {
                return true;
            }
        } while (cursor != startPos);
        return false;
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
        if (cursor == null) {
            return false;
        }

        Node startPos = cursor;
        do {
            cursor = cursor.getPrev();
            if (cursor.getValue().equals(value)) {
                return true;
            }
        } while (cursor != startPos);
        return false;
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
        private Node start = cursor;
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
            if (cursor == null) {
                return false;
            }
            if (cursor == start) {
                if (!handledStart) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        }

        /**
         * Returns the current value in the list and moves to the next.
         *
         * @return the current value, or null if the list is empty
         */
        @Override
        public E next() {
            if (cursor != null) {
                if (cursor == start) {
                    if (handledStart) {
                        return null;
                    } else {
                        handledStart = true;
                        E value = (E) cursor.getValue();
                        cursor = cursor.getNext();
                        return value;
                    }
                } else {
                    E value = (E) cursor.getValue();
                    cursor = cursor.getNext();
                    return value;
                }
            }
            return null;
        }

        /**
         * Removes the last value returned from next(). This assumes that next
         * is called before each remove. If this is the only value in the list
         * the cursor becomes null.
         */
        @Override
        public void remove() {
            getPrev();
            if (cursor != null) {
                if (cursor == start) {
                    start = cursor.getNext();
                    handledStart = false;
                }
            }
            EndlessList.this.remove();
        }

    }

}
