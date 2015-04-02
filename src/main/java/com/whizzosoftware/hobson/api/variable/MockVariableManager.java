/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.variable;

import com.whizzosoftware.hobson.api.device.DeviceContext;
import com.whizzosoftware.hobson.api.plugin.PluginContext;

import java.util.*;

public class MockVariableManager implements VariableManager {
    private final Map<String,HobsonVariable> publishedGlobalVariables = new HashMap<>();
    private final Map<String,Map<String,HobsonVariable>> publishedDeviceVariables = new HashMap<>();
    private final List<VariableUpdate> variableUpdates = new ArrayList<>();

    @Override
    public Collection<HobsonVariable> getAllVariables(String userId, String hubId) {
        return null;
    }

    @Override
    public Collection<HobsonVariable> getGlobalVariables(String userId, String hubId) {
        return getPublishedGlobalVariables().values();
    }

    @Override
    public HobsonVariable getGlobalVariable(String userId, String hubId, String name) {
        return getPublishedGlobalVariables().get(name);
    }

    @Override
    public Collection<HobsonVariable> getDeviceVariables(String userId, String hubId, String pluginId, String deviceId) {
        Map<String,HobsonVariable> m = getPublishedDeviceVariables().get(pluginId + ":" + deviceId);
        return (m != null) ? m.values() : null;
    }

    @Override
    public Collection<String> getDeviceVariableChangeIds(String userId, String hubId, String pluginId, String deviceId) {
        return null;
    }

    @Override
    public HobsonVariable getDeviceVariable(String userId, String hubId, String pluginId, String deviceId, String name) {
        Map<String,HobsonVariable> m = getPublishedDeviceVariables().get(pluginId + ":" + deviceId);
        return (m != null) ? m.get(name) : null;
    }

    @Override
    public boolean hasDeviceVariable(String userId, String hubId, String pluginId, String deviceId, String name) {
        return false;
    }

    @Override
    public void publishGlobalVariable(PluginContext ctx, String name, Object value, HobsonVariable.Mask mask) {
        publishedGlobalVariables.put(name, new MockHobsonVariable(ctx.getPluginId(), name, value, mask));
    }

    @Override
    public void unpublishGlobalVariable(PluginContext ctx, String name) {
        publishedGlobalVariables.remove(name);
    }

    @Override
    public void publishDeviceVariable(DeviceContext ctx, String name, Object value, HobsonVariable.Mask mask) {
        Map<String,HobsonVariable> m = publishedDeviceVariables.get(ctx.getPluginId() + ":" + ctx.getDeviceId());
        if (m == null) {
            m = new HashMap<>();
            publishedDeviceVariables.put(ctx.getPluginId() + ":" + ctx.getDeviceId(), m);
        }
        m.put(name, new MockHobsonVariable(ctx.getPluginId(), name, value, mask));
    }

    @Override
    public void unpublishDeviceVariable(DeviceContext ctx, String name) {
        Map<String,HobsonVariable> m = publishedDeviceVariables.get(ctx.getPluginId() + ":" + ctx.getDeviceId());
        if (m != null) {
            m.remove(name);
        }
    }

    @Override
    public void unpublishAllDeviceVariables(DeviceContext ctx) {
        publishedDeviceVariables.remove(ctx.getPluginId() + ":" + ctx.getDeviceId());
    }

    @Override
    public void unpublishAllPluginVariables(PluginContext ctx) {

    }

    @Override
    public Long setGlobalVariable(PluginContext ctx, String name, Object value) {
        return null;
    }

    @Override
    public Map<String, Long> setGlobalVariables(PluginContext ctx, Map<String, Object> values) {
        return null;
    }

    @Override
    public Long setDeviceVariable(DeviceContext ctx, String name, Object value) {
        return null;
    }

    @Override
    public Map<String, Long> setDeviceVariables(DeviceContext ctx, Map<String, Object> values) {
        return null;
    }

    @Override
    public void confirmVariableUpdates(String userId, String hubId, List<VariableUpdate> updates) {

    }

    public Map<String,HobsonVariable> getPublishedGlobalVariables() {
        return publishedGlobalVariables;
    }

    public Map<String,Map<String,HobsonVariable>> getPublishedDeviceVariables() {
        return publishedDeviceVariables;
    }

    public Map<String,HobsonVariable> getPublishedDeviceVariables(String pluginId, String deviceId) {
        return publishedDeviceVariables.get(pluginId + ":" + deviceId);
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
