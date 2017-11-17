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
}
