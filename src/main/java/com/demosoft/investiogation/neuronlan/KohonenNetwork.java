package com.demosoft.investiogation.neuronlan;

import java.util.List;

/**
 * Created by Andrii_Korkoshko on 30.11.2015.
 */
public class KohonenNetwork {
    public Input[] inputs;
    public Neuron[] neurons;

    public KohonenNetwork(Input[] inputs, Neuron[] neurons) {
        this.inputs = inputs;
        this.neurons = neurons;
    }

    public KohonenNetwork() {
    }

    public int handle(int[] input) {
        for (int i = 0; i < inputs.length; i++) {
            Input inputNeuron = inputs[i];
            for (Link outgoingLink : inputNeuron.outgoingLinks) {
                outgoingLink.neuron.power += outgoingLink.weight * input[i];
            }
        }
        int maxIndex = 0;
        for (int i = 1; i < neurons.length; i++) {
            if (neurons[i].power > neurons[maxIndex].power)
                maxIndex = i;
        }
        for (Neuron outputNeuron : neurons) {
            outputNeuron.power = 0;
        }
        return maxIndex;
    }

    public int newHandle(PlayerState input) {

        Input inputNeuron = inputs[0];
        for (Link outgoingLink : inputNeuron.outgoingLinks) {
            outgoingLink.neuron.power += outgoingLink.weight * input.getHealth();
        }

        inputNeuron = inputs[1];
        for (Link outgoingLink : inputNeuron.outgoingLinks) {
            outgoingLink.neuron.power += outgoingLink.weight * input.getGun();
        }

        inputNeuron = inputs[2];
        for (Link outgoingLink : inputNeuron.outgoingLinks) {
            outgoingLink.neuron.power += outgoingLink.weight * input.getEnemies();
        }


        int maxIndex = 0;
        for (int i = 1; i < neurons.length; i++) {
            if (neurons[i].power > neurons[maxIndex].power)
                maxIndex = i;
        }
        for (Neuron outputNeuron : neurons) {
            outputNeuron.power = 0;
        }
        return maxIndex;
    }

    public void study(int[] input, int correctAnswer) {
        Neuron neuron = neurons[correctAnswer];
        for (int i = 0; i < neuron.incomingLinks.size(); i++) {
            Link incomingLink = neuron.incomingLinks.get(i);
            incomingLink.weight = incomingLink.weight + 0.5 * (input[i] - incomingLink.weight);
        }
    }

    public void newStudy(PlayerState input, int correctAnswer) {
        Neuron neuron = neurons[correctAnswer];
        Link incomingLink = neuron.incomingLinks.get(0);
        neuron.incomingLinks.get(0).weight = incomingLink.weight + 0.95 * (input.getHealth() - incomingLink.weight);

        incomingLink = neuron.incomingLinks.get(1);
        neuron.incomingLinks.get(1).weight = incomingLink.weight + 0.95 * (input.isGun() ? 1 : 0 - incomingLink.weight);

        incomingLink = neuron.incomingLinks.get(2);
        neuron.incomingLinks.get(2).weight = incomingLink.weight + 0.95 * (input.getEnemies() - incomingLink.weight);
    }

    public void bulkNewStudy(List<PlayerState> input, int correctAnswer) {
        for (PlayerState playerState : input) {
            newStudy(playerState, correctAnswer);
        }
    }


    public Input[] getInputs() {
        return inputs;
    }

    public Neuron[] getNeurons() {
        return neurons;
    }
}
