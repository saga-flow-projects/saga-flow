package com.demosoft.investiogation.neuronlan;

import com.demosoft.investiogation.neuronlan.entity.newgen.Input;
import com.demosoft.investiogation.neuronlan.entity.newgen.Neuron;
import com.demosoft.investiogation.neuronlan.entity.newgen.Output;

import java.util.List;

/**
 * Created by Andrii_Korkoshko on 05.12.2015.
 */
public interface Brain {

    List<Input> getInputs();


    List<Neuron> getNeurons();


    List<Output> getOutputs();

}
