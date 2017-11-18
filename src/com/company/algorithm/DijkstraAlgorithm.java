package com.company.algorithm;

import com.company.Vertex;
import com.company.link.Link;
import com.company.network.Network;
import com.company.network.TestNetwork;

import java.util.ArrayList;
import java.util.List;


public class DijkstraAlgorithm implements MyAlgorithm {

    private List<MyAlgorithm.InputPath> inputPaths = new ArrayList<>();
    private Network net;
    
    private int[][] A; // macierz sąsiedztwa
    public double[][] W; // macierz wag

    // given adjacency matrix adj, finds shortest path from A to B
    public Network execute(Network network) {
        //a = start b = end
        setNetwork(network);
        Network.Matrixes matrixes = network.getNeighbors();

        A = matrixes.getNeighboursMatrix();
        W = matrixes.getWeightMatrix();

        int n = net.getVerticesArray().size();

        int a = inputPaths.get(0).start - 1;
        int b = inputPaths.get(0).end - 1;

        double inf = 1.0;

        ArrayList<Double> dist = new ArrayList<>();
        ArrayList<Boolean> vis = new ArrayList<>();

        for(int i = 0; i < n; ++i) {
            dist.add(inf);
            vis.add(false);
        }

        dist.set(a, 0.0);

        for(int i = 0; i < n; ++i) {
            int cur = -1;
            for(int j = 0; j < n; ++j) {
                if (vis.get(j)) continue;
                if (cur == -1 || dist.get(j) < dist.get(cur)) {
                    cur = j;
                }
            }

            vis.set(cur, true);

            for(int j = 0; j < n; ++j) {
                double path = dist.get(cur) + A[cur][j];
                if (path < dist.get(j)) {
                    dist.set(j, path);
                }
            }
        }

        System.out.println(dist.get(b));

        return network;
    }

    public void setInputPaths(List<MyAlgorithm.InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }

    public void setNetwork(Network network) {
        this.net = network;
    }

}
