package Main;

import DoublyLinkedList.DoublyLinkedList;

public class Main {

    public static void main(String[] args) {

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

    }
}
