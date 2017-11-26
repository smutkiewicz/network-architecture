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

      /*  for (i = 1; i < verticesAmount+1; i++){
            for (j = 1; j < verticesAmount+1; j++) {
                if (d[i][j]==Double.MAX_VALUE)
                    System.out.print("oo ");
                else
                System.out.format("%2.2f ", d[i][j]);
            }
            System.out.println();
            }
*/

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

        boolean skierowanie = false;

        i=2;
        j=1;
        k=j;
        int path=0;
        if (i == j)
            System.out.println("debilu, to ten sam węzeł");
        else if (p[i][j] == -1)
            System.out.println("nie ma połączenia");
        else {
            path++;

            if (!skierowanie)
                if (d[i][j] < d[j][i]) {
                    System.out.println(d[i][j]);
                    while (i != j) {
                        network.colorLink(j, p[i][j], path);
                        //System.out.println("pierwszy if " + j + " ");
                        j = p[i][j];
                    }
                    network.colorLink(j, p[i][j], path);
                } else {
                    System.out.println(d[j][i]);
                    while (i != j) {
                        network.colorLink(i, p[j][i], path);
                        //System.out.println("drugi if " + i + " ");
                        i = p[j][i];
                    }
                    network.colorLink(j, p[i][j], path);
                }
            else {
                System.out.println(d[i][j]);
                while (i != j) {
                    network.colorLink(j, p[i][j], path);
                    //System.out.println("trzeci if " + j + " ");
                    j = p[i][j];
                }
                network.colorLink(j, p[i][j], path);
            }
        }
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

}
