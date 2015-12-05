package com.demosoft.investiogation.neuronlan.painter.entity;

import com.demosoft.investiogation.neuronlan.entity.newgen.Neuron;

import java.awt.*;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class PainterNeuron {

    private Neuron neuron;
    private Point point;

    public PainterNeuron(Neuron neuron, Point point) {
        this.neuron = neuron;
        this.point = point;
    }

    public PainterNeuron(Neuron neuron) {
        this.neuron = neuron;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Neuron getNeuron() {
        return neuron;
    }

    public Point getPoint() {
        return point;
    }
}
