package Exercise2_8;

import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    //Funcionamiento de la simulación
    static double relojDeSimulacion;
    static int tipoDeSiguienteEvento;
    static int servidorSalida;

    //Acumuladores estadísticos

    static int transaccionesAtendidas;
    static double totalDeDemoras;
    static double areaClientesEnCola; //
    static double areaServidor1;
    static double areaServidor2;
    static double areaServidor3;


    //Listas pertinentes
    static ListaDoblementeEnlazada ListaDeEventos = new ListaDoblementeEnlazada();
    static ListaEnlazadaConCola FilaTransacciones = new ListaEnlazadaConCola();

    static ListaEnlazadaConCola [] servidores = new ListaEnlazadaConCola[3];

    static ListaEnlazadaConCola servidor1 = new ListaEnlazadaConCola();
    static ListaEnlazadaConCola servidor2 = new ListaEnlazadaConCola();
    static ListaEnlazadaConCola servidor3 = new ListaEnlazadaConCola();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //Media de llegadas = 0.1 hora / transacción -> 1/600 hora transaccion
        //Hay tres servidores. Cada uno de los cuales, procesa
        //Tiempo medio de servicio = 15 min / transacción

        servidores[0] = servidor1;
        servidores[1] = servidor2;
        servidores[2] = servidor3;

        ListaDeEventos.insert(randomExponential(6),1,-1); //agendo la primera llegada

        do {

            timing(); //determina el tipo del siguiente evento, avanza el reloj de simulación, elimina el primer registro de la lista
            //de eventos.
            //NOTA: Es mejor utilizar medias en decimal para las distribuciones de probabilidad.
            switch (tipoDeSiguienteEvento){
                case 1 -> llegada();
                case 2 -> salida();//terminación del servicio
            }

        }while(transaccionesAtendidas < 50);

        reporte();

        scanner.close();
    }

    public static double randomExponential (double mean) {
        /*Random randomNumber = new Random();
        double u = randomNumber.nextDouble();*/

//        double r = ThreadLocalRandom.current().nextDouble(1.00000000000000000001);
        SecureRandom random = new SecureRandom();

        double n = random.nextDouble();
//        double k = Math.random()/Math.nextDown(1.0);
        return (-1 * (mean * Math.log(n)));
    }

    public static void timing() {

        double [] evento = ListaDeEventos.topFront();

        /* Para sustentar
        double tiempoUltimoEvento = relojDeSimulacion;
        double tiempoEventoSiguiente = relojDeSimulacion + evento[0];

        double tiempoDesdeElUltimoEvento = tiempoEventoSiguiente - tiempoUltimoEvento;
        */

        relojDeSimulacion += evento[0];
        tipoDeSiguienteEvento = (int)evento[1];
        servidorSalida = (int)evento[2];

        double tiempoDesdeElUltimoEvento = evento[0];
        actualizarAcumuladores(tiempoDesdeElUltimoEvento);

        ListaDeEventos.popFront();

    }

    public static void llegada (){

        ListaDeEventos.insert(randomExponential(6), 1, -1); //agendo una nueva llegada

        int i = 0;
        while (i < 3 && !servidores[i].isEmpty()){ //miro si hay servidores libres
            i++;
        }

        if (i >= 3) { // si no hay servidores libres, hago que la transaccion espere en cola
            FilaTransacciones.pushBack(relojDeSimulacion);
        } else { // si hay servidores libres
            FilaTransacciones.pushBack(relojDeSimulacion);
            servidorSalida = i;
            servidores[servidorSalida].pushBack(relojDeSimulacion); //ocupo el servidor que esté libre

            double tiempoDeLlegada = FilaTransacciones.topFront(); //tomo el tiempo de llegada de la primera transaccion
            //que esté en la fila
            double demoraEnCola = relojDeSimulacion - tiempoDeLlegada; //calculo la demora en cola de esa transaccion

            totalDeDemoras += demoraEnCola; //incremento el total de demoras

            ListaDeEventos.insert(randomExponential(15),2,servidorSalida); //agendo un evento de terminacion
            //de servicio para la transaccion que estoy atendiendo

            FilaTransacciones.popFront(); //elimino el primer elemento que esté en la fila de transacciones por que ya lo estoy
            //atendiendo en el servidor
        }

    }

    public static void salida() {

        servidores[servidorSalida].popFront(); //desocupo el servidor
        transaccionesAtendidas++; //Incremento el numero de transacciones atendidas

        if (!FilaTransacciones.isEmpty()){ //Si la fila de transacciones no está vacia

            double tiempoDeLlegada = FilaTransacciones.topFront(); //Tomo el tiempo de llegada de la primera transaccion en la fila

            servidores[servidorSalida].pushBack(tiempoDeLlegada); //Ocupo el servidor que dejo libre en la linea 109
            double demoraEnCola = relojDeSimulacion - tiempoDeLlegada; //Calculo la demora en cola

            totalDeDemoras += demoraEnCola; //aumento el acumulador de demoras

            FilaTransacciones.popFront(); //elimino el primer elemento de la fila de transacciones por que ya lo guardé
            //en un servidor.

            ListaDeEventos.insert(randomExponential(15),2,servidorSalida); //agendo una terminacion del servicio
            //para este servidor.

        }
    }

    public static void actualizarAcumuladores (double tiempoParaArea) {

        areaClientesEnCola += FilaTransacciones.getTamanho() * tiempoParaArea;

        if (servidores[0].isEmpty()){
            areaServidor1 += 0;
        } else {
            areaServidor1 += tiempoParaArea; //se puede multiplicar por 1 que es cuando el servidor está ocupado.
        }

        if (servidores[1].isEmpty()){
            areaServidor2 += 0;
        } else {
            areaServidor2 += tiempoParaArea;
        }

        if (servidores[2].isEmpty()){
            areaServidor3 += 0;
        } else {
            areaServidor3 += tiempoParaArea;
        }

    }

    public static void reporte () {

        double promedioDemoraEnCola = totalDeDemoras / transaccionesAtendidas;
        double promedioDeTransaccionesEnCola = areaClientesEnCola / relojDeSimulacion;

        double utilizacionPromedioServidor1 = areaServidor1 / relojDeSimulacion;
        double utilizacionPromedioServidor2 = areaServidor2 / relojDeSimulacion;
        double utilizacionPromedioServidor3 = areaServidor3 / relojDeSimulacion;

        double utilizacionPromedioTotalDeServidores = utilizacionPromedioServidor1 +
                utilizacionPromedioServidor2 + utilizacionPromedioServidor3;

        System.out.println("REPORTE: ");
        System.out.println("La demora promedio en cola es: " + promedioDemoraEnCola);
        System.out.println("El numero promdeio de transacciones en cola es: " + promedioDeTransaccionesEnCola);
        System.out.println("La utilizacion promedio global de los servidores es: " + utilizacionPromedioTotalDeServidores);
        System.out.println("Cantidad de transacciones procesadas: " + transaccionesAtendidas);

    }
}
