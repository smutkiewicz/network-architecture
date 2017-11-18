package com.company.algorithm;

import com.company.network.Network;

import java.util.List;

/**
 * Created by Admin on 2017-11-16.
 */
public interface MyAlgorithm {

    Network execute(Network network);
    void setInputPaths(List<InputPath> inputPaths);

    class InputPath {

        public int start;
        public int end;

        public InputPath(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
