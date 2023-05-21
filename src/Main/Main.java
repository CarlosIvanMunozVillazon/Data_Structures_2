package Main;

import AVL.AVLTree;
import BST.BinarySearchTree;

public class Main {

    public static void main(String[] args) {

        //AVL TREE IMPLEMENTATION TESTING:

        AVLTree newAVL = new AVLTree();

        /*newAVL.insertData(10);
        newAVL.insertData(20);
        newAVL.insertData(15);
        newAVL.insertData(14);

        newAVL.insertData(25);
        newAVL.insertData(24);
        newAVL.insertData(23);

        newAVL.printInOrder();
        newAVL.printPreOrder();
        newAVL.printPostOrder();

        newAVL.printLevelOrder();

        newAVL.deleteData(25);
        newAVL.printInOrder();*/

        /*newAVL.insertData(50);
        newAVL.insertData(60);
        newAVL.insertData(55);
        newAVL.insertData(54);
        newAVL.insertData(57);
        newAVL.insertData(56);
        newAVL.insertData(58);
        newAVL.insertData(70);
        newAVL.insertData(53);

        newAVL.printInOrder();

        newAVL.deleteData(55);

        newAVL.printInOrder();*/

        //rotations testing

        newAVL.insertData(10);
        newAVL.insertData(5);
        newAVL.insertData(4);
        newAVL.insertData(3);
        newAVL.insertData(2);
        newAVL.insertData(1);

        /*newAVL.insertData(3);
        newAVL.insertData(4);
        newAVL.insertData(2);
        newAVL.insertData(7);
        newAVL.insertData(6);
        newAVL.insertData(8);
        newAVL.insertData(12);
        newAVL.insertData(11);*/

//        newAVL.insertData(5);
//
//        newAVL.insertData(1);
//        newAVL.insertData(3);

        newAVL.printPostOrder();

        newAVL.rightRotation(2);

        newAVL.printPostOrder();

        newAVL.rightRotation(4);

        newAVL.printPostOrder();

        newAVL.rightRotation(4);

        newAVL.printPostOrder();

        newAVL.rightRotation(2);

        newAVL.printPostOrder();

        newAVL.rightRotation(1);

        newAVL.printPostOrder();

        //BINARY TREE IMPLEMENTATION TESTING:

/*
        BinarySearchTree newTree = new BinarySearchTree();

        newTree.insertData(5); //Our root is 5.

        newTree.insertData(1);
        newTree.insertData(3);
        newTree.insertData(7);
        newTree.insertData(58);
        newTree.insertData(6);
        newTree.insertData(-1);
        newTree.insertData(2);
        newTree.insertData(11);
        newTree.insertData(45);
        newTree.insertData(46);

        System.out.println("Level order print: ");
        newTree.printLevelOrder();

        System.out.println("Post order print: ");
        newTree.printPostOrder();

        System.out.println("The height of the new tree is: ");
        System.out.println(newTree.getTreeHeight());
*/

/*
        System.out.println(newTree.getSize());

        newTree.printInOrder();
*/

        /*newTree.printPostOrder();
        newTree.printPreOrder();*/

//        System.out.println("Minimum element in the tree: " + newTree.findMinorValue());
//        System.out.println("Maximum element in the tree: " + newTree.findMaxValue());

//        System.out.println("Tree after deletions: ");

/*
        newTree.removeData(6);
        newTree.printInOrder();

        newTree.removeData(-1);
        newTree.printInOrder();

        newTree.removeData(58);
        newTree.printInOrder();
*/

//        System.out.println("Tree searches for deleted and non deleted numbers: ");

        /*System.out.println(newTree.find(6));
        System.out.println(newTree.find(45));
        System.out.println();
        System.out.println(newTree.find(1));
        System.out.println(newTree.find(2));
        System.out.println(newTree.find(3));
        System.out.println(newTree.find(4));
        System.out.println(newTree.find(5));
        System.out.println(newTree.find(7));
        System.out.println(newTree.find(11));
        System.out.println(newTree.find(48));
        System.out.println(newTree.find(29));
    */
//        System.out.println("Level order print: ");


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
