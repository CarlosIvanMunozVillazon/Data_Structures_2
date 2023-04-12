package DoublyLinkedList;

public class DoublyLinkedList {

    private static class Node { //private classes should be static
        private Node prev;
        private double data1;
        private double data2;
        private Node next;

        public Node(double data1, double data2) {
            this.prev = null;
            this.data1 = data1;
            this.data2 = data2;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;

    public boolean empty(){
        return this.head == null;
    }
    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
    }
    public void pushBack(double data1, double data2){
        Node newNode = new Node(data1, data2);
        if(empty()){
            this.head = newNode;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
        }
        this.tail = newNode;
    }

    public void popFront(){
        if(empty()){
            throw new RuntimeException("The doubly linked list is empty.");
        } else if(this.head == this.tail){
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
        }
    }

    public double [] topFront (){
        if(empty()){
            throw new RuntimeException("The doubly linked list is empty.");
        } else {
            double [] returnArray = new double[2];
            returnArray [0] = this.head.data1;
            returnArray [1] = this.head.data2;
            return returnArray;
        }
    }

    public void printDoublyLinkedList () {

        Node reference = this.head;

        while(reference != null){
            System.out.print("[" + reference.data1 + ", " + reference.data2 + "]");
            reference = reference.next;
        }

        System.out.println();

    }

    public void insert (double data1, double data2) { //inserts in order according to attribute1 of the node.

        Node reference = this.head;
        while (reference != null && reference.data1 < data1){ //for iterating over the list
            reference = reference.next;
        }

        if (reference == null){ //if the list is empty or the reference reached the end of the list
            pushBack(data1, data2);
        } else {
            Node newNode = new Node(data1, data2);
            newNode.next = reference;
            newNode.prev = reference.prev;
            if(reference.prev == null){ //for inserting at the beginning
                reference.prev = newNode;
                this.head = newNode;
            } else { //for inserting at the middle
                reference.prev.next = newNode;
                reference.prev = newNode;
            }
        }
    }

}
