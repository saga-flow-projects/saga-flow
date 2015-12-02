package com.demosoft.investiogation.neuronlan.entity.newgen;

import com.demosoft.investiogation.neuronlan.ObjectContainer;

/**
 * Created by Andrii_Korkoshko on 02.12.2015.
 */
public class Output {
    private String id;
    private Action action;

    public Output(String id, Action action) {
        this.id = id;
        this.action = action;
        ObjectContainer.outputMap.put(this.id, this);
    }

    public Output(com.demosoft.investiogation.neuronlan.xml.generated.network.Output parsedOutput) {
        this.id = parsedOutput.getId();
        Action fetchedAction = ObjectContainer.actionMap.get(parsedOutput.getActionId());
        if (fetchedAction == null) {
            throw new IllegalStateException("fetchedAction is null");
        }
        this.action = fetchedAction;
        ObjectContainer.outputMap.put(this.id, this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
