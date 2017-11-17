package com.company;

import com.company.link.Link;
import java.util.ArrayList;

public class Vertex {

    //lista krawędzi(łączy) wychodzących z węzła
    private ArrayList<Link> links = new ArrayList<>();

    //unikatowy id
    private int id;

    // współrzędne
    private double x = 50;
    private double y = 50;

    /**
     * @param id Unikatowy numer wierzchołka
     */
    public Vertex(int id) {
        this.id = id;
    }

    /**
     * @param id Unikatowy numer wierzchołka
     */
    public Vertex(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    /**
     * Jeśli łącze nie istnieje w zbiorze łączy węzła,
     * to dodaje go
     * @param link Nowe łącze do dodania
     */
    public void addLink(Link link) {
        if(!links.contains(link)){
            links.add(link);
        }
    }

    /**
     * @param other Obiekt łącza, którego szukamy
     * @return true Jeśli istnieje w węźle
     */
    public boolean containsLink(Link other) {
        return links.contains(other);
    }

    /**
     * @param index Indeks łącza w liście links
     * @return Link Konkretne łącze z listy
     */
    public Link getLink(int index) {
        return links.get(index);
    }

    /**
     * @param index Indeks łącza w liście links do usunięcia
     * @return Link Usuwane łącze
     */
    public Link removeLink(int index) {
        return links.remove(index);
    }

    /**
     * @param link Obiekt łącza w liście links do usunięcia
     */
    public void removeLink(Link link) {
        links.remove(link);
    }

    /**
     * @return ArrayList<Edge> zwraca KOPIĘ listy łączy
     */
    public ArrayList<Link> getLinksList() {
        return new ArrayList<Link>(links);
    }

    /**
     * @return int Ilość łączy w węźle
     */
    public int getNumberOfLinks() {
        return links.size();
    }

    /**
     * @return int Id węzła
     */
    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return "v" + id;
    }

    /**
     * @param o Obiekt do porównania
     * @return true jeśli id obiektów są zgodne
     */
    public boolean equals(Object o) {
        if(o instanceof Vertex) { //obiekt jest węzłem
            Vertex v = (Vertex)o;
            return this.id == v.id;
        } else {
            return false;
        }
    }
}


