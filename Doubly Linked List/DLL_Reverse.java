// Implement a method called reverse() that reverses the order of the nodes in the list.
// This method should reverse the order of the nodes in the list by manipulating the pointers of each node, not by swapping the values within the nodes.

// Method Signature:
// public void reverse()

// Output:
// No explicit output is returned. However, the method should modify the doubly linked list such that the order of the nodes is reversed.

// Constraints:
// The doubly linked list may be empty or have one or more nodes.

// Example:
// Consider the following doubly linked list:
// Head: 1
// Tail: 5
// Length: 5
// Doubly Linked List:
// 1 <-> 2 <-> 3 <-> 4 <-> 5
// After calling reverse(), the list should be:
// Head: 5
// Tail: 1
// Length: 5
// Doubly Linked List:
// 5 <-> 4 <-> 3 <-> 2 <-> 1

public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }
    }

    public DoublyLinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nDoubly Linked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }
    
    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }
    
    public void append (int value) {
        Node newNode = new Node(value);
        if(length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        length++;
    }
    
    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        if (index < length/2) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = length - 1; i > index; i--) {
                temp = temp.prev;
            }
        }
        return temp;
    }

	public void reverse(){
	    Node temp = null;
	    Node current = head;
	    
	    while(current != null){
	        
	        current.prev = current.next;
	        current.next = temp;
	        temp = current;
	        current = current.prev;
	        
	    }
	    
	    tail = head;
	    head = temp;
	    
	}
}

public class Main {

    // Helper to print forward traversal
    private static void printForward(DoublyLinkedList dll) {
        DoublyLinkedList.Node current = dll.getHead();
        if (current == null) {
            System.out.println("Forward: empty");
            return;
        }
        System.out.print("Forward: ");
        while (current != null) {
            System.out.print(current.value);
            if (current.next != null) System.out.print(" <-> ");
            current = current.next;
        }
        System.out.println();
    }

    // Helper to print backward traversal
    private static void printBackward(DoublyLinkedList dll) {
        DoublyLinkedList.Node current = dll.getTail();
        if (current == null) {
            System.out.println("Backward: empty");
            return;
        }
        System.out.print("Backward: ");
        while (current != null) {
            System.out.print(current.value);
            if (current.prev != null) System.out.print(" <-> ");
            current = current.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedList dll;
        
        // Explain why we do forward and backward prints
        System.out.println("We print each list forward and backward");
        System.out.println("to confirm BOTH next and prev pointers");
        System.out.println("are correct after reverseBetween.");
        System.out.println();

        // Test 1: Empty list
        System.out.println("Test 1: Empty List");
        dll = new DoublyLinkedList(1);
        dll.makeEmpty();
        System.out.println("Expected Forward: empty");
        System.out.println("Expected Backward: empty");
        printForward(dll);
        printBackward(dll);
        System.out.println();

        // Test 2: Single-node list
        System.out.println("Test 2: Single Node");
        dll = new DoublyLinkedList(10);
        System.out.println("Expected Forward: 10");
        System.out.println("Expected Backward: 10");
        printForward(dll);
        printBackward(dll);
        System.out.println();

        // Test 3: Multiple nodes after append
        System.out.println("Test 3: Multiple Nodes (After Append)");
        dll = new DoublyLinkedList(1);
        dll.append(2);
        dll.append(3);
        dll.append(4);
        dll.append(5);
        System.out.println("Expected Forward: 1 <-> 2 <-> 3 <-> 4 <-> 5");
        System.out.println("Expected Backward: 5 <-> 4 <-> 3 <-> 2 <-> 1");
        printForward(dll);
        printBackward(dll);
        System.out.println();

        // Test 4: Reverse an even-length list
        System.out.println("Test 4: Reverse Even-Length List");
        dll = new DoublyLinkedList(1);
        dll.append(2);
        dll.append(3);
        dll.append(4);
        dll.reverse();
        System.out.println("Expected Forward: 4 <-> 3 <-> 2 <-> 1");
        System.out.println("Expected Backward: 1 <-> 2 <-> 3 <-> 4");
        printForward(dll);
        printBackward(dll);
        System.out.println();

        // Test 5: Reverse an odd-length list
        System.out.println("Test 5: Reverse Odd-Length List");
        dll = new DoublyLinkedList(1);
        dll.append(2);
        dll.append(3);
        dll.append(4);
        dll.append(5);
        dll.reverse();
        System.out.println("Expected Forward: 5 <-> 4 <-> 3 <-> 2 <-> 1");
        System.out.println("Expected Backward: 1 <-> 2 <-> 3 <-> 4 <-> 5");
        printForward(dll);
        printBackward(dll);
        System.out.println();

        /*
            EXPECTED OUTPUT:
            ----------------
            Test 1: Empty List
            Expected Forward: empty
            Expected Backward: empty
            Forward: empty
            Backward: empty

            Test 2: Single Node
            Expected Forward: 10
            Expected Backward: 10
            Forward: 10
            Backward: 10

            Test 3: Multiple Nodes (After Append)
            Expected Forward: 1 <-> 2 <-> 3 <-> 4 <-> 5
            Expected Backward: 5 <-> 4 <-> 3 <-> 2 <-> 1
            Forward: 1 <-> 2 <-> 3 <-> 4 <-> 5
            Backward: 5 <-> 4 <-> 3 <-> 2 <-> 1

            Test 4: Reverse Even-Length List
            Expected Forward: 4 <-> 3 <-> 2 <-> 1
            Expected Backward: 1 <-> 2 <-> 3 <-> 4
            Forward: 4 <-> 3 <-> 2 <-> 1
            Backward: 1 <-> 2 <-> 3 <-> 4

            Test 5: Reverse Odd-Length List
            Expected Forward: 5 <-> 4 <-> 3 <-> 2 <-> 1
            Expected Backward: 1 <-> 2 <-> 3 <-> 4 <-> 5
            Forward: 5 <-> 4 <-> 3 <-> 2 <-> 1
            Backward: 1 <-> 2 <-> 3 <-> 4 <-> 5
        */
        
    }
    
}


