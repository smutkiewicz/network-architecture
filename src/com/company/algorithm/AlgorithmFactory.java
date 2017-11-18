package com.company.algorithm;

public class AlgorithmFactory {

    public MyAlgorithm produceAlgorithm(String type) {

        MyAlgorithm algorithm = null;

        if(type.matches("MST"))
            algorithm = new MSTAlgorithm();
        /*else if(type == "FLOYD")
            algorithm = new FloydAlgorithm();*/
        else if(type.matches("SCIEZKA"))
            algorithm = new DijkstraAlgorithm();

        return algorithm;
    }

}
