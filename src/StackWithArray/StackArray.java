package StackWithArray;

public class StackArray<T> {

    private static final int defaultSize = 3;//this indeed can be a constant since is the default size
    private final T[] sarray;//non static constant cause it is part of the ADT
    private int top;

    //in both constructors top is pointing to the bottom of the stack
    public StackArray() {
        top = 0;
        sarray = (T[]) new Object[defaultSize]; //here i get warning
    }

    public StackArray(int size) {
        top = 0;
        sarray = (T[]) new Object[size];//here i get another warning. Don't know how to fix it.
    }

    //in both empty() and full() methods we could have top >= 0 or top <= 0, depending on what is better.
    public boolean empty() {
        return top == 0;
    }

    public boolean full() {
        return top == sarray.length;
    }

    public void push(T key) {
        if (full()) {
            throw new RuntimeException("The stack array is full.");
        }
        sarray[top] = key;
        top++;
    }

    public T pop() {
        if (empty()) {
            throw new RuntimeException("The stack array is empty.");
        }
        top--;
        return sarray[top];
    }

    public void printStackRegularOrder() {

        if (empty()) {
            System.out.print("[]");
        } else {
            System.out.print("[" + sarray[0]);
            /*for (T key : sarray) {
                System.out.print(key + " ");
            }*/


            for (int i = 1; i < sarray.length; i++) {

                if (sarray[i] != null) {
                    System.out.print(" " + sarray[i]);
                }


            }
            /*if (sarray[sarray.length-1] != null){
                System.out.print(sarray[sarray.length-1]);
            }*/

            System.out.print("]");

        }
    }

    public void printStackReverseOrder() {

        for (int i = sarray.length - 1; i >= 0; i--) {
            System.out.print(sarray[i] + " ");
        }

        System.out.println();
    }
}
