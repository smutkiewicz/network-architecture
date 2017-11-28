package com.company.algorithm;

import com.company.link.Link;
import com.company.network.Network;
import com.company.network.TestNetwork;
import sun.nio.ch.Net;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MSTAlgorithm implements MyAlgorithm {

    private List<InputPath> inputPaths = new ArrayList<>();
    private Network net;

    public Network execute(Network network) {

        kruskalMST(network);

        return network;
    }

    void kruskalMST(Network network) {
        int V = network.getVerticesSet().size();
        ArrayList<Link> mst = new ArrayList<>();

        // Sort edges in non-decreasing order of their weight
        ArrayList<Link> links = new ArrayList<>();
        links.addAll(network.getLinksSet());

        links.sort(new Comparator<Link>() {
            @Override
            public int compare(Link l1, Link l2) {
                return l1.compareTo(l2);
            }
        });

        // Allocate memory for creating V ssubsets
        ArrayList<Subset> subsets = new ArrayList<>();

        // Create V subsets with single elements
        for (int v = 0; v < V; v++ ) {
            subsets.add(new Subset(0, v));
        }

        for (Link l : links) {

            int x = find(subsets, l.getStart().getId());
            int y = find(subsets, l.getEnd().getId());

            if(x != y) {
                mst.add(l);
                union(subsets, x, y);
            }
        }

        // print the contents of result[] to display the
        // built MST
        System.out.printf("Following are the edges in the constructed MST\n");

        for (Link l : mst) {
            System.out.println(l.toString());
        }

    }

    // A utility function to find set of an element i
    // (uses path compression technique)
    int find(ArrayList<Subset> subsets, int i) {
        // find root and make root as parent of i
        // (path compression)
        if (subsets.get(i).parent != i)
            subsets.get(i).parent = find(subsets, subsets.get(i).parent);

        return subsets.get(i).parent;
    }

    // A function that does union of two sets of x and y
    // (uses union by rank)
    void union(ArrayList<Subset> subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root of high
        // rank tree (Union by Rank)
        if (subsets.get(xroot).rank < subsets.get(yroot).rank)
            subsets.get(xroot).parent = yroot;
        else if (subsets.get(xroot).rank > subsets.get(yroot).rank)
            subsets.get(yroot).parent = xroot;

            // If ranks are same, then make one as root and
            // increment its rank by one
        else
        {
            subsets.get(yroot).parent = xroot;
            subsets.get(yroot).rank++;
        }
    }

    public void setInputPaths(List<InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }

    public void setNetwork(Network network) {
        this.net = network;
    }

    private class Subset {
        public int rank;
        public int parent;

        public Subset(int rank, int parent) {
            this.rank = rank;
            this.parent = parent;
        }
    }

}
