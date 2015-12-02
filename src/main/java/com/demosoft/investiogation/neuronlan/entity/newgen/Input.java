package com.demosoft.investiogation.neuronlan.entity.newgen;

import com.demosoft.investiogation.neuronlan.ObjectContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class Input {
    private String id;
    private List<Link> outgoingLinks = new ArrayList<>();

    public Input(String id, List<Link> outgoingLinks) {
        this.id = id;
        this.outgoingLinks = outgoingLinks;
        ObjectContainer.inputMap.put(id, this);
    }

    public Input(com.demosoft.investiogation.neuronlan.xml.generated.network.Input parsedInput) {
        this.id = parsedInput.getId();
        this.outgoingLinks = Link.createLinks(parsedInput.getOutgoingLinks());
        ObjectContainer.inputMap.put(id, this);
    }

    public List<Link> getOutgoingLinks() {
        return outgoingLinks;
    }

    public void setOutgoingLinks(List<Link> outgoingLinks) {
        this.outgoingLinks = outgoingLinks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
