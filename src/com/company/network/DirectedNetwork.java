package com.company.network;

import com.company.algorithm.MyAlgorithm;
import com.company.link.DirectedLink;
import com.company.link.Link;
import com.company.Vertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2017-11-11.
 */
public class DirectedNetwork extends Network {

    public DirectedNetwork() {
        super();
    }

    /**
     * Konstruktor dodaje dodatkowo węzły z listy vertices
     *
     * @param vertices Lista węzłów
     */
    public DirectedNetwork(ArrayList<Vertex> vertices) {
        super(vertices);
    }

    public boolean addLink(Vertex start, Vertex end){
        return addLink(1, start, end);
    }

    public boolean addLink(int id, Vertex start, Vertex end){
        if(start.equals(end)){
            return false;
        }

        //ensures the Edge is not in the Graph
        Link l = new DirectedLink(start, end, id);
        if(links.containsKey(l.hashCode())){
            return false;
        } else if(start.containsLink(l) || end.containsLink(l)) {
            //and that the Edge isn't already incident to one of the vertice
            return false;
        }

        links.put(l.hashCode(), l);
        start.addLink(l);
        return true;
    }

    /**
     * Koloruje łącze o początku w start, a końcu w end.
     *
     * @param startId id węzła statowego
     * @param endId id węzła końcowego
     * @param path id koloru ścieżki
     */
    public void colorLink(int startId, int endId, int path) {

        Vertex start = vertices.get(startId);
        List<Link> linksList = start.getLinksList();

        if(startId != endId) {
            for (Link l : linksList) {
                if (l.getStart().getId() == startId
                        && l.getEnd().getId() == endId) {
                    l.addPath(path);
                }
            }
        }
    }

    public void colorFloydPath(double d[][], int p[][], MyAlgorithm.InputPath inputPath, int pathId){

        int i = inputPath.start;
        int j = inputPath.end;

        if (i == j)
            System.out.println("To ten sam węzeł, brak połączenia");
        else {

            if (p[i][j] == -1)
                System.out.println("Brak połączenia");
            else
                colorShortestPath(i, j, p, pathId);

        }
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
            W[x-1][y-1] = z;
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
