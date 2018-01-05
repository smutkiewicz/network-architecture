package com.company.link;

import com.company.Cable;
import com.company.Vertex;

import java.util.ArrayList;

public abstract class Link implements Comparable<Link> {

    protected static final int STANDARD_LINK = 0;
    protected Vertex start, end;
    protected Cable cableType;
    protected int id;
    protected int cost;
    protected int amountOfCables = 0;
    protected double weight;
    protected ArrayList<Integer> paths = new ArrayList<>();

    public abstract Vertex getLink(Vertex current);

    /**
     * @param start Pierwszy węzeł
     * @param end Końcowy węzeł
     * @param id Waga łącza
     */
    public Link(Vertex start, Vertex end, int id) {
        this.start = start;
        this.end = end;
        this.id = id;
        this.cost = 0;

        double powX = Math.pow(end.getX() - start.getX(), 2);
        double powY = Math.pow(end.getY() - start.getY(), 2);
        this.weight = Math.sqrt(powX + powY);
    }

    public Link(Vertex start, Vertex end, int id, int cost) {
        this.start = start;
        this.end = end;
        this.id = id;
        this.cost = cost;

        double powX = Math.pow(end.getX() - start.getX(), 2);
        double powY = Math.pow(end.getY() - start.getY(), 2);
        this.weight = Math.sqrt(powX + powY);
    }

    public void addPath(int path) {
        if(!paths.contains(path)) {
            paths.add(path);
        }
    }

    public int getPath() {
        if(paths.size() != 0) {
            return paths.remove(0);
        } else {
            return STANDARD_LINK;
        }
    }

    public boolean containsPath() {
        return !paths.isEmpty();
    }

    public Vertex getStart() {
        return this.start;
    }

    public Vertex getEnd() {
        return this.end;
    }

    public int getId() {
        return this.id;
    }

    public int getCost() {
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCableType(Cable cable, int amountOfCables) {
        this.cableType = cable;
        this.amountOfCables = amountOfCables;
    }

    public double getWeight() {
        return weight;
    }

    public void setZeroWeight() {
        weight = 1;
    }

    public Cable getCableType() {
        return cableType;
    }

    public int getAmountOfCables() {
        return amountOfCables;
    }

    public String toString() {
        // przykład: "Link: v11->v13"
        return "Link: " + start.toString() + "->" + end.toString();
    }

    /**
     * Metoda dziedziczący z interfejsu Comparable,
     * służy do porównywania łączy na podstawie ich wag.
     *
     * @param other Łącze do porównania
     * @return int Jeśli > 0, to waga naszego łącza jest wyższa
     */
    @Override
    public int compareTo(Link other) {
        double diff = this.weight - other.weight;
        return (diff > 0) ? 1 : 0;
    }

    /**
     * @param o Obiekt do porównania
     * @return true jeśli o jest łączem o identycznych węzłach
     */
    public boolean equals(Object o) {
        if(o instanceof Link) { //obiekt jest łączem
            Link l = (Link)o;
            return l.start.equals(this.start) && l.start.equals(this.end);
        } else {
            return false;
        }
    }
}


