package com.demosoft.investiogation.neuronlan.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 30.11.2015.
 */
public class Neuron {
    public List<Link> incomingLinks = new ArrayList<>();

    public double power;

    public int action;
    public int layerIndex = 0;

    public Link[] getLinksArray() {
        return incomingLinks.toArray(new Link[incomingLinks.size()]);
    }

    public void setLinks(Link[] links) {
        incomingLinks.addAll(Arrays.asList(links));
    }

    public void createLink(Input input) {
        Link link = new Link(this, 1);
        incomingLinks.add(link);
        input.outgoingLinks.add(link);

    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public List<Link> getIncomingLinks() {
        return incomingLinks;
    }

    public void setIncomingLinks(List<Link> incomingLinks) {
        this.incomingLinks = incomingLinks;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getLayerIndex() {
        return layerIndex;
    }

    public void setLayerIndex(int layerIndex) {
        this.layerIndex = layerIndex;
    }
}
