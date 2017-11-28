package com.company.network;

public class NetworkFactory {

    public final static int DIRECTED = 1;
    public final static int UNDIRECTED = 0;

    public Network produceNetwork(int direction) {

        Network network = null;

        if(direction == DIRECTED)
            network = new DirectedNetwork();
        else //if(direction == UNDIRECTED)
            network = new UndirectedNetwork();

        return network;
    }

}
