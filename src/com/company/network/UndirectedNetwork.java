package com.company.network;

import com.company.link.Link;
import com.company.link.UndirectedLink;
import com.company.Vertex;

import java.util.ArrayList;

public class UndirectedNetwork extends Network {

    public UndirectedNetwork() {
        super();
    }

    /**
     * Konstruktor dodaje dodatkowo węzły z listy vertices
     *
     * @param vertices Lista węzłów
     */
    public UndirectedNetwork(ArrayList<Vertex> vertices) {
        super(vertices);
    }

    // do podziału
    /**
     * This method adds am edge between Vertices one and two
     * of id 1, if no Edge between these Vertices already
     * exists in the Graph.
     *
     * @return true iff no Edge relating one and two exists in the Graph
     */
    @Override
    public boolean addLink(Vertex start, Vertex end) {
        return addLink(1, start, end);
    }


    // do podziału
    /**
     * Accepts two vertices and a id, and adds the edge
     * ({one, two}, id) iff no Edge relating one and two
     * exists in the Graph.
     *
     * @param start The first Vertex of the Edge
     * @param end The second Vertex of the Edge
     * @param id The id of the Edge
     * @return true iff no Edge already exists in the Graph
     */
    @Override
    public boolean addLink(int id, Vertex start, Vertex end) {
        if(start.equals(end)) {
            return false;
        }

        //ensures the Edge is not in the Graph
        Link l = new UndirectedLink(start, end, id);
        if(links.containsKey(l.hashCode())){
            return false;
        }

        //and that the Edge isn't already incident to one of the vertices
        else if(start.containsLink(l) || end.containsLink(l)){
            return false;
        }

        links.put(l.hashCode(), l);
        start.addLink(l);
        end.addLink(l);
        return true;
    }

    public Matrixes getNeighbors() {

        int N = getVerticesArray().size();
        int n = getLinksArray().size(); // ilość krawędzi
        int i,j,wmax,x,y;
        double z;

        int[][] A = new int[N][N]; // macierz sąsiedztwa
        double[][] W = new double[N][N]; // macierz wag

        for(i = 0; i < N; i++)
            for(j = 0; j < N; j++) W[i][j] = A[i][j] = 0;

        wmax = 0;
        ArrayList<Link> links = getLinksArray();

        for(i = 0; i < n; i++) {
            Link link = links.get(i);
            x = link.getStart().getId();
            y = link.getEnd().getId(); // odczytujemy krawędź
            z = link.getWeight();

            wmax = (x > wmax) ? x : wmax;
            wmax = (y > wmax) ? y : wmax;
            A[x-1][y-1] = 1;
            A[y-1][x-1] = 1;
            W[x-1][y-1] = z;
            W[y-1][x-1] = z;
        }

        for(i = 0; i < wmax; i++) {
            for(j = 0; j < wmax; j++)
                System.out.print(A[i][j]);
            System.out.println();
        }

        for(i = 0; i < wmax; i++) {
            for(j = 0; j < wmax; j++)
                System.out.print((int)W[i][j] + " ");
            System.out.println();
        }

        Matrixes matrixes = new Matrixes(A, W);

        return matrixes;

    }
}
