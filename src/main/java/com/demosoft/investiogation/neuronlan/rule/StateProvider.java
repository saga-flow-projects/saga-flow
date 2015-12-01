package com.demosoft.investiogation.neuronlan.rule;

import com.demosoft.investiogation.neuronlan.entity.PlayerStateRule;

import java.util.List;

/**
 * Created by Andrii_Korkoshko on 01.12.2015.
 */
public interface StateProvider {


    List<PlayerStateRule> getRules();


}
