package com.company;

import com.company.link.Link;
import com.company.network.Network;
import com.fxgraph.graph.CellType;
import com.fxgraph.graph.Graph;
import com.fxgraph.graph.Model;
import com.fxgraph.layout.base.Layout;
import com.fxgraph.layout.mylayout.CoordinateLayout;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Set;

public class View {

    private static String TITLE = "Network Architecture v.1.0 - (C) Michał Smutkiewicz & Joanna Zalewska";
    private Graph fxGraph = new Graph();
    private Network network;
    private Stage primaryStage;
    private CellType cellType = CellType.RECTANGLE_LABEL;

    public View(Stage primaryStage, Network network) {
        this.network = network;
        this.primaryStage = primaryStage;
    }

    public void initView() {

        BorderPane root = new BorderPane();

        fxGraph = new Graph();

        root.setCenter(fxGraph.getScrollPane());

        Scene scene = new Scene(root, 1024, 768);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle(TITLE);
        primaryStage.show();


    }

    public void showGraph() {

        addGraphComponents();

        Layout layout = new CoordinateLayout(fxGraph);
        layout.execute();

    }

    public void setVertexType(CellType type) {
        cellType = type;
    }

    private void addGraphComponents() {

        int startId, endId, path;
        Set<Vertex> vertices = network.getVerticesSet();
        Set<Link> links = network.getLinksSet();

        Model model = fxGraph.getModel();
        fxGraph.beginUpdate();

        for(Vertex v : vertices) {

            if(v.getRequired())
                cellType = CellType.TRIANGLE_LABEL;
            else
                cellType = CellType.RECTANGLE_LABEL;

            model.addCell("v " + v.getId(), v.getX(), v.getY(), cellType);
        }

        for(Link l : links) {
            startId = l.getStart().getId();
            endId = l.getEnd().getId();
            path = l.getPath();
            model.addEdge("v " + startId, "v " + endId, path);
        }

        fxGraph.endUpdate();

    }
}
