package src.CSR_Graph;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;

public class GraphAlgorithms {
    public GraphAlgorithms(){

    }

    public boolean BFS(Graph g, int start_id, int finish_id){
        int[] edges = g.getEdges();
        int[] nodes = g.getNodes();

        boolean[] visited = new boolean[nodes.length]; //keeps track of airports that have already been visited
        Queue<Integer> q = new LinkedList<>(); //keeps track of airports that still need to be checked
        visited[start_id] = true;
        q.add(start_id);
        System.out.print("Path: ");

        while(q.size() != 0){ // Runs until id is found or all connected nodes are checked
            int next = q.poll();
            System.out.print(next + " | ");
            if(next == finish_id){
                return true; // The airport_id has been found
            } else{
                Pair p = g.getAirportEdges(next);
                if(p.first == p.second){
                    continue; // This airport has no edges, continue
                }
                for (int i = p.first; i < p.second; i++){ // Add all edges that are unvisited to the queue
                    if(!visited[edges[i]]){
                        visited[edges[i]] = true;
                        q.add(edges[i]);
                    }
                }
            }
        }
        return false;
    }
}
