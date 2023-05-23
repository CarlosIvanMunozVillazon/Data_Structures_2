package Trip;

import AVL.AVLTree;

public class Main {

    public static void main(String[] args) {

        AVLTree municipios = new AVLTree();

        municipios.insertData(55);
        municipios.insertData(20);
        municipios.insertData(10);
        municipios.insertData(35);
        municipios.insertData(25);
        municipios.insertData(45);
        municipios.insertData(40);
        municipios.insertData(50);
        municipios.insertData(30);
        municipios.insertData(5);
        municipios.insertData(15);
        municipios.insertData(75);
        municipios.insertData(65);
        municipios.insertData(85);
        municipios.insertData(60);
        municipios.insertData(70);
        municipios.insertData(90);
        municipios.insertData(80);

        municipios.printInOrder();
    }
}
