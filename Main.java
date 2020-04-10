import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        caminoMasCorto cmc = new caminoMasCorto();
        Scanner entrada = new Scanner(System.in);

        // Se realizan las acciones correspondientes mientras hayan casos (Ciudades) disponibles
        while(entrada.hasNext()){

            // Se registran la cantidad de vertices (Intersecciones) y aristas(Calles) del grafo
            int intersecciones = entrada.nextInt();
            int calles = entrada.nextInt();
            ArrayList<int[]> callesList = new ArrayList<>();

            // Se crea una lista con todas las aristas del grafo (Calles)
            for (int i = 0; i < calles; i++) {
                callesList.add(new int[]{entrada.nextInt(), entrada.nextInt()});
            }
            // Se crea una lista de nodos, que nos servirá como lista de adyacencia para representar el grafo
            caminoMasCorto.Nodo[] grafo = cmc.crearListaAdyacencia(callesList, intersecciones);
            int numCasos = entrada.nextInt();
            for (int i = 0; i < numCasos; i++) {

                // Se obtienen los vertices (Intersecciones) de inicio y fin
                int inicio = entrada.nextInt()-1;
                int fin = entrada.nextInt()-1;

                // Mediante BFS, se encuentra la ruta mas corta entre 2 vertices (Intersecciones)
                cmc.rutaMasCorta(grafo, inicio, fin);

                // Se desmarcan todos los nodos visitados en el traversal anterior
                for (caminoMasCorto.Nodo nodo: grafo) {
                    nodo.desmarcarNodo();
                }
            }
            System.out.println("- - - -");
        }
    }
}
