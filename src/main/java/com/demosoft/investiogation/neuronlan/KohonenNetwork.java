package com.demosoft.investiogation.neuronlan;

import com.demosoft.investiogation.neuronlan.entity.Input;
import com.demosoft.investiogation.neuronlan.entity.Link;
import com.demosoft.investiogation.neuronlan.entity.Neuron;
import com.demosoft.investiogation.neuronlan.entity.PlayerStateRule;

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

    public int newHandle(PlayerStateRule input) {

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

        inputNeuron = inputs[2];
        for (Link outgoingLink : inputNeuron.outgoingLinks) {
            outgoingLink.neuron.power += outgoingLink.weight * input.getArmor();
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

    public void newStudy(PlayerStateRule input, int correctAnswer) {
        Neuron neuron = neurons[correctAnswer];
        Link incomingLink = neuron.incomingLinks.get(0);
        neuron.incomingLinks.get(0).weight = incomingLink.weight + 0.9 * (input.getHealth() - incomingLink.weight);

        incomingLink = neuron.incomingLinks.get(1);
        neuron.incomingLinks.get(1).weight = incomingLink.weight + 0.9 * (input.isGun() ? 1 : 0 - incomingLink.weight);

        incomingLink = neuron.incomingLinks.get(2);
        neuron.incomingLinks.get(2).weight = incomingLink.weight + 0.9 * (input.getEnemies() - incomingLink.weight);

        incomingLink = neuron.incomingLinks.get(3);
        neuron.incomingLinks.get(3).weight = incomingLink.weight + 0.9 * (input.getArmor() - incomingLink.weight);
    }

    public void study(PlayerStateRule input) {
        Neuron neuron = neurons[input.getAction().getCode()];
        Link incomingLink = neuron.incomingLinks.get(0);
        neuron.incomingLinks.get(0).weight = incomingLink.weight + 0.9 * (input.getHealth() - incomingLink.weight);

        incomingLink = neuron.incomingLinks.get(1);
        neuron.incomingLinks.get(1).weight = incomingLink.weight + 0.9 * (input.isGun() ? 1 : 0 - incomingLink.weight);

        incomingLink = neuron.incomingLinks.get(2);
        neuron.incomingLinks.get(2).weight = incomingLink.weight + 0.9 * (input.getEnemies() - incomingLink.weight);

        incomingLink = neuron.incomingLinks.get(3);
        neuron.incomingLinks.get(3).weight = incomingLink.weight + 0.9 * (input.getArmor() - incomingLink.weight);
    }

    public void bulkNewStudy(List<PlayerStateRule> input, int correctAnswer) {
        for (PlayerStateRule playerState : input) {
            newStudy(playerState, correctAnswer);
        }
    }

    public void bulkStudy(List<PlayerStateRule> input) {
        for (PlayerStateRule playerState : input) {
            study(playerState);
        }
    }


    public Input[] getInputs() {
        return inputs;
    }

    public Neuron[] getNeurons() {
        return neurons;
    }
}
