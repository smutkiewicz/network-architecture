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

    public Network execute(Network network) {

        newKruskalMST(network);

        return network;
    }

    // The main function to construct MST using Kruskal's algorithm
    public Network newKruskalMST(Network network) {

        ArrayList<Link> result = new ArrayList<>(); // wyliczone MST
        ArrayList<Link> graph = new ArrayList<>();
        int V = network.getVerticesSet().size();
        int e = 0;  // indeks result[]
        int i = 0;  // do posortowanych linków

        graph.addAll(network.getLinksSet());
        graph.sort(new Comparator<Link>() {
            @Override
            public int compare(Link l1, Link l2) {
                return l1.compareTo(l2);
            }
        });

        ArrayList<Subset> subsets = new ArrayList<>();

        for (int v = 0; v < V; ++v) {
            subsets.add(new Subset(0, v));
        }

        while (e < V - 1) {

            Link nextLink = graph.get(i++);

            int x = find(subsets, nextLink.getStart().getId() - 1);
            int y = find(subsets, nextLink.getEnd().getId() - 1);

            // sprawdzanie cykli
            if (x != y) {
                result.add(e++, nextLink);
                union(subsets, x, y);
            }
        }

        System.out.println("Najlżejsze drzewo rozpinające");

        for (i = 0; i < e; ++i) {

            int start = result.get(i).getStart().getId();
            int end = result.get(i).getEnd().getId();

            network.colorLink(start, end, 1);
            System.out.printf("%d->%d == %f\n", start, end,
                    result.get(i).getWeight());
        }

        return network;
    }

    int find(ArrayList<Subset> subsets, int i) {

        if (subsets.get(i).parent != i)
            subsets.get(i).parent = find(subsets, subsets.get(i).parent);

        return subsets.get(i).parent;
    }

    void union(ArrayList<Subset> subsets, int x, int y) {

        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets.get(xroot).rank < subsets.get(yroot).rank)
            subsets.get(xroot).setParent(yroot);
        else if (subsets.get(xroot).rank > subsets.get(yroot).rank)
            subsets.get(yroot).setParent(yroot);
        else {
            subsets.get(yroot).setParent(xroot);
            subsets.get(yroot).rank++;
        }
    }

    public void setInputPaths(List<InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }

    private class Subset {
        public int rank;
        public int parent;

        public Subset(int rank, int parent) {
            this.rank = rank;
            this.parent = parent;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }
    }

}
