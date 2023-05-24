package src.CSR_Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PipedInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {

    private int[] nodes; //contains numbers indicating what index the information in edges begins. EX: nodes[1] = 3 means that the edges for airport with id 1 begins at edges[3]
    private int[] edges; //contains a list of all edges sorted based on the airport id that points to them
    private String[] airport_information; //additional information on each airport can be found based on airport id
    private int[] edge_distances; // corresponds 1 to 1 with edges and describes the distance of that edge

    private int edges_size; // tracks the size within the dynamic edges array

    public Graph(String node_file, String edge_file){
        nodes = new int[2];
        edges = new int[2];
        airport_information = new String[2]; //TODO: parse airport node data and fill airport_information
        edge_distances = new int[2];
        int edges_index = 0;

        try (Scanner s = new Scanner(new File(edge_file))) {
            String line = s.nextLine();
            int prevIndex = 0;
            int currIndex = 0;

            while (s.hasNextLine()) {
                line = s.nextLine();
                String[] columns = line.split(",");
                currIndex = Integer.parseInt(columns[0]);
                int currAirportEdge = Integer.parseInt(columns[1]);
                int dist = Integer.parseInt(columns[2]);

                if (currIndex != prevIndex){
                    while (currIndex >= nodes.length){
                        nodes = doubleArray(nodes);
                    }
                    nodes[currIndex] = edges_index;
                    prevIndex = currIndex;
                }

                if(edges_index >= edges.length){
                    edges = doubleArray(edges);
                    edge_distances = doubleArray(edge_distances);
                }
                edges[edges_index] = currAirportEdge;
                edge_distances[edges_index] = dist;
                edges_index++;
            }

            if (currIndex+1 >= nodes.length){
                nodes = doubleArray(nodes);
            }
            nodes[currIndex+1] = edges_index;

            for (int i = 1; i < nodes.length; i++){
                if(nodes[i] == 0){
                    nodes[i] = nodes[i-1];
                }
            }

            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("___File Not Found___");
            e.printStackTrace();
        }
    }

    //_____GETTER METHODS BEGIN_____//
    public int[] getNodes(){
        return nodes;
    }

    public int[] getEdges(){
        return edges;
    }

    public int[] getEdge_distances(){
        return edge_distances;
    }

    public String[] getAirport_information(){
        return airport_information;
    }
    //_____GETTER METHODS END_____//

    //_____HELPER METHODS BEGIN_____//
    private int[] doubleArray(int[] a){
        int[] newArr = new int[a.length * 2];
        System.arraycopy(a, 0, newArr, 0, a.length);
        return newArr;
    }

    public Pair getAirportEdges(int airport_id){
        int edges_index_f = nodes[airport_id];
        int edges_index_s;

        if(airport_id == nodes.length-1){
            edges_index_s = edges.length;
        }
        else{
            edges_index_s = nodes[airport_id+1];
        }
        return new Pair(edges_index_f, edges_index_s);
    }
    //_____HELPER METHODS END_____//
}
