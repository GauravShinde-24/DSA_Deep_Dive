// You have a singly linked list that DOES NOT HAVE A TAIL POINTER  (which will make this method simpler to implement).
// Given a value x you need to rearrange the linked list such that all nodes with a value less than x come before all nodes with a value greater than or equal to x.
// Additionally, the relative order of nodes in both partitions should remain unchanged.


// Constraints:
// The solution should traverse the linked list at most once.
// The solution should not create a new linked list.


// Input:
// A singly linked list and an integer x.


// Output:
// The same linked list but rearranged as per the above criteria.


// Function signature:
// public void partitionList(int x);


// Details:

// The function partitionList takes an integer x as a parameter and modifies the current linked list in place according to the specified criteria. 
// If the linked list is empty (i.e., head is null), the function should return immediately without making any changes.


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
        if (head == null) {
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

    public void partitionList(int x){
        
        if(head == null){
            return;
        }
        
        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);
        
        Node prev1 = dummy1;
        Node prev2 = dummy2;
        
        Node current = head;
        
       while(current != null){
           
           if(current.value < x){
               prev1.next = current;
               prev1 = current;
           }else{
               prev2.next = current;
               prev2 = current;
           }
           
           current = current.next;
       }
        
        prev2.next = null;
        prev1.next = dummy2.next;
        head = dummy1.next;
        dummy1 = null;
        dummy2 = null;
        
    }

}

public class Main {

    public static void main(String[] args) {
        LinkedList myLinkedList;

        // Test 1: Empty list
        System.out.println("Test 1: Empty List");
        myLinkedList = new LinkedList(1);
        myLinkedList.makeEmpty();
        myLinkedList.partitionList(3);
        System.out.println("List: (empty)");
        System.out.println("Expected: empty");
        myLinkedList.printList();
        System.out.println();

        // Test 2: Single node (< x)
        System.out.println("Test 2: Single Node (< x)");
        myLinkedList = new LinkedList(1);
        myLinkedList.partitionList(3);
        System.out.println("List: 1");
        System.out.println("Expected: 1");
        myLinkedList.printList();
        System.out.println();

        // Test 3: Single node (>= x)
        System.out.println("Test 3: Single Node (>= x)");
        myLinkedList = new LinkedList(5);
        myLinkedList.partitionList(3);
        System.out.println("List: 5");
        System.out.println("Expected: 5");
        myLinkedList.printList();
        System.out.println();

        // Test 4: All nodes < x
        System.out.println("Test 4: All Nodes < x");
        myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(0);
        myLinkedList.partitionList(5);
        System.out.println("List: 1 -> 2 -> 0");
        System.out.println("Expected: 1 -> 2 -> 0");
        myLinkedList.printList();
        System.out.println();

        // Test 5: All nodes >= x
        System.out.println("Test 5: All Nodes >= x");
        myLinkedList = new LinkedList(5);
        myLinkedList.append(6);
        myLinkedList.append(7);
        myLinkedList.partitionList(5);
        System.out.println("List: 5 -> 6 -> 7");
        System.out.println("Expected: 5 -> 6 -> 7");
        myLinkedList.printList();
        System.out.println();

        // Test 6: Mixed nodes (< x and >= x)
        System.out.println("Test 6: Mixed Nodes");
        myLinkedList = new LinkedList(3);
        myLinkedList.append(5);
        myLinkedList.append(8);
        myLinkedList.append(5);
        myLinkedList.append(10);
        myLinkedList.append(2);
        myLinkedList.append(1);
        myLinkedList.partitionList(5);
        System.out.println("Original List: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1");
        System.out.println("Expected: 3 -> 2 -> 1 -> 5 -> 8 -> 5 -> 10");
        myLinkedList.printList();
        System.out.println();

        // Test 7: Nodes with duplicates around pivot
        System.out.println("Test 7: Duplicates Around Pivot");
        myLinkedList = new LinkedList(5);
        myLinkedList.append(1);
        myLinkedList.append(5);
        myLinkedList.append(0);
        myLinkedList.append(5);
        myLinkedList.partitionList(5);
        System.out.println("Original List: 5 -> 1 -> 5 -> 0 -> 5");
        System.out.println("Expected: 1 -> 0 -> 5 -> 5 -> 5");
        myLinkedList.printList();
        System.out.println();

        /*
            EXPECTED OUTPUT:
            ----------------
            Test 1: Empty List
            List: (empty)
            Expected: empty
            empty

            Test 2: Single Node (< x)
            List: 1
            Expected: 1
            1

            Test 3: Single Node (>= x)
            List: 5
            Expected: 5
            5

            Test 4: All Nodes < x
            List: 1 -> 2 -> 0
            Expected: 1 -> 2 -> 0
            1 -> 2 -> 0

            Test 5: All Nodes >= x
            List: 5 -> 6 -> 7
            Expected: 5 -> 6 -> 7
            5 -> 6 -> 7

            Test 6: Mixed Nodes
            Original List: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1
            Expected: 3 -> 2 -> 1 -> 5 -> 8 -> 5 -> 10
            3 -> 2 -> 1 -> 5 -> 8 -> 5 -> 10

            Test 7: Duplicates Around Pivot
            Original List: 5 -> 1 -> 5 -> 0 -> 5
            Expected: 1 -> 0 -> 5 -> 5 -> 5
            1 -> 0 -> 5 -> 5 -> 5
        */
        
    }
    
}
