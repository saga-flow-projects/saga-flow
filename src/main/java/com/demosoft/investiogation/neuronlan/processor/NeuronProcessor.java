package com.demosoft.investiogation.neuronlan.processor;

import com.demosoft.investiogation.neuronlan.xml.generated.network.Parameter;
import com.demosoft.investiogation.neuronlan.xml.generated.network.Processor;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Andrii_Korkoshko on 01.12.2015.
 */
public abstract class NeuronProcessor {
    public abstract double processPower(double weight);

    public abstract double processLinkWeight(double weight);

    public abstract void receiveParams(List<Parameter> parameters);

    public NeuronProcessor() {
    }

    public static NeuronProcessor createNeuronProcessorByParsedData(Processor parsedProcessor) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        String className = parsedProcessor.getClassName();
        if (className == null && className.length() == 0) {
            throw new IllegalStateException("className is empty");
        }
        NeuronProcessor neuronProcessor = createInstanceNeuronProcessor(className);
        List<Parameter> params = parsedProcessor.getParam();
        setParamsToProcessor(neuronProcessor,params);
        return null;
    }

    public static NeuronProcessor createInstanceNeuronProcessor(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object newInstance = Class.forName(className).newInstance();
        if (!(newInstance instanceof NeuronProcessor)) {
            throw new IllegalArgumentException("Class is not child of NeuronProcessor");
        }
        return (NeuronProcessor) newInstance;
    }

    public static void setParamsToProcessor(NeuronProcessor neuronProcessor, List<Parameter> parsedParams) throws NoSuchFieldException, IllegalAccessException {
        for (Parameter parameter : parsedParams) {
            neuronProcessor.receiveParams(parsedParams);
        }
    }

}
