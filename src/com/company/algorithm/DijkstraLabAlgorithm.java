package com.company.algorithm;

import com.company.network.Network;
import java.util.*;

public class DijkstraLabAlgorithm implements MyAlgorithm {

    private List<MyAlgorithm.InputPath> inputPaths = new ArrayList<>();

    public Network execute(Network network) {

        Network.Matrixes matrixes = network.getNeighbors();
        int n = network.getVerticesSet().size();

        double[][] W = matrixes.getWeightMatrix(); // macierz wag
        double[][] cost = new double[n][n];
        double[] distance = new double[n];
        double minDistance;

        int[] visited = new int[n];
        int[] pred = new int[n];
        int count, i, j, prev;

        int start = inputPaths.get(0).start - 1;
        int end = inputPaths.get(0).end - 1;
        int next = 0;

        for(i = 0; i < n; i++)
            for(j = 0; j < n; j++)
                if(W[i][j] == 0)
                    cost[i][j]=Double.MAX_VALUE;
                else
                    cost[i][j]=W[i][j];

        for(i = 0; i < n; i++) {
            distance[i]=cost[start][i];
            pred[i]=start;
            visited[i]=0;
        }

        distance[start]=0;
        visited[start]=1;
        count=1;

        while(count < n-1) {
            minDistance = Double.MAX_VALUE;

            for(i = 0; i < n; i++) {
                if (distance[i] < minDistance && !(visited[i] == 1)) {
                    minDistance = distance[i];
                    next = i;
                }
            }

            visited[next]=1;
            for(i = 0; i < n; i++) {
                if (!(visited[i] == 1))
                    if (minDistance + cost[next][i] < distance[i]) {
                        distance[i] = minDistance + cost[next][i];
                        pred[i] = next;
                    }
            }
            count++;
        }

        System.out.println("Start node: "+(start+1)+", ending node: "+(end+1));

        for(i = 0; i < n; i++) {
            if (i != start) {
                if (i == end) {
                   // System.out.printf("\nDistance of v%d = %f\n", (i + 1), distance[i]);

                    j = i;

                    do {
                        prev = j;
                        j = pred[j];

                        System.out.println("Make link between v" + (j + 1) + " and v" + (prev + 1) + ".");
                        network.setZeroWeight(j+1, prev+1);

                    } while (j != start);
                }
            }
        }

        return network;
    }


    public void setInputPaths(List<MyAlgorithm.InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }

}

