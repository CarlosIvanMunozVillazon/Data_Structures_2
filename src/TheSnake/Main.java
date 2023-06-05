package TheSnake;

import BST.BinarySearchTree;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner escaner = new Scanner(System.in);

        String foodNumbers = escaner.nextLine();
        String neededFood = escaner.nextLine();

        String [] foodNumbers2 = foodNumbers.split(" ");
        int neededFood2 = Integer.parseInt(neededFood);

        int [] foodNumbers3 = new int [foodNumbers2.length];

        for (int i = 0; i<foodNumbers3.length; i++){
            foodNumbers3[i] = Integer.parseInt(foodNumbers2[i]);
        }

        BinarySearchTree snakePath = new BinarySearchTree(foodNumbers3);

        int eatedFood = snakePath.snakePathDriver();

        if (eatedFood >= neededFood2){
            System.out.print("Sobrevive");
        } else {
            System.out.print("Muere");
        }
        System.out.println();
        snakePath.printLevelOrder();
        escaner.close();
    }

}
