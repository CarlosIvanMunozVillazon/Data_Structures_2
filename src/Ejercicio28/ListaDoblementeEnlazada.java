package Ejercicio28;

public class ListaDoblementeEnlazada {

    private static class Nodo { //private classes should be static
        private Nodo prev;
        private double timeNextEvent; //Tiempo del evento
        private int nextEventType; //Tipo del evento
        private int server; //Siguiente servidor
        private Nodo next;

        public Nodo(double timeNextEvent, int nextEventType, int server) {
            this.prev = null;
            this.timeNextEvent = timeNextEvent;
            this.nextEventType = nextEventType;
            this.server = server;
            this.next = null;
        }
    }

    private Nodo head;
    private Nodo tail;

    public boolean empty() {
        return this.head == null;
    }

    public ListaDoblementeEnlazada() {
        this.head = null;
        this.tail = null;
    }

    public void pushBack(double timeNextEvent, int nextEventType, int server) {
        Nodo newNodo = new Nodo(timeNextEvent, nextEventType, server);
        if (empty()) {
            this.head = newNodo;
        } else {
            this.tail.next = newNodo;
            newNodo.prev = this.tail;
        }
        this.tail = newNodo;
    }

    public void popFront() {
        if (empty()) {
            throw new RuntimeException("The doubly linked list is empty.");
        } else if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
        }
    }

    public double[] topFront() {
        if (empty()) {
            throw new RuntimeException("The doubly linked list is empty.");
        } else {
            double[] returnArray = new double[3];
            returnArray[0] = this.head.timeNextEvent;
            returnArray[1] = this.head.nextEventType;
            returnArray[2] = this.head.server;
            return returnArray;
        }
    }

    public void printDoublyLinkedList() {

        Nodo reference = this.head;

        while (reference != null) {
            System.out.print("[" + reference.timeNextEvent + ", " + reference.nextEventType + ", " + reference.server +"]");
            reference = reference.next;
        }

        System.out.println();

    }

    public void insert(double timeNextEvent, int nextEventType, int server) { //inserts in order according to attribute1 of the Nodo.

        Nodo reference = this.head;
        while (reference != null && reference.timeNextEvent < timeNextEvent) { //for iterating over the list
            reference = reference.next;
        }

        if (reference == null) { //if the list is empty or the reference reached the end of the list
            pushBack(timeNextEvent, nextEventType, server);
        } else {
            Nodo newNodo = new Nodo(timeNextEvent, nextEventType, server);
            newNodo.next = reference;
            newNodo.prev = reference.prev;
            if (reference.prev == null) { //for inserting at the beginning
                reference.prev = newNodo;
                this.head = newNodo;
            } else { //for inserting at the middle
                reference.prev.next = newNodo;
                reference.prev = newNodo;
            }
        }
    }
}
