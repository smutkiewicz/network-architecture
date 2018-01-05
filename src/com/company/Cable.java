package com.company;

public class Cable {

    private int id;
    private int capacity;
    private int cost;

    public Cable(int id, int capacity, int cost) {
        this.id = id;
        this.capacity = capacity;
        this.cost = cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCost() {
        return cost;
    }

    public int getId() {
        return id;
    }
}
