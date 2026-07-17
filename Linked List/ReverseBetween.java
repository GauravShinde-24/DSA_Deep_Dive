// In the LinkedList class, implement a method called reverseBetween that reverses the nodes of the list between indexes startIndex and endIndex (inclusive).
// It's important to note that you should only rearrange the nodes themselves, not just their values.

// Note:  The Linked List does NOT have a tail which will make the implementation easier.

// Assumption: You can assume that startIndex and endIndex are not out of bounds.

// The method should not return any value, and it should modify the original linked list.
// The positions startIndex and endIndex are 0-indexed.

// Example:

// Given the following linked list:
// 1 -> 2 -> 3 -> 4 -> 5

// Calling reverseBetween(1, 3) should result in the following modified linked list:
// 1 -> 4 -> 3 -> 2 -> 5

// I highly recommend that you draw the Linked List out on a piece of paper so you can visualize the steps.

// Notes:
// The method should not duplicate any of the existing nodes, only rearranging the existing nodes in the list.
// However, the creation of a limited number of new nodes is allowed (e.g., dummy nodes to facilitate the partitioning process).
// The method should not use any extra data structures such as arrays or lists.

public class LinkedList {

    private Node head;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        length = 1;
    }

    public Node getHead() {
        return head;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        if (temp == null) {
            System.out.println("empty");
        } else {
            while (temp != null) {
                System.out.print(temp.value);
                temp = temp.next;
                if (temp != null) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
    
    public void makeEmpty() {
        head = null;
        length = 0;
    }
    
    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        length++;
    }
    
   public void reverseBetween(int m, int n){
        if(head == null){
          return;  
        } 

        Node dummyNode = new Node(0);
        dummyNode.next = head;

        Node previousNode = dummyNode;
        for(int i = 0; i < m; i++){
            previousNode = previousNode.next;
        }

        Node currentNode = previousNode.next;
        for(int i=0; i< n-m; i++) {
            Node nodeToMove = currentNode.next;
            currentNode.next = nodeToMove.next;
            nodeToMove.next = previousNode.next;
            previousNode.next = nodeToMove;
        }
        head = dummyNode.next;
    }
}

public class Main {

    public static void main(String[] args) {
        LinkedList myLinkedList;

        // Test 1: Empty list
        System.out.println("Test 1: Empty List");
        myLinkedList = new LinkedList(1);
        myLinkedList.makeEmpty();
        myLinkedList.reverseBetween(0, 0);
        System.out.println("List: (empty)");
        System.out.println("Expected: empty");
        myLinkedList.printList();
        System.out.println();

        // Test 2: Single node list
        System.out.println("Test 2: Single Node");
        myLinkedList = new LinkedList(10);
        myLinkedList.reverseBetween(0, 0);
        System.out.println("List: 10");
        System.out.println("Expected: 10");
        myLinkedList.printList();
        System.out.println();

        // Test 3: Reverse sublist in middle (1 -> 2 -> 3 -> 4 -> 5; reverse 1..3)
        System.out.println("Test 3: Reverse Sublist in Middle");
        myLinkedList = new LinkedList(1);
        for (int i = 2; i <= 5; i++) myLinkedList.append(i);
        myLinkedList.reverseBetween(1, 3);  // Reverse nodes 2,3,4
        System.out.println("Original: 1 -> 2 -> 3 -> 4 -> 5");
        System.out.println("Expected: 1 -> 4 -> 3 -> 2 -> 5");
        myLinkedList.printList();
        System.out.println();

        // Test 4: Reverse from head (reverse 0..2)
        System.out.println("Test 4: Reverse From Head");
        myLinkedList = new LinkedList(1);
        for (int i = 2; i <= 5; i++) myLinkedList.append(i);
        myLinkedList.reverseBetween(0, 2);  // Reverse nodes 1,2,3
        System.out.println("Original: 1 -> 2 -> 3 -> 4 -> 5");
        System.out.println("Expected: 3 -> 2 -> 1 -> 4 -> 5");
        myLinkedList.printList();
        System.out.println();

        // Test 5: Reverse to tail (reverse 2..4)
        System.out.println("Test 5: Reverse To Tail");
        myLinkedList = new LinkedList(1);
        for (int i = 2; i <= 5; i++) myLinkedList.append(i);
        myLinkedList.reverseBetween(2, 4);  // Reverse nodes 3,4,5
        System.out.println("Original: 1 -> 2 -> 3 -> 4 -> 5");
        System.out.println("Expected: 1 -> 2 -> 5 -> 4 -> 3");
        myLinkedList.printList();
        System.out.println();

        // Test 6: Reverse entire list (0..4)
        System.out.println("Test 6: Reverse Entire List");
        myLinkedList = new LinkedList(1);
        for (int i = 2; i <= 5; i++) myLinkedList.append(i);
        myLinkedList.reverseBetween(0, 4);
        System.out.println("Original: 1 -> 2 -> 3 -> 4 -> 5");
        System.out.println("Expected: 5 -> 4 -> 3 -> 2 -> 1");
        myLinkedList.printList();
        System.out.println();

        // Test 7: startIndex == endIndex (no change)
        System.out.println("Test 7: No Change (start == end)");
        myLinkedList = new LinkedList(1);
        for (int i = 2; i <= 5; i++) myLinkedList.append(i);
        myLinkedList.reverseBetween(2, 2);
        System.out.println("Original: 1 -> 2 -> 3 -> 4 -> 5");
        System.out.println("Expected: 1 -> 2 -> 3 -> 4 -> 5");
        myLinkedList.printList();
        System.out.println();

        /*
            EXPECTED OUTPUT:
            ----------------
            Test 1: Empty List
            List: (empty)
            Expected: empty
            empty

            Test 2: Single Node
            List: 10
            Expected: 10
            10

            Test 3: Reverse Sublist in Middle
            Original: 1 -> 2 -> 3 -> 4 -> 5
            Expected: 1 -> 4 -> 3 -> 2 -> 5
            1 -> 4 -> 3 -> 2 -> 5

            Test 4: Reverse From Head
            Original: 1 -> 2 -> 3 -> 4 -> 5
            Expected: 3 -> 2 -> 1 -> 4 -> 5
            3 -> 2 -> 1 -> 4 -> 5

            Test 5: Reverse To Tail
            Original: 1 -> 2 -> 3 -> 4 -> 5
            Expected: 1 -> 2 -> 5 -> 4 -> 3
            1 -> 2 -> 5 -> 4 -> 3

            Test 6: Reverse Entire List
            Original: 1 -> 2 -> 3 -> 4 -> 5
            Expected: 5 -> 4 -> 3 -> 2 -> 1
            5 -> 4 -> 3 -> 2 -> 1

            Test 7: No Change (start == end)
            Original: 1 -> 2 -> 3 -> 4 -> 5
            Expected: 1 -> 2 -> 3 -> 4 -> 5
            1 -> 2 -> 3 -> 4 -> 5
        */
        
    }
    
}

