package com.fxgraph.cells;

import javafx.scene.control.Label;
import com.fxgraph.graph.Cell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class RectangleLabelCell extends Cell {

    public RectangleLabelCell(String id) {

        super(id);

        StackPane view = new StackPane();
        Label label = new Label(id);
        Rectangle rectangle = new Rectangle(50, 50);

        rectangle.setStroke(Color.DODGERBLUE);
        rectangle.setFill(Color.DODGERBLUE);

        label.setFont(Font.font("System", 26));

        view.getChildren().addAll(rectangle,label);

        setView(view);

    }

}
