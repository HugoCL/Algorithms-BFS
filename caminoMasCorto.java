import java.util.*;

class caminoMasCorto {

    static class Nodo{
        /* Dado que se trabaja con listas, los valores de los vertices se corren -1 al guardarlos, por lo que en idNodo se
           guarda el valor real del nodo al agregarle un +1
        */
        int idNodo;

        // Atributo que permite saber si este nodo ya fue visitado
        boolean marcado = false;

        Nodo(int idNodo){
            this.idNodo = idNodo+1;
        }
        // Lista que contiene los nodos adyacentes a este nodo
        ArrayList<Integer> nodosAdyacentes = new ArrayList<>();

        void addNodoAdyacente(int nodo){
            nodosAdyacentes.add(nodo);
        }

        void marcarNodo(){
            marcado = true;
        }

        void desmarcarNodo(){
            marcado = false;
        }

        boolean isMarcado(){
            return marcado;
        }
    }


    Nodo[] crearListaAdyacencia(ArrayList<int[]> ciudad, int vertices){
        // Se crea una lista de adyacencia de tamaño de N intersecciones
        Nodo[] grafo = new Nodo[vertices];

        /* Se inicializa creando nodos con sus ID correspondientes. Por ejemplo, el vertice 1 estaría ubicado en la
           posición 0 de la lista, pero el objeto Nodo tendría como ID el 1.
         */
        for (int i = 0; i < vertices; i++) {
            grafo[i] = new Nodo(i);
        }

        for (int[] interseccion : ciudad) {
            // Se leen ambas intersecciones
            int vA = interseccion[0];
            int vB = interseccion[1];

            // Se guardan las intersecciones en el nodo de la lista correspondiente
            grafo[vA-1].addNodoAdyacente(vB);
            grafo[vB-1].addNodoAdyacente(vA);
        }
        return grafo;
    }

    void rutaMasCorta(Nodo[] grafo, int idVerticeInicio, int idVerticeFinal){
        // Se crea un array de números que contendrá la distancia del vertice inicial a todos los vertices del grafo
        int[] distTo = new int[grafo.length];

        // Queue que tendrá los vertices pendientes de traversal
        Queue<Integer> queue = new LinkedList<>();

        // Suponemos que los vertices no tienen conexion con otros vertices, por lo que su distancia preliminar es -1
        for (int i = 0; i < grafo.length; i++) {
            distTo[i] = -1;
        }

        // El primer vertice en ser retirado es el inicial, por lo que su distancia es 0. Se agrega al queue.
        int distanciaActual = 0;
        queue.add(idVerticeInicio);
        distTo[idVerticeInicio] = distanciaActual;

        // Booleano para saber cuando ya llegamos al destino, para detener el traversal del BFS
        boolean nodoEncontrado = false;

        // Ciclo que se realiza mientras hayan elementos en el queue
        while (!queue.isEmpty()){
            // Se remueve por FiFo un elemento del queue y se marca
            int idVerticeActual = queue.remove();
            grafo[idVerticeActual].marcarNodo();

            // Se hace traversal de todos los nodos adyacentes del elemento removido de la queue
            for (int i = 0; i < grafo[idVerticeActual].nodosAdyacentes.size(); i++) {
                int idNodoActual = grafo[idVerticeActual].nodosAdyacentes.get(i)-1;

                // Si el nodo actual ya ha sido marcado, se ignora
                if (!grafo[idNodoActual].isMarcado()){

                    // Si no ha sido marcado, se añade al queue y se marca
                    queue.add(idNodoActual);
                    grafo[idNodoActual].marcarNodo();

                    // Si el nodo está conectado al nodo inicial, se setea su distancia a 1
                    if (idVerticeActual == idVerticeInicio){
                        distTo[idNodoActual] = 1;
                    }
                    // Si el nodo es uno cualquiera, se calcula la distancia de manera normal
                    else{
                        // Se agrega un 2 para anular el -1 y agregar el aumento de distancia
                        distTo[idNodoActual] += distTo[idVerticeActual]+2;

                        // Si el nodo actual es el destino, se termina el traversal
                        if (idNodoActual == idVerticeFinal){
                            nodoEncontrado = true;
                            break;
                        }
                    }
                }
            }
            // Si se encontro el destino, se limpia la queue, por lo que termina la ejecucion del BFS
            if (nodoEncontrado){
                queue.clear();
            }
        }
        if (distTo[idVerticeFinal] == -1){
            System.out.println("SIN CAMINO");
        }
        else{
            System.out.println(distTo[idVerticeFinal]);
        }

    }
}
