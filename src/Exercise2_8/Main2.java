package Exercise2_8;

import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Scanner;

public class Main2 {
    static double relojDeSimulacion;
    static int tipoDeSiguienteEvento;
    static int servidorSalida;

    //Acumuladores estadísticos
    static int transaccionesAtendidas;
    static double totalDeDemoras;
    static double areaClientesEnCola;
    static double areaServidorNuevo;


    //Listas Pertinentes
    static ListaDoblementeEnlazada ListaDeEventos = new ListaDoblementeEnlazada();
    static ListaEnlazadaConCola FilaTransacciones = new ListaEnlazadaConCola();

    static ListaEnlazadaConCola servidorNuevo = new ListaEnlazadaConCola();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Media de llegadas = 0.1 hora / transacción -> 1/600 hora transaccion
        //Hay 1 servidor. Que procesa con un tiempo medio de servicio de
        //5 min / transacción

        ListaDeEventos.insert(randomExponential(6), 1, -1); //Agendo la primera llegada

        do {

            timing(); //Determina el tipo del siguiente evento, avanza el reloj de simulación, elimina el primer registro de la lista
            //de eventos.

            switch (tipoDeSiguienteEvento) {
                case 1 -> llegada();//Manjeo de llegada y llegadas nuevas
                case 2 -> salida();//Terminación del servicio
            }

        } while (relojDeSimulacion <= 480); //Pasadas 8 horas no se reciben nuevas transacciones

        do {

            timing2();

            if (tipoDeSiguienteEvento == 2) {
                salida();
            }

        } while (!FilaTransacciones.isEmpty()); //Se atiende hasta que no hayan transacciones en la cola

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

        FilaTransacciones.pushBack(relojDeSimulacion); //Pongo la transaccion que llega a hacer cola y guardo su tiempo
        // de llegada

        if (servidorNuevo.isEmpty()) { // Miro si el servidor está libre
            double tiempoDeLlegada = FilaTransacciones.topFront(); //Tomo el dato del tiempo de llegada de la primera tra-
            //nsaccion que está en la fila de transacciones

            servidorNuevo.pushBack(tiempoDeLlegada); //Ocupo el servidor por que está libre

            double demoraEnCola = relojDeSimulacion - tiempoDeLlegada; //Calculo la demora en cola de esa transaccion

            totalDeDemoras += demoraEnCola; //Incremento el total de demoras

            ListaDeEventos.insert(randomExponential(5), 2, servidorSalida); //Agendo un evento de terminacion
            //de servicio para la transaccion que estoy atendiendo

            FilaTransacciones.popFront(); //Elimino el primer elemento que esté en la fila de transacciones por que ya lo estoy
            //atendiendo en el servidor
        }

        //Si el servidor está ocupado, no hago nada.

    }

    public static void salida() {

        servidorNuevo.popFront(); //Desocupo el servidor
        transaccionesAtendidas++; //Incremento el numero de transacciones atendidas

        if (!FilaTransacciones.isEmpty()) { //Miro si la fila de transacciones tiene transacciones por atender

            double tiempoDeLlegada = FilaTransacciones.topFront(); //Tomo el tiempo de llegada de la primera transaccion en la fila

            servidorNuevo.pushBack(tiempoDeLlegada); //Ocupo el servidor
            double demoraEnCola = relojDeSimulacion - tiempoDeLlegada; //Calculo la demora en cola

            totalDeDemoras += demoraEnCola; //Aumento el acumulador de demoras

            FilaTransacciones.popFront(); //Elimino el primer elemento de la fila de transacciones por que ya lo guardé
            //en el servidor.

            ListaDeEventos.insert(randomExponential(5), 2, servidorSalida); //Agendo una terminacion del servicio

        }
    }

    public static void actualizarAcumuladores(double tiempoParaArea) {

        areaClientesEnCola += FilaTransacciones.getTamanho() * tiempoParaArea;

        if (servidorNuevo.isEmpty()) {
            areaServidorNuevo += 0;
        } else {
            areaServidorNuevo += tiempoParaArea; //Se puede multiplicar por 1 que es cuando el servidor está ocupado.
        }

    }

    public static void reporte() {

        double promedioDemoraEnCola = totalDeDemoras / transaccionesAtendidas;
        double promedioDeTransaccionesEnCola = areaClientesEnCola / relojDeSimulacion;
        double utilizacionPromedioServidorNuevo = areaServidorNuevo / relojDeSimulacion;

        double costoTotalDeServicio = (relojDeSimulacion / 60) * 1500;
        double costoDeEspera = (totalDeDemoras / 60) * 1000;
        double costoGeneral = costoTotalDeServicio + costoDeEspera;

        System.out.println("REPORTE: ");
        System.out.println("La demora promedio en cola es: " + (promedioDemoraEnCola));
        System.out.println("El número promedio de transacciones en cola es: " + promedioDeTransaccionesEnCola);
        System.out.println("La utilización promedio del servidor nuevo es: " + utilizacionPromedioServidorNuevo);
        System.out.println("Costo total de espera: -" + costoDeEspera);
        System.out.println("Costo total del servidor : -" + costoTotalDeServicio);
        System.out.println("Costos totales: -" + costoGeneral);

    }
}
