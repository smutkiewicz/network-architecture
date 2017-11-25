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

        return network;
    }

    public Network floyd(Network network, InputPath inputPath) {

        int start = inputPath.start - 1;
        int end = inputPath.end - 1;
        int verticesAmount = network.getVerticesArray().size();
        int p[][]= new int [verticesAmount+1][verticesAmount+1];

        double d[][]= new double [verticesAmount+1][verticesAmount+1];

        ArrayList<Link> links = network.getLinksArray();
        ArrayList<Vertex> vertices = network.getVerticesArray();

        //wypełniam macierz nieskończonościami
        for (double[] row: d)
            Arrays.fill(row, Double.MAX_VALUE);

        for (int[] row: p)
            Arrays.fill(row, -1);

        int i=0, j=0, k=0;

        //wypełniam przekątną zerami, bo droga A->A =0
        for (i = 0; i < verticesAmount+1; i++)
            d[i][i]=0;

        Vertex vertex;
        i=1;
        j=1;
        //wypełniam kolejno istniejące połączenia węzłów wagami
        for (i = 1; i < verticesAmount+1; i++) {
            //links = network.getVertex(i).getLinksList();
            //vertex=network.getVertex(i);
            //System.out.println(vertices.get(i).getId());
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
        //k->k, u->j, v->i
        for (k = 0; k < verticesAmount; k++) {
            for (j = 0; j < verticesAmount; j++){
                for (i = 0; i < verticesAmount; i++){
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

        return network;
    }


    public void setInputPaths(List<MyAlgorithm.InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }

    public void setNetwork(Network network) {
        this.net = network;
    }

}
