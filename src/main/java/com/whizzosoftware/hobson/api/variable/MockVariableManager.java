/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.variable;

import com.whizzosoftware.hobson.api.device.DeviceContext;
import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.plugin.PluginContext;

import java.util.*;

public class MockVariableManager implements VariableManager {
    private final Map<String,HobsonVariable> publishedGlobalVariables = new HashMap<>();
    private final Map<VariableContext,HobsonVariable> publishedDeviceVariables = new HashMap<>();
    private final List<VariableUpdate> variableUpdates = new ArrayList<>();

    @Override
    public Collection<HobsonVariable> getAllVariables(HubContext ctx) {
        List<HobsonVariable> results = new ArrayList<>();
        results.addAll(publishedGlobalVariables.values());
        for (HobsonVariable v : publishedDeviceVariables.values()) {
            results.add(v);
        }
        return results;
    }

    @Override
    public Collection<String> getPublishedVariableNames(HubContext ctx) {
        return null;
    }

    @Override
    public Collection<HobsonVariable> getGlobalVariables(HubContext ctx) {
        return getPublishedGlobalVariables().values();
    }

    @Override
    public Collection<HobsonVariable> getDeviceVariables(DeviceContext ctx) {
        List<HobsonVariable> m = null;
        for (VariableContext dvc : publishedDeviceVariables.keySet()) {
            if (dvc.getDeviceContext().equals(ctx)) {
                if (m == null) {
                    m = new ArrayList<>();
                }
                m.add(publishedDeviceVariables.get(dvc));
            }
        }
        return m;
    }

    @Override
    public HobsonVariable getVariable(VariableContext ctx) {
        return publishedDeviceVariables.get(ctx);
    }

    @Override
    public boolean hasVariable(VariableContext ctx) {
        return publishedDeviceVariables.containsKey(ctx);
    }

    @Override
    public void publishVariable(VariableContext ctx, Object value, HobsonVariable.Mask mask) {
        publishVariable(ctx, value, mask, null);
    }

    @Override
    public void publishVariable(VariableContext ctx, Object value, HobsonVariable.Mask mask, VariableMediaType mediaType) {
        publishVariable(ctx, value, mask, mediaType, System.currentTimeMillis());
    }

    public void publishVariable(VariableContext ctx, Object value, HobsonVariable.Mask mask, VariableMediaType mediaType, Long lastUpdate) {
        MutableHobsonVariable mhv = new MutableHobsonVariable(ctx, mask, value, mediaType);
        mhv.setLastUpdate(lastUpdate);
        publishedDeviceVariables.put(ctx, mhv);
    }

    @Override
    public void unpublishVariable(VariableContext ctx) {
        publishedDeviceVariables.remove(ctx);
    }

    @Override
    public void unpublishAllVariables(DeviceContext ctx) {
        for (Iterator<VariableContext> it = publishedDeviceVariables.keySet().iterator(); it.hasNext(); ) {
            VariableContext dvc = it.next();
            if (dvc.getDeviceContext().equals(ctx)) {
                it.remove();
            }
        }
    }

    @Override
    public void unpublishAllVariables(PluginContext ctx) {

    }

    @Override
    public Long setVariable(VariableContext ctx, Object value) {
        return null;
    }

    @Override
    public Map<String, Long> setDeviceVariables(DeviceContext ctx, Map<String, Object> values) {
        return null;
    }

    @Override
    public void fireVariableUpdateNotifications(HubContext ctx, List<VariableUpdate> updates) {
        variableUpdates.addAll(updates);
    }

    public Map<String,HobsonVariable> getPublishedGlobalVariables() {
        return publishedGlobalVariables;
    }

    public Collection<HobsonVariable> getPublishedDeviceVariables() {
        return publishedDeviceVariables.values();
    }

    public HobsonVariable getPublishedDeviceVariable(DeviceContext ctx, String name) {
        return publishedDeviceVariables.get(VariableContext.create(ctx, name));
    }

    public void clearPublishedDeviceVariables() {
        publishedDeviceVariables.clear();
    }

    public List<VariableUpdate> getVariableUpdates() {
        return variableUpdates;
    }

    public void clearVariableUpdates() {
        variableUpdates.clear();
    }
}
