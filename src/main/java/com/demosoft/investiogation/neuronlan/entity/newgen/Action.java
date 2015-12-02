package com.demosoft.investiogation.neuronlan.entity.newgen;

import com.demosoft.investiogation.neuronlan.ObjectContainer;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class Action {
    private String id;
    private String name;


    public Action(String id, String name) {
        this.id = id;
        this.name = name;
        ObjectContainer.actionMap.put(id, this);
    }

    public Action(com.demosoft.investiogation.neuronlan.xml.generated.network.Action parsedAction) {
        this.id = parsedAction.getId();
        this.name = parsedAction.getName();
        ObjectContainer.actionMap.put(id, this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
