package HomeApplianceDelivery;

import LinkedListWithTail.LinkedListWithTail;
import StackWithArray.StackArray;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String numberOfChanges1 = scanner.nextLine();
        int numberOfChanges2 = Integer.parseInt(numberOfChanges1);


        String[] input = new String[numberOfChanges2 * 4];

        for (int i = 0; i < (numberOfChanges2 * 4); i += 4) {

            String numberOfAppliances1 = scanner.nextLine();
            input[i] = numberOfAppliances1;

            String appliances = scanner.nextLine();
            input[i + 1] = appliances;

            String numberOfStores = scanner.nextLine();
            input[i + 2] = numberOfStores;

            String productsPerStore = scanner.nextLine();
            input[i + 3] = productsPerStore;

        }

        int totalStores = 0;

        for (int p = 2; p < input.length; p+= 4){
            totalStores += Integer.parseInt(input[p]);
        }

        StackArray [] globalStacks = new StackArray[totalStores];

        int storeIndex = 0;
        for (int i = 0; i < input.length; i += 4) {

            LinkedListWithTail<String> AppliancesQueue = new LinkedListWithTail<>();
            String[] appliancesString = input[i + 1].split(" ");
            for (int j = 0; j < appliancesString.length; j++) {
                AppliancesQueue.pushBack(appliancesString[j]);
            }

            int stores = Integer.parseInt(input[i + 2]);

            StackArray[] stackArrays = new StackArray[stores];
            String[] productsPerStore = input[i + 3].split(" ");

            for (int k = 0; k < stores; k++) {

                for (int h = 0; h < stores; h++) {
                    StackArray<String> storeStack = new StackArray<>(Integer.parseInt(productsPerStore[h]));
                    stackArrays[h] = storeStack;
                }

                for (int l = 0; l < Integer.parseInt(productsPerStore[k]); l++) {

                    if (!AppliancesQueue.isEmpty()) {

                        String pushedProduct = AppliancesQueue.topFront();
                        stackArrays[k].push(pushedProduct);
                        AppliancesQueue.popFront();
                    }

                }

                globalStacks[storeIndex] = stackArrays[k];
                storeIndex +=1;
            }
            scanner.close();
        }
        //Innecesario
        for (int u = 0; u < globalStacks.length; u++){
            if (globalStacks[u].empty() && u == globalStacks.length - 1){
                globalStacks[u].printStackRegularOrder();
            } else if (globalStacks[u].empty() && u != globalStacks.length - 1){
                globalStacks[u].printStackRegularOrder();
                System.out.println();
            } else if (!globalStacks[u].empty() && u == globalStacks.length - 1){
                globalStacks[u].printStackRegularOrder();
            } else {
                globalStacks[u].printStackRegularOrder();
                System.out.println();
            }
        }

    }
}
