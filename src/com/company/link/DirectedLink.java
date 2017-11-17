package com.company.link;

import com.company.Vertex;

public class DirectedLink extends Link {
    /**
     * @param start Pierwszy węzeł
     * @param end Końcowy węzeł
     * @param id id łącza
     */
    public DirectedLink(Vertex start, Vertex end, int id) {
        super(start, end, id);
    }

    /**
     * @param v
     * @return Zwraca TYLKO skierowane łącze
     */
    public Vertex getLink(Vertex v) {
        if(v.equals(start)) {
            return end;
        } else {
            return null;
        }
    }
}
