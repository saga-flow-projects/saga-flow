package com.demosoft.investiogation.neuronlan;

import com.demosoft.investiogation.neuronlan.entity.StateRule;

import java.util.List;

/**
 * Created by Andrii_Korkoshko on 05.12.2015.
 */
public interface Teacher {

    void study(Brain brain, List<StateRule> stateRules);
}
