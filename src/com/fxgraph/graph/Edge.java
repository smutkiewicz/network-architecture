package com.fxgraph.graph;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;

public class Edge extends Group {

    protected Cell source;
    protected Cell target;

    Line line;

    public Edge(Cell source, Cell target, int path) {

        this.source = source;
        this.target = target;

        source.addCellChild(target);
        target.addCellParent(source);

        line = new Line();
        setLineProperties();
        setLineColor(path);

        getChildren().add( line);

    }

    private void setLineProperties() {

        line.setStrokeWidth(3);
        line.startXProperty().bind( source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0));
        line.startYProperty().bind( source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0));

        line.endXProperty().bind( target.layoutXProperty().add( target.getBoundsInParent().getWidth() / 2.0));
        line.endYProperty().bind( target.layoutYProperty().add( target.getBoundsInParent().getHeight() / 2.0));

    }

    private void setLineColor(int path) {
        switch (path) {
            case 1 :
                line.setStroke(Color.RED);
                break;
            case 2 :
                line.setStroke(Color.GREEN);
                break;
            case 3 :
                line.setStroke(Color.CYAN);
                break;
            case 4 :
                line.setStroke(Color.MAGENTA);
                break;
            case 5 :
                line.setStroke(Color.ORANGE);
                break;
            default:
                line.setStroke(Color.BLACK);
                line.setStrokeWidth(1);
                break;
        }
    }

    public Cell getSource() {
        return source;
    }

    public Cell getTarget() {
        return target;
    }

}
