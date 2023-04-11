package StackWithArray;

public class StackArray {

    private static final int defaultSize = 3;//this indeed can be a constant since is the default size
    private final int [] sarray;//non static constant cause it is part of the ADT
    private int top;

    //in both constructors top is pointing to the bottom of the stack
    public StackArray () {
        top = 0;
        sarray = new int[defaultSize];
    }

    public StackArray (int size){
        top = 0;
        sarray = new int[size];
    }

    //in both empty() and full() methods we could have top >= 0 or top <= 0, depending on what is better.
    public boolean empty () {
        return top == 0;
    }

    public boolean full () {
        return top == sarray.length;
    }

    public void push (int key){
        if (full()){
            throw new RuntimeException("The stack array is full.");
        }
        sarray[top] = key;
        top++;
    }

    public int pop (){
        if (empty()){
            throw new RuntimeException("The stack array is empty.");
        }
        top--;
        return sarray[top];
    }


}
