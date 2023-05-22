package GalapagosIsland;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Just for input reading
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        String [] insects = line.split(" ");
        String nodes = scanner.nextLine();

        int[] numberOfInsects = new int[insects.length];
        for (int i = 0; i < numberOfInsects.length; i++){
            numberOfInsects[i] = Integer.parseInt(insects[i]);
        }

        int numberOfNodes = Integer.parseInt(nodes);

        scanner.close();
    }






}
