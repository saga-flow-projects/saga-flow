package com.demosoft.investiogation.neuronlan.painter.entity;

import com.demosoft.investiogation.neuronlan.entity.newgen.Output;

import java.awt.*;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class PainterOutput {

    private Output output;

    private Point point;

    public PainterOutput(Output output, Point point) {
        this.output = output;
        this.point = point;
    }

    public PainterOutput(Output output) {
        this.output = output;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Output getOutput() {
        return output;
    }

    public Point getPoint() {
        return point;
    }
}
