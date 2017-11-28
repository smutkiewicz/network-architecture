package com.company.algorithm;

import com.company.network.Network;

import java.util.*;

public class FloydAlgorithm implements MyAlgorithm {

    private List<MyAlgorithm.InputPath> inputPaths = new ArrayList<>();

    public Network execute(Network network) {

        int verticesAmount = network.getVerticesArray().size();
        int p[][]= new int [verticesAmount+1][verticesAmount+1];
        double d[][]= new double [verticesAmount+1][verticesAmount+1];
        int i, j, k, myPath=1;

        //wypełniam macierz nieskończonościami
        for (double[] row: d)
            Arrays.fill(row, Double.MAX_VALUE);

        for (int[] row: p)
            Arrays.fill(row, -1);

        //wypełniam przekątną zerami, bo droga A->A =0
        for (i = 0; i < verticesAmount+1; i++)
            d[i][i]=0;

        //wypełniam kolejno istniejące połączenia węzłów wagami
        for (i = 1; i < verticesAmount+1; i++) {
            for (j = 1; j < verticesAmount+1; j++) {
                if (i==j)
                    continue;
                else if (network.checkIfLinked(network.getVertex(i), network.getVertex(j))){
                    d[i][j] = network.getLink(i, j).getWeight();
                    p[i][j] = i;
                }
            }
        }

        for (k = 1; k < verticesAmount+1; k++) {
            for (j = 1; j < verticesAmount+1; j++){
                for (i = 1; i < verticesAmount+1; i++){
                    if (d[j][i]>d[j][k]+d[k][i]) {
                        d[j][i] = d[j][k] + d[k][i];
                        p[j][i] = p[k][i];
                    }
                }
            }
        }

        for (InputPath inputPath : inputPaths) {
            network.colorFloydPath(d, p, inputPath, myPath);
            myPath++;
        }

        return network;
    }

    public void setInputPaths(List<MyAlgorithm.InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }

}