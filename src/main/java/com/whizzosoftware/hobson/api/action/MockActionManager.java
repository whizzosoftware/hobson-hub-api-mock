/*
 *******************************************************************************
 * Copyright (c) 2016 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************
*/
package com.whizzosoftware.hobson.api.action;

import com.whizzosoftware.hobson.api.action.job.AsyncJobHandle;
import com.whizzosoftware.hobson.api.action.job.JobInfo;
import com.whizzosoftware.hobson.api.device.DeviceContext;
import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.plugin.PluginContext;
import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.property.PropertyContainerClassContext;
import com.whizzosoftware.hobson.api.property.PropertyContainerSet;

import java.util.*;

public class MockActionManager implements ActionManager {
    private Map<String,PropertyContainerSet> actionSets = new HashMap<>();

    @Override
    public void addJobStatusMessage(PluginContext pctx, String msgName, Object o) {

    }

    @Override
    public AsyncJobHandle executeAction(PropertyContainer action) {
        return null;
    }

    @Override
    public AsyncJobHandle executeActionSet(PropertyContainerSet actionSet) {
        return null;
    }

    @Override
    public ActionClass getActionClass(PropertyContainerClassContext ctx) {
        return null;
    }

    @Override
    public Collection<ActionClass> getActionClasses(PluginContext ctx) {
        return null;
    }

    @Override
    public Collection<ActionClass> getActionClasses(HubContext ctx, boolean applyConstraints) {
        return null;
    }

    @Override
    public Collection<ActionClass> getActionClasses(DeviceContext ctx, boolean applyConstraints) {
        return null;
    }

    @Override
    public PropertyContainerSet getActionSet(HubContext ctx, String actionSetId) {
        return actionSets.get(actionSetId);
    }

    @Override
    public Collection<PropertyContainerSet> getActionSets(HubContext ctx) {
        return null;
    }

    @Override
    public JobInfo getJobInfo(HubContext ctx, String jobId) {
        return null;
    }

    @Override
    public boolean hasActionClass(PropertyContainerClassContext ctx) {
        return false;
    }

    @Override
    public void publishActionProvider(ActionProvider actionProvider) {
    }

    @Override
    public PropertyContainerSet publishActionSet(HubContext ctx, String name, List<PropertyContainer> actions) {
        String actionSetId = UUID.randomUUID().toString();
        PropertyContainerSet tas = new PropertyContainerSet(actionSetId, actions);
        actionSets.put(actionSetId, tas);
        return tas;
    }
}
