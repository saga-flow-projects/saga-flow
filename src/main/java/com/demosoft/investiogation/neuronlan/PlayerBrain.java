package com.demosoft.investiogation.neuronlan;

import com.demosoft.investiogation.neuronlan.entity.newgen.Input;
import com.demosoft.investiogation.neuronlan.entity.newgen.Neuron;
import com.demosoft.investiogation.neuronlan.entity.newgen.Output;

import java.util.List;

/**
 * Created by Andrii_Korkoshko on 01.12.2015.
 */
public class PlayerBrain {

    private List<Input> inputs;
    private List<Neuron> neurons;
    private List<Output> outputs;

    public PlayerBrain(List<Input> inputs, List<Neuron> neurons, List<Output> outputs) {
        this.inputs = inputs;
        this.neurons = neurons;
        this.outputs = outputs;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<Neuron> neurons) {
        this.neurons = neurons;
    }

    public List<Output> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Output> outputs) {
        this.outputs = outputs;
    }
}
