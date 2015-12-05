package com.demosoft.investiogation.neuronlan.painter.entity;

import com.demosoft.investiogation.neuronlan.entity.newgen.Input;

import java.awt.*;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class PainterInput {
    private Input input;
    private Point point;

    public PainterInput(Input input) {
        this.input = input;
    }

    public PainterInput(Input input, Point point) {
        this.input = input;
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Input getInput() {
        return input;
    }
}
