package com.company;

import com.company.network.Network;

import com.company.network.NetworkParser;
import com.fxgraph.graph.CellType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import static com.company.network.NetworkFactory.DIRECTED;
import static com.company.network.NetworkFactory.UNDIRECTED;

public class Main extends Application {

    private Network network;

    @Override
    public void start(Stage primaryStage) throws Exception {

        try {

            NetworkParser parser = new NetworkParser(primaryStage);
            network = parser.parseNetwork(UNDIRECTED, NetworkParser.ADVANCED_NETWORK_MODE);

            View view = new View(primaryStage, network);
            view.setVertexType(CellType.RECTANGLE_LABEL);
            view.initView();
            view.showGraph();

        } catch (Exception e) {
            Platform.exit(); //invalid file, stop app
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
