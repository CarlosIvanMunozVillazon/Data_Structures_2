package Main;

import BST.BinaryTree;
import DoublyLinkedList.DoublyLinkedList;
import LinkedLIstWithTail.LinkedListWithTail;
import QueueWithArray.QueueArray;
import StackWithArray.StackArray;

public class Main {

    public static void main(String[] args) {


        //BINARY TREE IMPLEMENTATION TESTING:

        BinaryTree newTree = new BinaryTree();

        newTree.insertData(5);


        newTree.insertData(1);
        newTree.insertData(3);
        newTree.insertData(7);
        newTree.insertData(58);
        newTree.insertData(6);
        newTree.insertData(-1);
        newTree.insertData(2);
        newTree.insertData(11);
        newTree.insertData(45);

        System.out.println(newTree.getSize());

        newTree.printInOrder();
        /*newTree.printPostOrder();
        newTree.printPreOrder();*/

        System.out.println("Minimum element in the tree: " + newTree.findMinorValue());
        System.out.println("Maximum element in the tree: " + newTree.findMaxValue());

        System.out.println("Tree after deletions: ");

        newTree.removeData(6);
        newTree.printInOrder();

        newTree.removeData(-1);
        newTree.printInOrder();

        newTree.removeData(58);
        newTree.printInOrder();

        //QUEUE WITH ARRAY IMPLEMENTATION TESTING:

/*
        QueueArray <String> queueOfStrings = new QueueArray<>(5);

        queueOfStrings.enqueue("Roger");
        queueOfStrings.enqueue("Arthur");
        queueOfStrings.enqueue("Magnus");
        queueOfStrings.enqueue("Irving");
        queueOfStrings.enqueue("Andy");

        queueOfStrings.printQueueArray();
        System.out.println(queueOfStrings.getRear());
        System.out.println(queueOfStrings.getFront());

        System.out.println("//////////////");
        queueOfStrings.dequeue();
        System.out.println(queueOfStrings.getRear());
        System.out.println(queueOfStrings.getFront());


        queueOfStrings.printQueueArray();

        queueOfStrings.enqueue("Armin");

        queueOfStrings.printQueueArray();

        queueOfStrings.dequeue();
        queueOfStrings.dequeue();

        queueOfStrings.printQueueArray();
        System.out.println(queueOfStrings.getFront());
        System.out.println(queueOfStrings.getRear());
*/


        //SINGLY GENERIC LINKED LIST WITH TAIL TESTING

/*        LinkedListWithTail <Integer> myList = new LinkedListWithTail<>();

        myList.pushBack(1);
        myList.pushBack(2);
        myList.pushBack(3);
        myList.pushBack(4);

        myList.printLinkedListWithTail();

        System.out.println(myList.topFront());

        myList.popFront();

        myList.printLinkedListWithTail();*/

        //STACK ARRAY PRINT METHOD TESTING:

/*
        StackArray <Integer> myStack = new StackArray<>(4);
*/

/*        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);

        myStack.printStackRegularOrder();
        myStack.printStackReverseOrder();*/


        //DOUBLY LINKED LIST TESTING
        /*
        DoublyLinkedList myList = new DoublyLinkedList();

        myList.pushBack(1.2,3.6);
        myList.pushBack(3.4,4.5);
        myList.pushBack(9.6,12.3);
        myList.pushBack(23.5,15.2);

        myList.printDoublyLinkedList();

        double [] returnArray = myList.topFront();
        System.out.print(returnArray[0] + " " + returnArray[1]);

        myList.popFront();

        System.out.println();
        System.out.println();

        myList.printDoublyLinkedList();

        //Test for the insert method

        DoublyLinkedList myList2 = new DoublyLinkedList();

        myList2.pushBack(3,23);
        myList2.pushBack(9,23);

        myList2.printDoublyLinkedList();

        myList2.insert(1,32);
        myList2.printDoublyLinkedList();

        myList2.insert(7,45);
        myList2.printDoublyLinkedList();

        myList2.insert(11,334);
        myList2.printDoublyLinkedList();

        DoublyLinkedList myList3 = new DoublyLinkedList();

        myList3.insert(23,11);
        myList3.printDoublyLinkedList();
*/

    }
}
