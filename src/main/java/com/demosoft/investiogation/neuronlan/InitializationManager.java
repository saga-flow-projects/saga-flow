package com.demosoft.investiogation.neuronlan;

import com.demosoft.investiogation.neuronlan.entity.newgen.Input;
import com.demosoft.investiogation.neuronlan.entity.newgen.Neuron;
import com.demosoft.investiogation.neuronlan.entity.newgen.Output;
import com.demosoft.investiogation.neuronlan.xml.JAXB;
import com.demosoft.investiogation.neuronlan.xml.generated.network.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class InitializationManager {


    public PlayerBrain initPlayerBrain() throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        PlayerBrain playerBrain = null;
        Properties configs = ConfigProvider.getConfigs();
        JAXB jaxb = new JAXB(configs.getProperty(ConfigProvider.NETWORK_CONFIG_FILE));
        Network parsedNetwork = jaxb.getStructure();
        List<Output> outputs = generateOutputs(parsedNetwork);
        List<Neuron> neurons = generateNeurons(parsedNetwork);

        playerBrain = new PlayerBrain(generateInputs(parsedNetwork), neurons, outputs);
        return playerBrain;
    }

    public static List<Input> generateInputs(Network parsedNetwork) {
        List<Input> result = new ArrayList<>();
        List<com.demosoft.investiogation.neuronlan.xml.generated.network.Input> pasedInputs = parsedNetwork.getInput();
        for (com.demosoft.investiogation.neuronlan.xml.generated.network.Input parsedInput : pasedInputs) {
            result.add(new Input(parsedInput));
        }
        return result;
    }

    public static List<Neuron> generateNeurons(Network parsedNetwork) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        List<Neuron> result = new ArrayList<>();
        List<com.demosoft.investiogation.neuronlan.xml.generated.network.Neuron> parsedNeurons = parsedNetwork.getNeuron();
        for (com.demosoft.investiogation.neuronlan.xml.generated.network.Neuron parsedNeuron : parsedNeurons) {
            result.add(new Neuron(parsedNeuron));
        }
        return result;
    }

    public static List<Output> generateOutputs(Network parsedNetwork) {
        generateActions(parsedNetwork);
        List<Output> result = new ArrayList<>();
        List<com.demosoft.investiogation.neuronlan.xml.generated.network.OutputGroup> parsedOutPutGroups = parsedNetwork.getOutputGroup();
        for (com.demosoft.investiogation.neuronlan.xml.generated.network.OutputGroup parsedOutputGroup : parsedOutPutGroups) {
            result.addAll(parsedOutputGroup.getOutput().stream().map(Output::new).collect(Collectors.toList()));
        }
        return result;
    }

    public static void generateActions(Network parsedNetwork) {
        parsedNetwork.getAction().forEach(com.demosoft.investiogation.neuronlan.entity.newgen.Action::new);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        new InitializationManager().initPlayerBrain();
    }
}
