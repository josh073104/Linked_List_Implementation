package Java.libraries.DATA_STRUCTURES.linkedList;

/*
 * This linked list supports inserting to the front of the list, inserting to the end of the list, deleting an element of the linked list, searching the
 * linked list, checking if the list is empty, creating a string of the linked list, and printing out the linked list
 */

public interface LinkedListInterface<T> {
    
    void insertToFront(T data);

    void insertToTail(T data);

    void delete(T data);

    T search(T data);

    boolean isEmpty();

    String toString();

    void print();

}
