package com.demosoft.investiogation.neuronlan.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 30.11.2015.
 */
public class Input {
    public List<Link> outgoingLinks = new ArrayList<>();

    public Link[] getLinksArray() {
        return outgoingLinks.toArray(new Link[outgoingLinks.size()]);
    }

    public void setLinks(Link[] links) {
        outgoingLinks.addAll(Arrays.asList(links));
    }
}
