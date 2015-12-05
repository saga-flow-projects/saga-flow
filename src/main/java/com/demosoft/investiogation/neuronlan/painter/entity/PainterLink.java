package com.demosoft.investiogation.neuronlan.painter.entity;

import com.demosoft.investiogation.neuronlan.entity.newgen.Link;

import java.awt.*;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class PainterLink {

    private Link link;
    private Point point;

    public PainterLink(Link link) {
        this.link = link;
    }

    public PainterLink(Link link, Point point) {
        this.link = link;
        this.point = point;
    }

    public Link getLink() {
        return link;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
