package src.CSR_Graph;

import java.util.Arrays;

public class driver {

    public static void main(String[] args) {
        String node_file = "input_data/small_test_nodes.csv"; // Contains information on all airport id's.
        String edge_file = "input_data/small_test_edges.csv"; // Contains all edge information sorted by airport id's
        Graph g = new Graph(node_file, edge_file);

        //_____DEBUG DATA ARRAYS START_____
        int[] n = g.getNodes();
        int[] e = g.getEdges();
        int[] d = g.getEdge_distances();
        String[][] a = g.getAirport_information();

        printArray("NODES", castArray(n));
        printArray("EDGES", castArray(e));
        printArray("DISTANCES", castArray(d));
        print2DArray("Airport Information", a);
        //_____DEBUG DATA ARRAYS END_____

        //____DEBUG getAirportEdges START_____
        Pair p = g.getAirportEdges(5);
        g.printAirportEdges(5);
        //____DEBUG getAirportEdges END_____

        //_____DEBUG GraphAlgorithms START_____//
        GraphAlgorithms algos = new GraphAlgorithms();
        boolean found = algos.BFS(g, 1, 10);
        if(found){
            System.out.println("Found node.");
        } else{
            System.out.println("Did not find node.");
        }
        //_____DEBUG GraphAlgorithms END_____//
    }

    public static <T> void printArray(String name, T[] a){ // Debugging function used to print arrays beautifully.
        System.out.print(name + ": ");
        System.out.println(Arrays.toString(a));

        /*System.out.print(name + " [");
        for (int i = 0; i < a.length; i++){
            if(i != a.length-1){
                System.out.print(a[i] + ", ");
            }
            else{
                System.out.print(a[i]);
            }
        }
        System.out.println("]");*/
    }

    public static <T> void print2DArray(String name, T[][] a) {
        System.out.println(name + ":");
        for (T[] r : a) {
            System.out.println(Arrays.toString(r));
        }
    }

    public static Integer[] castArray(int[] array) {
        Integer[] intArr = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            intArr[i] = array[i];
        }
        return intArr;
    }
}
