package GalapagosIsland;
import BST.BinarySearchTree;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Just for input reading
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        String[] insects = line.split(" ");
        String nodes = scanner.nextLine();

        int[] numberOfInsects = new int[insects.length];
        for (int i = 0; i < numberOfInsects.length; i++) {
            numberOfInsects[i] = Integer.parseInt(insects[i]);
        }

        int numberOfNodes = Integer.parseInt(nodes);

        BinarySearchTree levelOrderTree = new BinarySearchTree(numberOfInsects);

        int preOrderResult = preOrderSum(levelOrderTree,numberOfNodes);
        int postOrderResult = postOrderSum(levelOrderTree, numberOfNodes);
        int inOrderResult = inOrderSum(levelOrderTree, numberOfNodes);

        if (inOrderResult > preOrderResult && inOrderResult > postOrderResult){
            System.out.print("Inorder " + inOrderResult);
        } else if (preOrderResult > inOrderResult && preOrderResult > postOrderResult){
            System.out.print("Preorder " + preOrderResult);
        } else if (postOrderResult > inOrderResult && postOrderResult > preOrderResult){
            System.out.print("Postorder " + postOrderResult);
        } else if (postOrderResult == preOrderResult && inOrderResult < postOrderResult) {
            System.out.print("Preorder " + preOrderResult);
        } else if (postOrderResult == inOrderResult && inOrderResult > preOrderResult) {
            System.out.print("Inorder " + inOrderResult);
        } else {
            System.out.print("Preorder " + preOrderResult);
        }


        scanner.close();
    }

    public static int preOrderSum(BinarySearchTree levelOrderedTree, int numberOfNodes) {

        String preOrder = levelOrderedTree.preOrderStringDriver();
        String[] insects = preOrder.split(" ");

        int[] preOrderInsects = new int[insects.length];

        for (int i = 0; i < preOrderInsects.length; i++) {
            preOrderInsects[i] = Integer.parseInt(insects[i]);
        }

        int preOrderSum = 0;
        int counter = 0;

        for (int i = 0; i < preOrderInsects.length; i ++){
            int number = preOrderInsects[i];

            if (number != -1){
                preOrderSum += number;
                counter ++;
            }

            if (counter == numberOfNodes){
                break;
            }
        }
        return preOrderSum;
    }


    public static int postOrderSum(BinarySearchTree levelOrderedTree, int numberOfNodes) {

        String postOrder = levelOrderedTree.postOrderStringDriver();
        String[] insects = postOrder.split(" ");

        int[] postOrderInsects = new int[insects.length];

        for (int i = 0; i < postOrderInsects.length; i++) {
            postOrderInsects[i] = Integer.parseInt(insects[i]);
        }

        int postOrderSum = 0;
        int counter = 0;

        for (int i = 0; i < postOrderInsects.length; i ++){
            int number = postOrderInsects[i];

            if (number != -1){
                postOrderSum += number;
                counter++;
            }

            if (counter == numberOfNodes){
                break;
            }
        }
        return postOrderSum;
    }

    public static int inOrderSum(BinarySearchTree levelOrderedTree, int numberOfNodes) {

        String inOrder = levelOrderedTree.inOrderStringDriver();
        String[] insects = inOrder.split(" ");

        int[] inOrderInsects = new int[insects.length];

        for (int i = 0; i < inOrderInsects.length; i++) {
            inOrderInsects[i] = Integer.parseInt(insects[i]);
        }

        int inOrderSum = 0;
        int counter = 0;

        for (int i = 0; i < inOrderInsects.length; i ++){
            int number = inOrderInsects[i];

            if (number != -1){
                inOrderSum += number;
                counter++;
            }

            if (counter == numberOfNodes){
                break;
            }
        }
        return inOrderSum;
    }
}
