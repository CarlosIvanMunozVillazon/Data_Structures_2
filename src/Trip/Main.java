package Trip;

import AVL.AVLTree;
import java.util.Objects;
import java.util.Scanner;

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

        Scanner escaner = new Scanner(System.in);

        String ruta = escaner.nextLine();

        String [] ruta2 = ruta.split(" ");

        int municipio1 = getMunicipioId(ruta2[0]);
        int municipio2 = getMunicipioId(ruta2[1]);

        int distance = municipios.getKeyDistance(municipio1, municipio2);
        distance += 1;
        System.out.print(distance);

        escaner.close();
    }

    public static int getMunicipioId(String nombreMunicipio) {

        if (Objects.equals(nombreMunicipio, "Aquitania")) {
            return 5;
        } else if (Objects.equals(nombreMunicipio, "Chiquiza")) {
            return 10;
        } else if (Objects.equals(nombreMunicipio, "Chivata")) {
            return 15;
        } else if (Objects.equals(nombreMunicipio, "Combita")) {
            return 20;
        } else if (Objects.equals(nombreMunicipio, "Gameza")) {
            return 25;
        } else if (Objects.equals(nombreMunicipio, "Guayata")) {
            return 30;
        } else if (Objects.equals(nombreMunicipio, "Guican")) {
            return 35;
        } else if (Objects.equals(nombreMunicipio, "Mongui")) {
            return 40;
        } else if (Objects.equals(nombreMunicipio, "Nobsa")) {
            return 45;
        } else if (Objects.equals(nombreMunicipio, "Raquira")) {
            return 50;
        } else if (Objects.equals(nombreMunicipio, "Sachica")) {
            return 55;
        } else if (Objects.equals(nombreMunicipio, "Soraca")) {
            return 60;
        } else if (Objects.equals(nombreMunicipio, "Sutamarchan")) {
            return 65;
        } else if (Objects.equals(nombreMunicipio, "Tenza")) {
            return 70;
        } else if (Objects.equals(nombreMunicipio, "Tibasosa")) {
            return 75;
        } else if (Objects.equals(nombreMunicipio, "Tinjaca")) {
            return 80;
        } else if (Objects.equals(nombreMunicipio, "Toca")) {
            return 85;
        } else if (Objects.equals(nombreMunicipio, "Topaga")) {
            return 90;
        } else {
            return -1;
        }
    }

}
