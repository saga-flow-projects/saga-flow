package com.demosoft.investiogation.neuronlan.entity.newgen;

import com.demosoft.investiogation.neuronlan.ObjectContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class Link {

    public enum LinkType {
        NEURON, OUTPUT
    }

    private Neuron neuron;
    private Output output;

    private LinkType linkType;

    public Link(Neuron neuron) {
        this.neuron = neuron;
        this.linkType = LinkType.NEURON;
    }

    public Link(Output output) {
        this.output = output;
        this.linkType = LinkType.OUTPUT;
    }

    public Link(com.demosoft.investiogation.neuronlan.xml.generated.network.Link parsedLink) {
        if (parsedLink.getNeuronId() != null && parsedLink.getNeuronId().length() > 0) {
            Neuron neuron = ObjectContainer.neuronMap.get(parsedLink.getNeuronId());
            if (neuron == null) {
                throw new IllegalStateException("neuron is not initialized id = " + parsedLink.getNeuronId());
            }
            this.neuron = neuron;
            this.linkType = LinkType.NEURON;
        } else if (parsedLink.getOutputId() != null && parsedLink.getOutputId().length() > 0) {
            Output output = ObjectContainer.outputMap.get(parsedLink.getOutputId());
            if (output == null) {
                throw new IllegalStateException("output is not initialized id = " + parsedLink.getOutputId());
            }
            this.output = output;
            this.linkType = LinkType.OUTPUT;
        }
    }

    public static List<Link> createLinks(List<com.demosoft.investiogation.neuronlan.xml.generated.network.Link> parsedLink) {
        List<Link> result = new ArrayList<>();
        for (com.demosoft.investiogation.neuronlan.xml.generated.network.Link link : parsedLink) {
            result.add(new Link(link));
        }
        return result;
    }


    public Neuron getNeuron() {
        return neuron;
    }

    public Output getOutput() {
        return output;
    }

    public LinkType getLinkType() {
        return linkType;
    }
}
