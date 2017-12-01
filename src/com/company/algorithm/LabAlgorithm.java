package com.company.algorithm;

import com.company.Vertex;
import com.company.link.Link;
import com.company.network.Network;

import java.util.ArrayList;
import java.util.List;

public class LabAlgorithm implements MyAlgorithm {

    private List<MyAlgorithm.InputPath> inputPaths = new ArrayList<>();

    public Network execute(Network network) {

        Vertex start, end;
        MyAlgorithm.InputPath inputPath;
        DijkstraLabAlgorithm dijkstra = new DijkstraLabAlgorithm();
        ArrayList<MyAlgorithm.InputPath> listOfPaths = new ArrayList<>();
        ArrayList<Vertex> vertices = new ArrayList<>();

        for (Vertex v: network.getVerticesArray()) {
            if(v.getRequired()) {
                vertices.add(v);
                System.out.println(v.toString());
            }
        }


        start = vertices.get(0);

        for(int i=1; i < vertices.size(); i++) {
            end = vertices.get(i);
            inputPath = new MyAlgorithm.InputPath(start.getId(), end.getId());
            System.out.println("from " + inputPath.start + " to " + inputPath.end);
            listOfPaths.add(inputPath);

            dijkstra.setInputPaths(listOfPaths);
            dijkstra.execute(network);
            listOfPaths.clear();
        }

        for (Link l: network.getLinksArray()) {
            if(l.getWeight() == 1)
                network.colorLink(l.getStart().getId(), l.getEnd().getId(), 1);
        }

        return network;
    }

    public void setInputPaths(List<MyAlgorithm.InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }

}
