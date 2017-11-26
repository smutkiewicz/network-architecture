package com.company.algorithm;

import com.company.Vertex;
import com.company.link.Link;
import com.company.network.Network;

import java.util.*;

public class FloydAlgorithm implements MyAlgorithm {

    private List<MyAlgorithm.InputPath> inputPaths = new ArrayList<>();
    private Network net;

    public Network execute(Network network) {

        for (InputPath inputPath : inputPaths) {
            floyd(network, inputPath);
        }

        int verticesAmount = network.getVerticesArray().size();
        int p[][]= new int [verticesAmount+1][verticesAmount+1];
        double d[][]= new double [verticesAmount+1][verticesAmount+1];
        int i=0, j=0, k=0;
        Vertex vertex;

        //wypełniam macierz nieskończonościami
        for (double[] row: d)
            Arrays.fill(row, Double.MAX_VALUE);

        for (int[] row: p)
            Arrays.fill(row, -1);

        //wypełniam przekątną zerami, bo droga A->A =0
        for (i = 0; i < verticesAmount+1; i++)
            d[i][i]=0;

        i=1;
        j=1;
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

        /*
        PRINT MACIERZY
        for (i = 1; i < verticesAmount+1; i++){
            for (j = 1; j < verticesAmount+1; j++) {
                if (d[i][j]==Double.MAX_VALUE)
                    System.out.print("oo ");
                else
                    System.out.format("%2.2f ", d[i][j]);

            }
            System.out.println();
        }

        for (i = 1; i < verticesAmount+1; i++){
            for (j = 1; j < verticesAmount+1; j++) {
                System.out.print(p[i][j]+" ");
            }
            System.out.println();
        }
        */

        //TUTAJ SIĘ ZACZYNA TEN FLOYD CO GO TRZEBA POPRAWIC NA SKIEROWANEGO/NIESKIEROWANEGO
        boolean skierowanie = true; //informacja o tym, czy sieć skierowana
        int path=0; //do różnych kolorów połączeń w grafie

        //floyd dla przykładowych połączeń węzłów (skoro zawsze przekazuję to samo, to się to da uprościć pewnie)

        path=floydPath(3, 6, true, network, d, p, path);
        path=floydPath(4, 5, true, network, d, p, path);
        path=floydPath(2,6, false, network, d, p, path);

        //A TU SIE KONCZY

        return network;
    }

    public Network floyd(Network network, InputPath inputPath) {

        int start = inputPath.start - 1;
        int end = inputPath.end - 1;

        return network;
    }


    public void setInputPaths(List<MyAlgorithm.InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }

    public void setNetwork(Network network) {
        this.net = network;
    }

    public void colorShortestPath(int i, int j, Network network, double[][] d, int [][] p, int path){
        while (i != j) {
            network.colorLink(j, p[i][j], path);
            j = p[i][j];
        }
        network.colorLink(j, p[i][j], path);

    }
    public int floydPath(int i, int j, boolean skierowanie, Network network, double d[][], int p[][], int path){
        if (i == j)
            System.out.println("To ten sam węzeł, brak połączenia");
        else if (p[i][j] == -1)
            System.out.println("Brak połączenia");
        else {
            path++;

            if (!skierowanie)
                if (d[i][j] < d[j][i])
                    colorShortestPath(i, j, network, d, p, path);
                else
                    colorShortestPath(j, i, network, d, p, path);
            else
                colorShortestPath(i, j, network, d, p, path);
        }
        return path;
    }

}
