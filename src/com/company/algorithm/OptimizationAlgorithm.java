package com.company.algorithm;

import com.company.Cable;
import com.company.Vertex;
import com.company.link.Link;
import com.company.network.Network;

import java.util.ArrayList;
import java.util.List;

public class OptimizationAlgorithm implements MyAlgorithm {

    private static final int EMPTY_PATH = 0;
    private List<MyAlgorithm.InputPath> inputPaths = new ArrayList<>();

    public Network execute(Network network) {

        MSTAlgorithm mst = new MSTAlgorithm();
        mst.execute(network);

        System.out.println();
        System.out.println("Cable optimization results:");

        network.getLinksSet().forEach(l -> {
            if(l.containsPath()) {
                findTheBestSetOfCables(network.getAvailableCables(), l);
                printUsedCable(l);
            }
        });

        return network;
    }

    private void findTheBestSetOfCables(ArrayList<Cable> cablesAvailable, Link l) {

        System.out.println("Finding for " + l.toString());
        int amountOfCables, requiredCapacity = l.getCost();
        double ratio, maxRatio = 0;

        for (Cable cable: cablesAvailable) {

            double arg = (double)requiredCapacity/cable.getCapacity();
            amountOfCables = (int)Math.ceil(arg);
            ratio = (double)amountOfCables/cable.getCost();
            System.out.println("Ratio for cable " + cable.getId() + ": " + ratio);

            if(ratio > maxRatio) {
                l.setCableType(cable, amountOfCables*getMaxAmountOfCablesForClients(l));
                maxRatio = ratio;
            }
        }

    }

    private int getMaxAmountOfCablesForClients(Link l) {
        return Math.max(l.getStart().getAmountOfClients(), l.getEnd().getAmountOfClients());
    }

    private void printUsedCable(Link l) {

        int amountOf = l.getAmountOfCables();

        if(amountOf > 0) {
            int id = l.getCableType().getId();
            System.out.println(l.toString() +
                    " cableType: " + id +
                    ", cost per one: " + l.getCableType().getCost() +
                    ", amountOf: " + amountOf);
        }
    }

    public void setInputPaths(List<MyAlgorithm.InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }
}
