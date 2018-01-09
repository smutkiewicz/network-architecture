package com.company.algorithm;

import com.company.Cable;
import com.company.link.Link;
import com.company.network.Network;

import java.util.ArrayList;
import java.util.List;

public class OptimizationAlgorithm implements MyAlgorithm {

    private static final int EMPTY_PATH = 0;
    private List<MyAlgorithm.InputPath> inputPaths = new ArrayList<>();

    public Network execute(Network network) {

        MSTAlgorithm mst = new MSTAlgorithm();
        mst.setStartingVertexId(network.getCentralId());
        mst.execute(network);

        System.out.println("\nCABLE OPTIMIZATION RESULTS:");

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
        int minCost = Integer.MAX_VALUE;
        double ratio, maxRatio = 0;

        System.out.print("Ratios: ");

        for (Cable cable: cablesAvailable) {

            double arg = (double)requiredCapacity/cable.getCapacity();
            amountOfCables = (int)Math.ceil(arg);
            ratio = (double)amountOfCables/cable.getCost();
            System.out.printf("%1.2f, ", ratio);

            if(ratio >= maxRatio) {

                if(cable.getCost() < minCost) {
                    l.setCableType(cable, amountOfCables * getMaxAmountOfCablesForClients(l));
                    maxRatio = ratio;
                    minCost = cable.getCost();
                }

            }
        }

        System.out.println("");

    }

    private int getMaxAmountOfCablesForClients(Link l) {
        int max = Math.max(l.getStart().getAmountOfClients(), l.getEnd().getAmountOfClients());

        if(max > 0) return max; // przypadek gdy nie ma klientów po obu stronach
        else return 1;
    }

    private void printUsedCable(Link l) {

        int amountOf = l.getAmountOfCables();

        if(amountOf > 0) {
            int id = l.getCableType().getId();
            System.out.println("cost: " + l.getCost() +
                    "clients: " + l.getStart().getAmountOfClients() + "/" + l.getEnd().getAmountOfClients() +
                    ", cableType: " + id +
                    ", cost per one: " + l.getCableType().getCost() +
                    ", amountOf: " + amountOf);
            System.out.println("");
        }
    }

    public void setInputPaths(List<MyAlgorithm.InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }
}
