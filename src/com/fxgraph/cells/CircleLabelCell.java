package com.fxgraph.cells;

import javafx.scene.control.Label;
import com.fxgraph.graph.Cell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class CircleLabelCell extends Cell {

    public CircleLabelCell(String id) {

        super(id);

        StackPane view = new StackPane();
        Label label = new Label(id);
        Circle circle = new Circle(25);

        circle.setStroke(Color.GREENYELLOW);
        circle.setFill(Color.GREENYELLOW);

        label.setFont(Font.font("System", 26));

        view.getChildren().addAll(circle,label);

        setView(view);

    }

}
