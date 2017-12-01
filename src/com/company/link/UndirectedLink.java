package com.company.link;

import com.company.Vertex;

public class UndirectedLink extends Link {
    /**
     * @param start Pierwszy węzeł
     * @param end Końcowy węzeł
     * @param id id łącza
     */
    public UndirectedLink(Vertex start, Vertex end, int id) {
        super(start, end, id);
    }

    /**
     * @param v
     * @return Zwraca nieskierowane łącze,
     *         jeśli posiadamy jeden z połączonych wierzchołków
     */
    public Vertex getLink(Vertex v) {
        if(v.equals(start) || v.equals(end))
            return (v.equals(start)) ? end : start;
        else
            return null;

    }
}
