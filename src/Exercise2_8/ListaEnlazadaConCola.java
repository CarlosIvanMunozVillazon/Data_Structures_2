package Exercise2_8;

public class ListaEnlazadaConCola {

    private class Nodo {

        private Nodo siguiente;

        private double tiempoDeLlegada;

        public Nodo (double tiempoDeLlegada) {
            this.tiempoDeLlegada = tiempoDeLlegada;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;
    private Nodo cola;
    private int tamanho;

    public ListaEnlazadaConCola () {
        this.cabeza = null;
        this.cola = null;
        this.tamanho = 0;
    }

    public boolean isEmpty() {
        return (cabeza == null && cola == null);
    }

    public void pushBack(double tiempoDeLlegada) { //introduce atrás los nuevos elementos, es decir que los encola.
        Nodo nuevoNodo = new Nodo(tiempoDeLlegada);
        if (isEmpty()) {
            cabeza = nuevoNodo;
        } else {
            cola.siguiente = nuevoNodo;
        }
        cola = nuevoNodo;
        this.tamanho += 1;
    }

    public double topBack() {
        if (isEmpty()) {
            throw new RuntimeException("The linked list is empty.");
        } else {
            return cola.tiempoDeLlegada;
        }
    }

    public double topFront() { // retorna el dato que está al principio de la lista
        if (isEmpty()) {
            throw new RuntimeException("The linked list is empty.");
        } else {
            return cabeza.tiempoDeLlegada;
        }
    }

    public void popFront() { // elimina el nodo que está al principio de la lista

        if(isEmpty()){
            throw new RuntimeException("The linked list is empty.");
        } else if (cabeza == cola){
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.siguiente;
        }

        this.tamanho -= 1;
    }

    public void printLinkedListWithTail() {

        Nodo referencia = cabeza;

        while (referencia != null) {
            System.out.print(referencia.tiempoDeLlegada + " ");
            referencia = referencia.siguiente;
        }

        System.out.println();
    }

    public int getTamanho() {
        return this.tamanho;
    } // retorna el tamaño de la lista, es decir la cantidad de elementos
    //que lleva
}
