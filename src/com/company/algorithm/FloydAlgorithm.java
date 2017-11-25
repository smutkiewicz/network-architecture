package com.company.algorithm;

import com.company.Vertex;
import com.company.link.Link;
import com.company.network.Network;

import java.util.*;

public class FloydAlgorithm implements MyAlgorithm {

    private List<MyAlgorithm.InputPath> inputPaths = new ArrayList<>();
    private Network net;

    public Network execute(Network network) {

        int start = inputPaths.get(0).start - 1;
        int end = inputPaths.get(0).end - 1;
        int verticesAmount = network.getVerticesArray().size();
        int p[][]= new int [verticesAmount][verticesAmount];

        double d[][]= new double [verticesAmount][verticesAmount];

        ArrayList<Link> links = network.getLinksArray();
        ArrayList<Vertex> vertices = network.getVerticesArray();

        //wypełniam macierz nieskończonościami
        for (double[] row: d)
            Arrays.fill(row, Double.MAX_VALUE);

        for (int[] row: p)
            Arrays.fill(row, -1);

        int i=0, j=0, k=0;

        //wypełniam przekątną zerami, bo droga A->A =0
        for (i = 0; i < verticesAmount; i++)
            d[i][i]=0;

        //System.out.println(Arrays.toString(d));
        System.out.println(Arrays.deepToString(d));

        //wypełniam kolejno istniejące połączenia węzłów wagami
        for (i = 0; i < verticesAmount; i++) {
            links = vertices.get(i).getLinksList();
            for (j = 0; j < links.size(); j++) {
                k = links.get(j).getEnd().getId();
                d[i][k]=links.get(j).getWeight();
                p[i][k]=vertices.get(i).getId(); //jeśli podają węzły w kolejności, to jest to to samo co p[i][k]=i;
           }
        }

        System.out.println(Arrays.deepToString(d));
        System.out.println(Arrays.deepToString(p));

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

        return network;
    }


    public void setInputPaths(List<MyAlgorithm.InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }

    public void setNetwork(Network network) {
        this.net = network;
    }

}
