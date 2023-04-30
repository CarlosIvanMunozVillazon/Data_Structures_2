package Exercise2_8;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Main {
    //Funcionamiento de la simulación
    static double relojDeSimulacion;
    static int tipoDeSiguienteEvento;
    static int servidorSalida;

    //Acumuladores estadísticos

    static int transaccionesAtendidas;
    static double totalDeDemoras;
    static double areaClientesEnCola;
    static double areaServidor1;
    static double areaServidor2;
    static double areaServidor3;


    //Listas pertinentes
    static ListaDoblementeEnlazada ListaDeEventos = new ListaDoblementeEnlazada();
    static ListaEnlazadaConCola FilaTransacciones = new ListaEnlazadaConCola();

    static ListaEnlazadaConCola[] servidores = new ListaEnlazadaConCola[3];

    static ListaEnlazadaConCola servidor1 = new ListaEnlazadaConCola();
    static ListaEnlazadaConCola servidor2 = new ListaEnlazadaConCola();
    static ListaEnlazadaConCola servidor3 = new ListaEnlazadaConCola();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //Media de llegadas = 0.1 hora / transacción -> 1/600 hora transaccion
        //Hay tres servidores. Cada uno de los cuales, procesa con un tiempo medio de servicio de
        //15 min / transacción

        servidores[0] = servidor1;
        servidores[1] = servidor2;
        servidores[2] = servidor3;

        ListaDeEventos.insert(randomExponential(6), 1, -1); //Agendo la primera llegada, con
        //servidor = -1, por que no se que servidor se va a desocupar primero.

        do {

            timing(); //Determina el tipo del siguiente evento, avanza el reloj de simulación, elimina el primer registro de la lista
            //de eventos.

            switch (tipoDeSiguienteEvento) {
                case 1 -> llegada();
                case 2 -> salida();//Terminación del servicio
            }

        } while (relojDeSimulacion <= 480);

        do {

            timing2();

            if (tipoDeSiguienteEvento == 2) {
                salida();
            }

        } while (!FilaTransacciones.isEmpty());

        reporte();

        scanner.close();
    }

    public static double randomExponential(double mean) {
//        Random randomNumber = new Random();
//        double u = randomNumber.nextDouble();

        double r = ThreadLocalRandom.current().nextDouble(1.000000000000001);
//        SecureRandom random = new SecureRandom();

//        double n = random.nextDouble();
//        double k = Math.random()/Math.nextDown(1.0);
        return (-1 * (mean * Math.log(r)));
    }

    public static void timing() {

        double[] evento = ListaDeEventos.topFront();

        /* Para sustentar
        double tiempoUltimoEvento = relojDeSimulacion;
        double tiempoEventoSiguiente = relojDeSimulacion + evento[0];

        double tiempoDesdeElUltimoEvento = tiempoEventoSiguiente - tiempoUltimoEvento;
        */

        relojDeSimulacion += evento[0];
        tipoDeSiguienteEvento = (int) evento[1];
        servidorSalida = (int) evento[2];

        double tiempoDesdeElUltimoEvento = evento[0];
        actualizarAcumuladores(tiempoDesdeElUltimoEvento);

        ListaDeEventos.popFront();

    }

    public static void timing2() {

        double[] evento = ListaDeEventos.topFront();

        if ((int) evento[1] == 2) {

            relojDeSimulacion += evento[0];
            tipoDeSiguienteEvento = (int) evento[1];
            servidorSalida = (int) evento[2];

            double tiempoDesdeElUltimoEvento = evento[0];
            actualizarAcumuladores(tiempoDesdeElUltimoEvento);

        }

        ListaDeEventos.popFront();

    }

    public static void llegada() {

        ListaDeEventos.insert(randomExponential(6), 1, -1); //Agendo una nueva llegada

        int i = 0;
        while (i < 3 && !servidores[i].isEmpty()) { //Miro si hay servidores libres
            i++;
        }

        FilaTransacciones.pushBack(relojDeSimulacion); // Pongo a hacer cola la transaccion que llega

        if (i < 3) { //Si hay servidores libres, paso la transaccion a ser atendida
            double tiempoDeLlegada = FilaTransacciones.topFront(); //Tomo el tiempo de llegada de la primera transaccion
            //que está haciendo cola

            servidorSalida = i; //Establezco el servidor que va a ser ocupado
            servidores[servidorSalida].pushBack(tiempoDeLlegada); //Ocupo el servidor

            double demoraEnCola = relojDeSimulacion - tiempoDeLlegada; //Calculo la demora en cola de la transaccion

            totalDeDemoras += demoraEnCola; //Incremento el total de demoras

            ListaDeEventos.insert(randomExponential(15), 2, servidorSalida); //Agendo un evento de terminacion
            //de servicio para la transaccion que estoy atendiendo

            FilaTransacciones.popFront(); //Elimino el primer elemento que esté en la fila de transacciones por que ya lo estoy
            //atendiendo en el servidor
        }

        //si no hay servidores libres, no hago nada.

    }

    public static void salida() {

        servidores[servidorSalida].popFront(); //Desocupo el servidor
        transaccionesAtendidas++; //Incremento el numero de transacciones atendidas

        if (!FilaTransacciones.isEmpty()) { //Si la fila de transacciones no está vacia

            double tiempoDeLlegada = FilaTransacciones.topFront(); //Tomo el tiempo de llegada de la primera transaccion en la fila

            servidores[servidorSalida].pushBack(tiempoDeLlegada); //Ocupo el servidor que previamente dejo libre.
            double demoraEnCola = relojDeSimulacion - tiempoDeLlegada; //Calculo la demora en cola

            totalDeDemoras += demoraEnCola; //Actualizo el acumulador de demoras

            FilaTransacciones.popFront(); //Elimino el primer elemento de la fila de transacciones por que ya lo guardé
            //en un servidor.

            ListaDeEventos.insert(randomExponential(15), 2, servidorSalida); //Agendo una terminacion del servicio
            //para este servidor.

        }
    }

    public static void actualizarAcumuladores(double tiempoParaArea) {

        areaClientesEnCola += FilaTransacciones.getTamanho() * tiempoParaArea;

        if (servidores[0].isEmpty()) {
            areaServidor1 += 0;
        } else {
            areaServidor1 += tiempoParaArea; //Se puede multiplicar por 1 que es cuando el servidor está ocupado.
        }

        if (servidores[1].isEmpty()) {
            areaServidor2 += 0;
        } else {
            areaServidor2 += tiempoParaArea;
        }

        if (servidores[2].isEmpty()) {
            areaServidor3 += 0;
        } else {
            areaServidor3 += tiempoParaArea;
        }

    }

    public static void reporte() {

        double promedioDemoraEnCola = totalDeDemoras / transaccionesAtendidas;
        double promedioDeTransaccionesEnCola = areaClientesEnCola / relojDeSimulacion;

        double utilizacionPromedioServidor1 = areaServidor1 / relojDeSimulacion;
        double utilizacionPromedioServidor2 = areaServidor2 / relojDeSimulacion;
        double utilizacionPromedioServidor3 = areaServidor3 / relojDeSimulacion;

        double utilizacionPromedioTotalDeServidores = utilizacionPromedioServidor1 +
                utilizacionPromedioServidor2 + utilizacionPromedioServidor3;

        double costoTotalDeServicio = (relojDeSimulacion / 60) * 1500;
        double costoDeEspera = (totalDeDemoras / 60) * 1000;
        double costoGeneral = costoTotalDeServicio + costoDeEspera;

        System.out.println("REPORTE: ");
        System.out.println("La demora promedio en cola es: " + (promedioDemoraEnCola / 60));
        System.out.println("El numero promedio de transacciones en cola es: " + promedioDeTransaccionesEnCola);
        System.out.println("La utilizacion promedio global de los servidores es: " + utilizacionPromedioTotalDeServidores);
        System.out.println("Costo total de espera: -" + costoDeEspera);
        System.out.println("Costo total de los servidores: -" + costoTotalDeServicio);
        System.out.println("Costos totales: -" + costoGeneral);

    }
}
