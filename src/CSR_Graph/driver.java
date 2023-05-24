package src.CSR_Graph;

public class driver {

    public static void main(String[] args) {
        String node_file = "input_data/small_test_nodes.csv"; // Contains information on all airport id's.
        String edge_file = "input_data/small_test_edges.csv"; // Contains all edge information sorted by airport id's
        Graph g = new Graph(node_file, edge_file);

        //_____DEBUG DATA ARRAYS START_____
        int[] n = g.getNodes();
        int[] e = g.getEdges();
        int[] d = g.getEdge_distances();

        printArray("NODES", n);
        printArray("EDGES", e);
        printArray("DISTANCES", d);
        //_____DEBUG DATA ARRAYS END_____

        //____DEBUG getAirportEdges START_____
        Pair p = g.getAirportEdges(5);
        for (int i = p.first; i < p.second; i++){
            System.out.print(e[i] + ", ");
        }
        System.out.println("");
        if(p.first == p.second){
            System.out.println("No edges found for airport_id.");
        }
        //____DEBUG getAirportEdges END_____
    }

    public static void printArray(String name, int[] a){ // Debugging function used to print arrays beautifully.
        System.out.print(name + " [");
        for (int i = 0; i < a.length; i++){
            if(i != a.length-1){
                System.out.print(a[i] + ", ");
            }
            else{
                System.out.print(a[i]);
            }
        }
        System.out.println("]");
    }
}
