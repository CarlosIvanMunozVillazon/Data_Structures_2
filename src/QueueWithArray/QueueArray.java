package QueueWithArray;

public class QueueArray <T> {

    private int size;
    private int front;
    private int rear;
    private int count;
    private T [] qarray;

    public QueueArray () {
        front = 0;
        rear = 0;
        count = 0;
        size = 4;
        qarray = (T[]) new Object[size];
    }

    public QueueArray (int size) {
        front = 0;
        rear = 0;
        count = 0;
        this.size = size;
        qarray = (T[]) new Object[size];
    }

    public boolean empty () {
        return count == 0;
    }

    public boolean full () {
        return count == size;
    }

    public void enqueue (T data) {
        if (full()){
            throw new RuntimeException("The queue is full");
        }
        qarray[rear] = data;
        rear = (rear + 1) % size;
        count++;
    }

    public T dequeue (){
        if (empty()){
            throw new RuntimeException("The queue is empty");
        }
        T item = qarray[front];
        qarray[front] = null;
        front = (front + 1) % size;
        count--;

        return item;
    }

    public void printQueueArray () {
        for(int i = 0; i < qarray.length; i++){
            if (qarray[i] != null) {
                System.out.print(qarray[i] + " ");
            }
        }

        System.out.println();
    }

    public int getSize() {
        return size;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public int getCount() {
        return count;
    }
}
