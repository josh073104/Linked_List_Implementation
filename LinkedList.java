package Java.libraries.DATA_STRUCTURES.linkedList;

/*
 * A linked list is a type of data structure that is similar to an array in the sense that it stores the same type of data in a list
 * It is differnt because you cannot really index into a linked list like you can in an array
 * you cannot do linkedlist[5], you would have to iterate to that index or just find the data itself
 * Because of this insability to index into a linked list, linked lists aren't as good for searching and sorting
 * 
 * Where a linked list is better than an array, however, is that it is very easy to insert or remove an element from anywhere in the list
 * In order to insert or delete an element, all you have to do is change the next and previous links of the surrounding elements accordingly
 * To remove, remove all the links to the element you wish to remove and chagne the surrounding elements' links to 'skip over' the removed element
 * To add, make the element being added's links point to the surrounding elements, and make the surrounding elements' links point back at the new element
 * 
 * The nature of insertion to a linked list also means that linked lists do not have a set static size like arrays do. This means you can insert an 
 * unlimited amount of elements to a linked list (until the computer runs out of memory)
 * 
 * Linked lists do require slightly more memory than arrays though
 * Arrays simply store the raw data into themselves, linked lists use node objects each of which contain variables to hold the data and the links
 * This takes up more space than an arrays contiguous block of memory
 * 
 * Linked lists are memory efficient though meaning you do not need to guess the size of one in advance like you may have to with an array, they will 
 * grow and shrink automatically as needed
 */

public class LinkedList<T> implements LinkedListInterface<T> {

    /*
     * The linked list itself stores 3 variables: the head of the list (first elements), the tail of the list (last element), and the number of elements
     * in the linked list
     * The number of elements starts out at zero
     */

    Node<T> head;
    Node<T> tail;
    int numElements = 0;

    /*
     * The consturctor for the linked list just sets the head and tail of the list to null, since no elements have been added yet
     */

    public LinkedList() {
        head = null;
        tail = null;
    }

    /*
     * The insertToFront() method takes in data of type T and creates a new node with that data
     * If the linked list is empty, it sets both the head and the tail to the new node, since it is the only element in the list
     * 
     * If the linked list is not empty, it sets the node's next link to be the head (since it is being added to the front in front of the current head),
     * the node's previous link to null (since the node will become the head and have nothing in front of it), the current head's previous link to be the 
     * new node (since the node is being added in front of the head and so the head must be linked backwards to the new node), and finally it overrites the 
     * head to be the new node
     * 
     * finally it increments the number of elements by 1
     */

    @Override
    public void insertToFront(T data) {
        Node<T> node = new Node<>(data);

        if (isEmpty()) {
            head = node;
            tail = node;
        }
        else {
            node.next = head;
            node.prev = null;
            head.prev = node;
            head = node;
        }

        numElements ++;
    }

    /*
     * Inserting to the tail is similar to inserting to the head only to the end of the list
     * Again, if the list is empty, it sets both the head and tail to the new node
     * 
     * If it is not empty, it sets the new node's next link to null (since it is being placed at the end and will have nothing after it), the new node's
     * previous to be the tail (since it is being inserted after the tail and must be linked backwards to the current tail), the current tail's next to be
     * the new node (since the new node is inserted after the current tail so the current tail must be linked forwards to the new node), and finally it
     * overrites the tail to be the new node
     * 
     * Lastly, it increments the number of elements by 1
     */

    @Override
    public void insertToTail(T data) {
        Node<T> node = new Node<>(data);

        if (isEmpty()) {
            head = node;
            tail = node;
        }
        else {
            node.next = null;
            node.prev = tail;
            tail.next = node;
            tail = node;
        }

        numElements ++;
    }

    /*
     * this method takes in the data to be inserted and the data to insert it after
     * it then creates a new node with the data to be inserted
     * If the list is empty, it simply sets the new node to be the head and tail
     * Otherwise, it sets a current variable to the head of the list and then iterates through the list until either the current node becomes null, or
     * the data to insert the new node after is reached
     * 
     * if the data to be inserted after is reached, two situations arise
     * This data to be inserted after can be the tail or any other node
     * If it is the tail, the new node is inserted after the current tail and becomes the new tail
     * 
     * If it is any other node, the new node is inserted after this any other node and the surrounding elements' links are changed accordingly
     * If the data to be inserted after is found, the loop is broken out of to prevent it from continuing onwards after the correct spot has already been 
     * found
     * 
     * If the loop finished all the way through without finding the data to be inserted after, then that data is not in the list and the new node is just
     * inserted to the end of the list as the new tail
     * 
     * finally the number of elements is incremented by one
     */

    public void insertAfter(T data, T afterData) {
        Node<T> node = new Node<>(data);

        if (isEmpty()) {
            head = node;
            tail = node;
        }
        else {
            Node<T> current = head;
            while (current != null) {
                if (current.data.equals(afterData)) {
                    if (current.next == null) {
                        current.next = node;
                        node.prev = current;
                        tail = node;
                        break;
                    }
                    else {
                        node.prev = current;
                        node.next = current.next;
                        current.next = node;
                        break;
                    }
                }
                current = current.next;
            }
            if (current == null) {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            numElements ++;
        }
    }

    /*
     * This method is similar to the insertAfter() method, but instead inserts a node before the specified data
     * It first creates a new node with the data to be inserted
     * 
     * Then if the list is empty, it sets this new node to be the head and tail of the list
     * Otherwise, it creates a current variable and sets it equal to the head
     * It then iterates through the list until either current is null or the data to be inserted before is found
     * If the data to be inserted before is found, it will either be the head of the list, or any other node of the list
     * If it is the head, the new node is inserted before the head and the head is overriten to be the new node
     * If the data to be inserted before is any other element in the list, the new node is inserted before it and the surrounding elements' links are changed
     * accordingly
     * If the data to be insesrted before is found the loop also breaks to ensure that it does not continure unnecessarily after the data has already been
     * found
     * 
     * If the loop finishes and current is null, this means that the data to be inserted before is not in the the list and so in that case the new node
     * is inserted to the end of the list as the new tail
     * 
     * finally the number of elements is incremented by one
     */

    public void insertBefore(T data, T beforeData) {
        Node<T> node = new Node<>(data);

        if (isEmpty()) {
            head = node;
            tail = node;
        }
        else {
            Node<T> current = head;
            while (current != null) {
                if (current.data.equals(beforeData)) {
                    if (current.prev == null) {
                        current.prev = node;
                        node.next = current;
                        head = node;
                        break;
                    }
                    else {
                        node.next = current;
                        node.prev = current.prev;
                        current.prev.next = node;
                        break;
                    }
                }
                current = current.next;
            }
            if (current == null) {
                tail.next = node;
                node.prev = tail;
                tail = node;
            }
            numElements ++;
        }
    }

    /*
     * The delete() method takes in the data that the user wants to delete
     * 
     * If the list is empty, it prints out a message that nothing can be removed from an empty list and returns
     * 
     * It sets a current variable equal to the head to start at the front of the list
     * 
     * It then iterates through the list while current is not null
     * If the data we want to delete is found (current's data is equal to the data we passed through) then three situations can arise:
     * The current node to be deleted is the head, the current node to be deleted is the tail, and the current node to be deleted is some interior node
     * 
     * If the current node is the head, then the node after the current node must become the new head since the current head is being deleted
     * The node after current's previous link is set to null to unlink the current head node, the node after current is set to be the new head and the number of elements is decreased
     * by one
     * The loop is broken out of to prevent the method from deleting any other elements that might match the data we passed through
     * 
     * If the current node is the tail, then the node before it must become the new tail since it is being deleted
     * The node before the current node's next is set to null, to unlink the current tail, the tail is set to the node before the current node, the number
     * of elements is decreased by one, and finally the loop is broken to prevent any other nodes from being deleted
     * 
     * Finally, if the node is an interior node, its surrounding nodes' links are changed accordinly to unlink this current node in between them (basically)
     * the surrounding two nodes are linked to each other instead of the current node and finaly the number of elements is decreased and the loop is broken
     */

    @Override
    public void delete(T data) {

        if (numElements == 0) {
            System.out.println("Cannot remove something from an empty list!");
            return;
        }

        Node<T> current = head;

        while (current != null) {
            if (current.data.equals(data)) {
                if (current.prev == null) {
                    current.next.prev = null;
                    head = current.next;
                    numElements --;
                    break;
                }
                else if (current.next == null) {
                    current.prev.next = null;
                    tail = current.prev;
                    numElements --;
                    break;
                }
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    numElements --;
                    break;
                }
            }
            current = current.next;
        }
    }

   /*
     * The deleteAll() method is very similar to the delete() method so the original explanation is below:
     * it takes in the data that the user wants to delete
     * 
     * If the list is already empty, the method prints out a message that nothing can be removed from an empty list and returns
     * 
     * It sets a current variable equal to the head to start at the front of the list
     * 
     * It then iterates through the list while current is not null
     * If the data we want to delete is found (current's data is equal to the data we passed through) then three situations can arise:
     * The current node to be deleted is the head, the current node to be deleted is the tail, and the current node to be deleted is some interior node
     * 
     * If the current node is the head, then the node after the current node must become the new head since the current head is being deleted
     * The node after current's previous link is set to null to unlink the current head node, the node after current is set to be the new head and the number of elements is decreased
     * by one
     * 
     * If the current node is the tail, then the node before it must become the new tail since it is being deleted
     * The node before the current node's next is set to null, to unlink the current tail, the tail is set to the node before the current node, the number
     * of elements is decreased by one
     * 
     * Finally, if the node is an interior node, its surrounding nodes' links are changed accordinly to unlink this current node in between them (basically)
     * the surrounding two nodes are linked to each other instead of the current node and finaly the number of elements is decreased 
     * 
     * The difference for this function is that the loop is not broken so it continues deleting every element that matches the data we passed through until
     * it gets to the end of the list
     */

    public void deleteAll(T data) {

        if (numElements == 0) {
            System.out.println("Cannot remove something from an empty list!");
            return;
        }

        Node<T> current = head;

        while (current != null) {
            if (current.data.equals(data)) {
                if (current.prev == null) {
                    current.next.prev = null;
                    head = current.next;
                    numElements --;
                }
                else if (current.next == null) {
                    current.prev.next = null;
                    tail = current.prev;
                    numElements --;
                }
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    numElements --;
                }
            }
            current = current.next;
        }
    }

    /*
     * This method takes in a specific index of the linked list
     * Although a linked list does not support indexing, you can say "find me the 5th element of this list and return it if it exists", this is pretty much
     * what this method does
     * It takes in an integer as the 'index' and iterates through the list until that index (iterates through the list the many times)
     * 
     * If the loop finishes and current is equal to null, there is no element at that index (there is no 5th element), and so null is returned, otherwise,
     * if the current node is not null, its data is returned
     */

    public T getDataAt(int index) {
        Node<T> current = head;

        for (int i = 0; i < index; i ++) {
            current = current.next;
        }

        if (current == null)
            return null;

        return current.data;
    }

    /*
     * This method takes in data to be found
     * it sets a variable current to be the head of the linked list and iterates through the list until current is null
     * if the current node's data matches the data passed through the method that we are looking for, this current node's data is returned
     * 
     * Otherwise if the loop finishes and the data is not found, it returns null
     */

    @Override
    public T search(T data) {
        Node<T> current = head;

        while (current != null) {
            if (current.data.equals(data)) {
                return current.data;
            }
            current = current.next;
        }

        return null;
    }

    /*
     * the isEmpty() method returns a boolean value representing whether or not the number of elements is equal to zero (you could achieve the same effect 
     * by return a boolean represeenting whether the head is null or not)
     */

    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    /*
     * The toString() method returns [] size: 0 if the list is empty
     * Otherwise it sets a variable of type Node<T> called current to be the head, and creates an empty string that will later be added to
     * It then iterates through the list while current it not null and adds each elements data folowed by an arrow and spaces to the string
     * 
     * Finally it adds brackets in front, removes the last arrow and spaces from the string of list data, adds a closed bracket to the end, and adds the size
     * which is represented by numElements
     */

    @Override
    public String toString() {
        if (isEmpty())
            return "[]\nSize: 0";
        
        Node<T> current = head;
        String string = "";

        while (current != null) {
            string = string + current.data + " -> ";
            current = current.next;
        }

        return "[" + string.substring(0, string.length() - 4) + "]\nSize: " + numElements;
        
    }

    /*
     * This method starts at the tail of the list and adds elements in reverse order with the arrow pointing the other way
     */

    public String toStringReverse() {
        if (isEmpty())
            return "[]\nSize: 0";
        
        Node<T> current = tail;
        String string = "";

        while (current != null) {
            string = string + current.data + " <- ";
            current = current.prev;
        }

        return "[" + string.substring(0, string.length() - 4) + "]\nSize: " + numElements;
    }

    /*
     * the print() method creates a string using the toString() method and then just prints out that string
     */

    @Override
    public void print() {
        String string = toString();
        System.out.println(string);
    }

    /*
     * This method uses the toStringReverse() method to create a reverse string of the linked list and prints out this string
     */

    public void printReverse() {
        String string = toStringReverse();
        System.out.println(string);
    }
}