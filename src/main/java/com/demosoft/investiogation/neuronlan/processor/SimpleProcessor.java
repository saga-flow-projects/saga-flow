package com.demosoft.investiogation.neuronlan.processor;

/**
 * Created by Andrii_Korkoshko on 01.12.2015.
 */
public class SimpleProcessor implements NeuronProcessor {

    private double value;

    public SimpleProcessor(double value) {
        this.value = value;
    }

    @Override
    public double process() {
        return 0;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
