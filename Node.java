package Java.libraries.DATA_STRUCTURES.linkedList;


/*
 * a node of a linked list represents a vessel to hold the data which the linked list wil store
 * This linked list is a doubly linked list, meaning that each node stores a link to the next node in the list and the previous node
 * 
 * The variables stored in each node are the next and previous links as well as the actual data that the node is storing
 * 
 * the constructor of the class just sets the data passed to be the data stored in the node
 * it leaves next and previous as null since they will start off as null and be set to something by the list itself
 */

public class Node<T> {
    
    T data;
    Node<T> next;
    Node<T> prev;

    public Node(T data) {
        this.data = data;
    }


    
}
