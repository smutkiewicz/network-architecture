package com.fxgraph.cells;

import javafx.scene.control.Label;
import com.fxgraph.graph.Cell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

public class TriangleLabelCell extends Cell {

    public TriangleLabelCell(String id) {

        super(id);

        double width = 50;
        double height = 50;

        StackPane view = new StackPane();
        Label label = new Label(id);
        Polygon polygon = new Polygon( width / 2, 0, width, height, 0, height);

        polygon.setStroke(Color.RED);
        polygon.setFill(Color.RED);

        label.setFont(Font.font("System", 14));

        view.getChildren().addAll(polygon,label);

        setView(view);

    }

}
