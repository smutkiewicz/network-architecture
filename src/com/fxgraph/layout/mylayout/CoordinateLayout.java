package com.fxgraph.layout.mylayout;

import java.util.List;
import java.util.Random;

import com.fxgraph.graph.Cell;
import com.fxgraph.graph.Graph;
import com.fxgraph.layout.base.Layout;

public class CoordinateLayout extends Layout {

    Graph graph;

    public CoordinateLayout(Graph graph) {

        this.graph = graph;

    }

    public void execute() {

        List<Cell> cells = graph.getModel().getAllCells();

    }

}


