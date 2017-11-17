package com.company.link;

import com.company.Vertex;

import java.util.ArrayList;

public abstract class Link implements Comparable<Link> {

    protected static final int STANDARD_LINK = 0;
    protected Vertex start, end;
    protected int id;
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
    }

    public void addPath(int path) {
        if(!paths.contains(path)) {
            paths.add(path);
        }
    }

    public int getPath() {
        if(paths.size() != 0)
            return paths.get(0);
        else
            return STANDARD_LINK;
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

    public void setId(int id) {
        this.id = id;
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
        return this.id - other.id;
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


