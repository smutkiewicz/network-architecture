package com.company.network;
import com.company.algorithm.MyAlgorithm;
import com.company.link.Link;
import com.company.Vertex;

import java.util.*;

public abstract class Network {

    protected HashMap<Integer, Vertex> vertices;
    protected HashMap<Integer, Link> links;

    public abstract boolean addLink(Vertex start, Vertex end);
    public abstract boolean addLink(int id, Vertex start, Vertex end);
    public abstract Matrixes getNeighbors();

    public abstract void colorFloydPath(double d[][], int p[][], MyAlgorithm.InputPath inputPath, int pathId);
    public abstract void colorLink(int startId, int endId, int path);

    public Network() {
        this.vertices = new HashMap<Integer, Vertex>();
        this.links = new HashMap<Integer, Link>();
    }

    /**
     * Konstruktor dodaje dodatkowo węzły z listy vertices
     *
     * @param vertices Lista węzłów
     */
    public Network(ArrayList<Vertex> vertices) {
        this.vertices = new HashMap<Integer, Vertex>();
        this.links = new HashMap<Integer, Link>();

        for(Vertex v: vertices) {
            this.vertices.put(v.getId(), v);
        }

    }

    /**
     *
     * @param l łącze, którego szukamy
     * @return true iff this Graph contains the Edge e
     */
    public boolean containsLink(Link l) {
        if(l.getStart() == null || l.getEnd() == null){
            return false;
        }

        return links.containsKey(l.hashCode());
    }

    /**
     * Usuwa łącze l z Sieci, razem z łączami
     *
     * @param l Krawędź do usunięcia
     * @return Edge Krawędź usunięta z Sieci
     */
    public Link removeLink(Link l) {
        l.getStart().removeLink(l);
        l.getEnd().removeLink(l);
        return links.remove(l.hashCode());
    }

    public void colorShortestPath(int i, int j, int [][] p, int path) {

        while (i != j) {
            colorLink(j, p[i][j], path);
            j = p[i][j];
        }
    }

    /**
     * @param v Węzeł, którego szukamy
     * @return true jeśli Sieć zawiera ten węzeł
     */
    public boolean containsVertex(Vertex v) {
        return vertices.get(v.getId()) != null;
    }

    /**
     * @param id numer id węzła
     * @return Vertex Węzeł o konkretnym id
     */
    public Vertex getVertex(int id) {
        return vertices.get(id);
    }

    /**
     * Dodaje węzeł do Sieci. Jeśli węzeł z takim samym id istnieje w grafie,
     * istniejący węzeł jest zastępowany (tylko gdy overwriteExisting == true).
     * Jeśli zostanie usunięty, wszystkie krawędzie incydentne zostaną usunięte z Sieci.
     *
     * @param v
     * @param overwriteExisting
     * @return true jeśli węzeł został dodany pomyślnie
     */
    public boolean addVertex(Vertex v, boolean overwriteExisting) {
        Vertex current = vertices.get(v.getId());
        if(current != null) {
            if(!overwriteExisting) {
                return false;
            }

            while(current.getNumberOfLinks() > 0) {
                this.removeLink(current.getLink(0));
            }
        }

        vertices.put(v.getId(), v);
        return true;
    }

    /**
     * @param id węzła do usunięcia
     * @return Vertex The removed Vertex object
     */
    public Vertex removeVertex(int id) {
        Vertex v = vertices.remove(id);

        while(v.getNumberOfLinks() > 0) {
            removeLink(v.getLink((0)));
        }

        return v;
    }

    /**
     * @return Set<Integer> Zbiór unikalnych kluczy dla węzłów
     */
    public Set<Integer> vertexKeys() {
        return vertices.keySet();
    }

    /**
     * @return Set<Link> Krawędzie Sieci
     */
    public Set<Link> getLinksSet() {
        return new HashSet<Link>(links.values());
    }

    public ArrayList<Link> getLinksArray() {

        ArrayList<Link> array = new ArrayList<>();
        array.addAll(links.values());

        return array;
    }

    public Set<Vertex> getVerticesSet() {
        return new HashSet<Vertex>(vertices.values());
    }

    public ArrayList<Vertex> getVerticesArray() {

        ArrayList<Vertex> array = new ArrayList<>();
        array.addAll(vertices.values());

        return array;
    }

    public class Matrixes {

        private int[][] A; // macierz sąsiedztwa
        private double[][] W; // macierz wag

        public Matrixes(int[][] A, double[][] W) {
            this.A = A;
            this.W = W;
        }

        public double[][] getWeightMatrix() {
            return W;
        }

        public int[][] getNeighboursMatrix() {
            return A;
        }

    }

    public boolean checkIfLinked(Vertex start, Vertex end) {
        for (Link l : start.getLinksList()) {
            if(l.getEnd().getId() == end.getId())
                return true;
        }

        return false;
    }

    public boolean checkIfLinked(int startId, int endId) {
        Vertex start = vertices.get(startId);
        Vertex end = vertices.get(endId);

        for (Link l : start.getLinksList()) {
            if(l.getEnd().getId() == end.getId())
                return true;
        }

        return false;
    }

    public Link getLink(int startId, int endId) {
        Vertex start = getVertex(startId);

        for (Link l : start.getLinksList()) {
            if(l.getStart().getId() == startId
                    && l.getEnd().getId() == endId)
                return l;
        }

        return null;
    }

}


