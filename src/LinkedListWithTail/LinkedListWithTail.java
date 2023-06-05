package LinkedListWithTail;

public class LinkedListWithTail<T> {

    private static class Node<T> {

        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return data;
        }

    }

    private Node<T> head;
    private Node<T> tail;

    private int size;

    public LinkedListWithTail() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return (head == null && tail == null);
    }

    public void pushBack(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        this.size++;
    }

    public T topBack() {
        if (isEmpty()) {
            throw new RuntimeException("The linked list is empty.");
        } else {
            return tail.data;
        }
    }

    //Write popBack(); pending

    //Write pushFront(); pending

    public void pushFront(T data){
        Node<T> newNode = new Node<>(data);

        if(isEmpty()){
            this.head = newNode;
            this.tail = newNode;
        }else{
            newNode.next = this.head;
            this.head = newNode;
        }

        this.size++;
    }

    public T topFront() {
        if (isEmpty()) {
            throw new RuntimeException("The linked list is empty.");
        } else {
            return head.data;
        }
    }

    public void popFront() {

        if(isEmpty()){
            throw new RuntimeException("The linked list is empty.");
        } else if (head == tail){
            head = null;
            tail = null;
        } else {
            head = head.next;
        }

        this.size--;
    }

    public void printLinkedListWithTail() {

        Node<T> ref = head;

        while (ref != null) {
            System.out.print(ref.getData() + " ");
            ref = ref.next;
        }

        System.out.println();
    }

    public int getSize() {
        return size;
    }
}
