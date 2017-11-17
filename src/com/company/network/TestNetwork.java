package com.company.network;

import com.company.Vertex;
import com.company.network.DirectedNetwork;
import com.company.network.Network;

import java.util.List;

public class TestNetwork {

    public static Network testNetwork() {

        Network network = new DirectedNetwork();

        //WEZLY = 5
        Vertex[] vertices = new Vertex[5];

        //kazdy wezel to trojka (identyfikator, wspolrzedna X, wspolrzedna Y)
        //np. 1 10 50
        vertices[0] = new Vertex(1, 10, 50);
        vertices[1] = new Vertex(2, 30, 56);
        vertices[2] = new Vertex(3, 45, 32);
        vertices[3] = new Vertex(4, 90, 23);
        vertices[4] = new Vertex(5, 44, 33);

        for(int i = 0; i < vertices.length; i++){
            network.addVertex(vertices[i], true);
        }

        //LACZA = 10
        //kazde lacze to trojka (identyfikator, wezel poczatkowy, wezel koncowy)
        //np. 1 1 2
        network.addLink(1, vertices[0], vertices[1]);
        network.addLink(2, vertices[1], vertices[2]);
        network.addLink(3, vertices[2], vertices[3]);

        network.addLink(4, vertices[3], vertices[4]);
        network.addLink(5, vertices[4], vertices[0]);
        network.addLink(6, vertices[0], vertices[2]);

        network.addLink(7, vertices[0], vertices[3]);
        network.addLink(8, vertices[1], vertices[2]);
        network.addLink(9, vertices[1], vertices[3]);

        network.addLink(10, vertices[1], vertices[4]);

        return network;
    }

    public static Network randomNetwork() {

        Network network = new DirectedNetwork();

        Vertex[] vertices = new Vertex[5];
        for(int i = 0; i < vertices.length; i++){
            vertices[i] = new Vertex(i);
            network.addVertex(vertices[i], true);
        }

        for(int i = 0; i < vertices.length - 1; i++){
            for(int j = i + 1; j < vertices.length; j++){
                network.addLink(vertices[i], vertices[j]);
                network.addLink(vertices[i], vertices[j]);
                network.addLink(vertices[j], vertices[i]);
            }
        }

        return network;

    }

    public static void displayNetworkSetup(Vertex[] vertices) {

        for(int i = 0; i < vertices.length; i++){
            System.out.println(vertices[i]);

            for(int j = 0; j < vertices[i].getNumberOfLinks(); j++){
                System.out.println(vertices[i].getLink(j));
            }

            System.out.println();
        }

    }

    public static void displayNetworkSetup(List<Vertex> vertices) {

        for(int i = 0; i < vertices.size(); i++){
            System.out.println(vertices.get(i));

            for(int j = 0; j < vertices.get(i).getNumberOfLinks(); j++){
                System.out.println(vertices.get(i).getLink(j));
            }

            System.out.println();
        }

    }


}
