package com.demosoft.investiogation.neuronlan.entity.newgen;

import com.demosoft.investiogation.neuronlan.ObjectContainer;
import com.demosoft.investiogation.neuronlan.processor.NeuronProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class Neuron {

    private String id;
    private NeuronProcessor processor;
    private List<Link> outgoingLinks = new ArrayList<>();
    private double power;

    public Neuron(String id, NeuronProcessor processor, List<Link> outgoingLinks, double power) {
        this.id = id;
        this.processor = processor;
        this.outgoingLinks = outgoingLinks;
        this.power = power;
        ObjectContainer.neuronMap.put(id, this);
    }

    public Neuron(com.demosoft.investiogation.neuronlan.xml.generated.network.Neuron parsedNeuron) throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        this.id = parsedNeuron.getId();
        this.power = 0;
        processor = NeuronProcessor.createNeuronProcessorByParsedData(parsedNeuron.getProcessor());
        this.outgoingLinks = Link.createLinks(parsedNeuron.getOutgoingLinks());
        ObjectContainer.neuronMap.put(id, this);

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NeuronProcessor getProcessor() {
        return processor;
    }

    public void setProcessor(NeuronProcessor processor) {
        this.processor = processor;
    }

    public List<Link> getOutgoingLinks() {
        return outgoingLinks;
    }

    public void setOutgoingLinks(List<Link> outgoingLinks) {
        this.outgoingLinks = outgoingLinks;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }
}
