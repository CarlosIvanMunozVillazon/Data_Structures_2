package HomeApplianceDelivery;

import LinkedLIstWithTail.LinkedListWithTail;
import StackWithArray.StackArray;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String numberOfChanges1 = scanner.nextLine();
        int numberOfChanges2 = Integer.parseInt(numberOfChanges1); //C

        for (int i = 0; i < numberOfChanges2; i++) {
            String numberOfAppliances1 = scanner.nextLine(); //N
            int numberOfAppliances2 = Integer.parseInt(numberOfAppliances1);

            String appliancesList1 = scanner.nextLine(); //N's
            String [] appliancesList2 = appliancesList1.split(" ");

            String numberOfStores1 = scanner.nextLine(); //T
            int numberOfStores2 = Integer.parseInt(numberOfStores1);

            String appliancesPerStore1 = scanner.nextLine(); //T's
            String [] appliancesPerStore2 = appliancesPerStore1.split(" ");
            //Puedo coger "appliancesPerStore2" e ir convirtiendo sus elementos a int según sea el caso.

            LinkedListWithTail<String> appliancesQueue = new LinkedListWithTail<>();

            for (String appliance: appliancesList2){
                appliancesQueue.pushBack(appliance);
            }

            for (int r = 0; r < numberOfStores2; r++) {
                for (int j = 0; j < appliancesPerStore2.length; j++) {
                    StackArray<String> nStoreStack = new StackArray<>(Integer.parseInt(appliancesPerStore2[j]));
                    for (int k = 0; k < j; k++){
                        nStoreStack.push(appliancesQueue.topFront());
                        appliancesQueue.popFront();
                    }

                    nStoreStack.printStackRegularOrder();
                }
            }
        }

    }
}
