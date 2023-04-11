package Main;

import StackWithArray.StackArray;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter stack size:");
        int stackSize = scanner.nextInt();
        StackArray stacky = new StackArray(stackSize);

        System.out.println("Please enter some numbers, enter negative number for stopping the prompt:");
        int key = scanner.nextInt();
        while(key > 0){
            stacky.push(key);
            key = scanner.nextInt();
        }

        while(!stacky.empty()){
            System.out.print(stacky.pop() + " ");
        }

        scanner.close();
    }
}
