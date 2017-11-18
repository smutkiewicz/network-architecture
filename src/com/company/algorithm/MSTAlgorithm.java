package com.company.algorithm;

import com.company.network.Network;
import com.company.network.TestNetwork;

import java.util.ArrayList;
import java.util.List;

public class MSTAlgorithm implements MyAlgorithm {

    private List<InputPath> inputPaths = new ArrayList<>();
    private Network net;

    public Network execute(Network network) {

        setNetwork(network);

        for (InputPath i : inputPaths) {
            System.out.println(i.start + " " + i.end);
        }

        for (int i = 0; i < net.getLinksArray().size(); i++) {

            // działamy na oryginale, nie na kopii!!!
            if(net.getLinksArray().get(i).getId()%2 == 0) {
                net.getLinksArray().get(i).addPath(1);
            }

        }

        TestNetwork.displayNetworkSetup(net.getVerticesArray());

        return net;
    }

    public void setInputPaths(List<InputPath> inputPaths) {
        this.inputPaths = inputPaths;
    }

    public void setNetwork(Network network) {
        this.net = network;
    }

}
