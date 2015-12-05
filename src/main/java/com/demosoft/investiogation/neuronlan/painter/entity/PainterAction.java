package com.demosoft.investiogation.neuronlan.painter.entity;

import com.demosoft.investiogation.neuronlan.entity.newgen.Action;

import java.awt.*;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class PainterAction {
    private Action action;
    private Point point;

    public PainterAction(Action action) {
        this.action = action;
    }

    public PainterAction(Action action, Point point) {
        this.action = action;
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Action getAction() {
        return action;
    }
}
