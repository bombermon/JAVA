package org.example;


public class Customer  {

    private String name;
    private int taskQty;

    public Customer(String name, int taskQty) {
        this.name = name;
        this.taskQty = taskQty;
    }

    public String getName() {
        return name;
    }

    public int getTaskQty() {
        return taskQty;
    }


    @Override
    public String toString() {
        return getName();
    }
}
