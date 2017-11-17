package com.fxgraph.layout.mylayout;

import java.util.List;
import java.util.Random;

import com.fxgraph.graph.Cell;
import com.fxgraph.graph.Graph;
import com.fxgraph.layout.base.Layout;

public class MyRandomLayout extends Layout {

    Graph graph;

    Random rnd = new Random();

    public MyRandomLayout(Graph graph) {

        this.graph = graph;

    }

    public void execute() {

        List<Cell> cells = graph.getModel().getAllCells();

        for (Cell cell : cells) {

            double x = rnd.nextDouble() * 500;
            double y = rnd.nextDouble() * 500;

            cell.relocate(x, y);

        }

    }

}
