package com.company.network;

import com.company.algorithm.MyAlgorithm;
import com.company.link.Link;
import com.company.link.UndirectedLink;
import com.company.Vertex;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public boolean addLink(Vertex start, Vertex end) {
        return addLink(1, start, end);
    }

    @Override
    public boolean addLink(int id, Vertex start, Vertex end) {
        if(start.equals(end)) {
            return false;
        }

        Link l = new UndirectedLink(start, end, id);

        if(links.containsKey(l.hashCode())){
            return false;
        } else if(start.containsLink(l) || end.containsLink(l)){
            return false;
        }

        links.put(l.hashCode(), l);
        start.addLink(l);
        end.addLink(l);
        return true;
    }

    @Override
    public boolean addLink(int id, Vertex start, Vertex end, int cost) {
        if(start.equals(end)) {
            return false;
        }

        Link l = new UndirectedLink(start, end, id, cost);

        if(links.containsKey(l.hashCode())){
            return false;
        } else if(start.containsLink(l) || end.containsLink(l)){
            return false;
        }

        links.put(l.hashCode(), l);
        start.addLink(l);
        end.addLink(l);
        return true;
    }

    public Link getLink(int startId, int endId) {
        Vertex start = getVertex(startId);

        for (Link l : start.getLinksList()) {
            if(l.getStart().getId() == startId
                    && l.getEnd().getId() == endId)
                return l;

            if(l.getStart().getId() == endId
                    && l.getEnd().getId() == startId)
                return l;
        }

        return null;
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

                if (l.getStart().getId() == endId
                        && l.getEnd().getId() == startId) {
                    l.addPath(path);
                }
            }
        }
    }

    public void colorFloydPath(double d[][], int p[][], MyAlgorithm.InputPath inputPath, int pathId) {

        int i = inputPath.start;
        int j = inputPath.end;

        if (i == j)
            System.out.println("To ten sam węzeł, brak połączenia");
        else {

            if (d[i][j] < d[j][i])
                colorShortestPath(i, j, p, pathId);
            else
                colorShortestPath(j, i, p, pathId);
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
