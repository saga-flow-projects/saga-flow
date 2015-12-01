package com.demosoft.investiogation.neuronlan.entity;

/**
 * Created by Andrii_Korkoshko on 30.11.2015.
 */
public class Link {

    public Neuron neuron;
    public double weight;

    public Link(Neuron neuron, double weight) {
        this.neuron = neuron;
        this.weight = weight;
    }

    public Neuron getNeuron() {
        return neuron;
    }

    public void setNeuron(Neuron neuron) {
        this.neuron = neuron;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
