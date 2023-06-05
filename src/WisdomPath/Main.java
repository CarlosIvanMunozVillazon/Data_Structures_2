package WisdomPath;

import BST.BinarySearchTree;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner escaner = new Scanner(System.in);

        String numbers = escaner.nextLine();
        String [] numbers2 = numbers.split(" ");

        int [] numbers3 = new int [numbers2.length];

        for (int i = 0; i< numbers3.length; i++){
            numbers3[i] = Integer.parseInt(numbers2[i]);
        }

        BinarySearchTree wisdomTree = new BinarySearchTree(numbers3);
        System.out.print(wisdomTree.wisdomPathDriver());
    }
}
