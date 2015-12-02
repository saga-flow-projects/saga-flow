package com.demosoft.investiogation.neuronlan.processor;

import com.demosoft.investiogation.neuronlan.xml.generated.network.Parameter;

import java.util.List;

/**
 * Created by Andrii_Korkoshko on 01.12.2015.
 */
public class SimpleProcessor extends NeuronProcessor {

    private double value;

    private double beta = 1;

    public SimpleProcessor() {
    }

    public SimpleProcessor(double value, double beta) {
        this.value = value;
        this.beta = beta;
    }

    @Override
    public double processPower(double weight) {
        return weight * value;
    }

    @Override
    public double processLinkWeight(double weight) {
        return weight + beta * (value - weight);
    }

    @Override
    public void receiveParams(List<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            switch (parameter.getName()) {
                case "value":
                    setValue(Double.parseDouble(parameter.getValue()));
                    break;
                case "beta":
                    setValue(Double.parseDouble(parameter.getValue()));
                    break;
            }
        }

    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }
}
