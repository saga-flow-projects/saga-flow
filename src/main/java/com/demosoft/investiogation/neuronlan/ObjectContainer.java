package com.demosoft.investiogation.neuronlan;

import com.demosoft.investiogation.neuronlan.entity.newgen.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class ObjectContainer {

    public static Map<String, Neuron> neuronMap = new HashMap<>();
    public static Map<String, Link> linkMap = new HashMap<>();
    public static Map<String, Input> inputMap = new HashMap<>();
    public static Map<String, Output> outputMap = new HashMap<>();
    public static Map<String, Action> actionMap = new HashMap<>();
}
