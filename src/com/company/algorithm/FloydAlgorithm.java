package com.company.algorithm;

import com.company.Vertex;
import com.company.link.Link;
import com.company.network.Network;

import java.util.*;

public class FloydAlgorithm implements MyAlgorithm {

    private List<MyAlgorithm.InputPath> inputPaths = new ArrayList<>();
    private Network net;

    private int[][] p;
    private double[][] d;
    private int verticesAmount;

    public Network execute(Network network) {

        int myPath = 1;
        verticesAmount = network.getVerticesArray().size();
        p = new int [verticesAmount+1][verticesAmount+1];
        d = new double [verticesAmount+1][verticesAmount+1];

        //wypełniam macierz nieskończonościami
        for (double[] row: d)
            Arrays.fill(row, Double.MAX_VALUE);

        for (int[] row: p)
            Arrays.fill(row, -1);

        //wypełniam przekątną zerami, bo droga A->A =0
        for (int i = 0; i < verticesAmount+1; i++)
            d[i][i]=0;

        //wypełniam kolejno istniejące połączenia węzłów wagami
        for (int i = 1; i < verticesAmount+1; i++) {
            for (int j = 1; j < verticesAmount+1; j++) {
                if (i==j)
                    continue;
                else if (network.checkIfLinked(network.getVertex(i), network.getVertex(j))){
                    d[i][j] = network.getLink(i, j).getWeight();
                    p[i][j] = i;
                }
            }
        }

        for (InputPath inputPath : inputPaths) {
            performOptimalization(network, inputPath, myPath);
            myPath++;
        }

        return network;
    }

    private void performOptimalization(Network network, InputPath inputPath, int idOfPath) {

        //WAŻNE!!! TUTAJ MUSIMY MIEĆ KLONY I NIE DZIAŁAĆ NA CZYSTYCH STARTOWYCH!!!
        double[][] d = this.d.clone();
        int[][] p = this.p.clone();

        for (int k = 1; k < verticesAmount+1; k++) {
            for (int j = 1; j < verticesAmount+1; j++){
                for (int i = 1; i < verticesAmount+1; i++){
                    if (d[j][i]>d[j][k]+d[k][i]) {
                        d[j][i] = d[j][k] + d[k][i];
                        p[j][i] = p[k][i];
                    }
                }
            }
        }

        floydPath(network, false, d, p, inputPath, idOfPath);

    }

    public void floydPath(Network network, boolean skierowanie, double d[][], int p[][], InputPath inputPath, int pathId) {

        int i = inputPath.start;
        int j = inputPath.end;

        if (i == j)
            System.out.println("To ten sam węzeł, brak połączenia");
        else if (p[i][j] == -1)
            System.out.println("Brak połączenia");
        else {

            if (!skierowanie)
                if (d[i][j] < d[j][i])
                    colorShortestPath(i, j, network, p, pathId);
                else
                    colorShortestPath(j, i, network, p, pathId);
            else
                colorShortestPath(i, j, network, p, pathId);
        }
    }

    public void colorShortestPath(int i, int j, Network network, int [][] p, int path) {

        while (i != j) {
            network.colorLink(j, p[i][j], path);
            j = p[i][j];
        }

        network.colorLink(j, p[i][j], path);

    }

    public void setInputPaths(List<MyAlgorithm.InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }

    public void setNetwork(Network network) {
        this.net = network;
    }
}
