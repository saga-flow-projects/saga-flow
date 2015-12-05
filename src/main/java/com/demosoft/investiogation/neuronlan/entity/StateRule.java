package com.demosoft.investiogation.neuronlan.entity;

import com.demosoft.investiogation.neuronlan.entity.newgen.Action;

import java.util.List;

/**
 * Created by Andrii_Korkoshko on 05.12.2015.
 */
public interface StateRule {

    Action getAction();

    Object getValue(String key);

    List<String> getKeys();

}
