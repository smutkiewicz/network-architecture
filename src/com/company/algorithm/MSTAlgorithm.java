package com.company.algorithm;

import com.company.Vertex;
import com.company.link.Link;
import com.company.network.Network;

import java.util.ArrayList;
import java.util.List;

public class MSTAlgorithm implements MyAlgorithm {

    private static final int PATH_ID = 1;
    private List<InputPath> inputPaths = new ArrayList<>();
    private ArrayList<InputPath> outputPath = new ArrayList<>();

    private int startingVertexId = 1;

    public Network execute(Network network) {

        DijkstraZeroAlgorithm dijkstraZero = new DijkstraZeroAlgorithm();
        ArrayList<MyAlgorithm.InputPath> listOfPaths = new ArrayList<>();
        ArrayList<Vertex> vertices = new ArrayList<>();
        MyAlgorithm.InputPath inputPath;
        Vertex start, end;

        addAllRequiredVertices(network, vertices);

        start = getById(vertices, startingVertexId);

        for(int i = 0; i < vertices.size(); i++) {

            end = vertices.get(i);

            if(start.getId() != end.getId()) {

                inputPath = new MyAlgorithm.InputPath(start.getId(), end.getId());
                listOfPaths.add(inputPath);

                System.out.println("MSTAlgorithm from " + inputPath.start + " to " + inputPath.end);
                dijkstraZero.setInputPaths(listOfPaths);
                dijkstraZero.execute(network);
                //dijkstraZero.getOutputPath();
                listOfPaths.clear();

            }
        }

        colorAllZeroLinks(network);

        return network;
    }

    private Vertex getById(ArrayList<Vertex> vertices, int id) {

        for (Vertex v: vertices) {
            if(v.getId() == id)
                return v;
        }

        return null;
    }

    public void addAllRequiredVertices(Network network, List<Vertex> vertices) {

        network.getVerticesArray().forEach(v -> {
            if(v.getRequired()) {
                vertices.add(v);
                System.out.println(v.toString());
            }
        });
    }

    public void colorAllZeroLinks(Network network) {

        network.getLinksSet().forEach(l -> {
            if(l.getWeight() == 1)
                network.colorLink(l.getStart().getId(), l.getEnd().getId(), PATH_ID);
        });

    }

    public void setInputPaths(List<MyAlgorithm.InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }

    public void setStartingVertexId(int id) {
        this.startingVertexId = id;
    }

    /*public interface AlgorithmInteraction {
        public void
    }*/

}
