/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.variable;

import com.whizzosoftware.hobson.api.plugin.HobsonPlugin;
import com.whizzosoftware.hobson.api.variable.telemetry.TelemetryInterval;
import com.whizzosoftware.hobson.api.variable.telemetry.TemporalValue;

import java.util.*;

public class MockVariableManager implements VariableManager {
    private final Map<String,HobsonVariable> publishedGlobalVariables = new HashMap<>();
    private final Map<String,Map<String,HobsonVariable>> publishedDeviceVariables = new HashMap<>();
    private final List<VariableUpdate> variableUpdates = new ArrayList<>();

    @Override
    public void publishGlobalVariable(String userId, String hubId, String pluginId, String name, Object value, HobsonVariable.Mask mask) {
        publishedGlobalVariables.put(name, new MockHobsonVariable(name, value, mask));
    }

    @Override
    public Collection<HobsonVariable> getGlobalVariables(String userId, String hubId) {
        return publishedGlobalVariables.values();
    }

    @Override
    public HobsonVariable getGlobalVariable(String userId, String hubId, String name) {
        return publishedGlobalVariables.get(name);
    }

    @Override
    public void unpublishGlobalVariable(String userId, String hubId, String pluginId, String name) {
        publishedGlobalVariables.remove(name);
    }

    @Override
    public void publishDeviceVariable(String userId, String hubId, String pluginId, String deviceId, String name, Object value, HobsonVariable.Mask mask) {
        Map<String,HobsonVariable> m = publishedDeviceVariables.get(pluginId + ":" + deviceId);
        if (m == null) {
            m = new HashMap<>();
            publishedDeviceVariables.put(pluginId + ":" + deviceId, m);
        }
        m.put(name, new MockHobsonVariable(name, value, mask));
    }

    @Override
    public void unpublishDeviceVariable(String userId, String hubId, String pluginId, String deviceId, String name) {
        Map<String,HobsonVariable> m = publishedDeviceVariables.get(pluginId + ":" + deviceId);
        if (m != null) {
            m.remove(name);
        }
    }

    @Override
    public void unpublishAllDeviceVariables(String userId, String hubId, String pluginId, String deviceId) {
        publishedDeviceVariables.remove(pluginId + ":" + deviceId);
    }

    @Override
    public void unpublishAllPluginVariables(String userId, String hubId, String pluginId) {

    }

    @Override
    public Collection<HobsonVariable> getDeviceVariables(String userId, String hubId, String pluginId, String deviceId) {
        Map<String,HobsonVariable> m = publishedDeviceVariables.get(pluginId + ":" + deviceId);
        return (m != null) ? m.values() : null;
    }

    @Override
    public Collection<String> getDeviceVariableChangeIds(String userId, String hubId, String pluginId, String deviceId) {
        return null;
    }

    @Override
    public HobsonVariable getDeviceVariable(String userId, String hubId, String pluginId, String deviceId, String name) {
        Map<String,HobsonVariable> m = publishedDeviceVariables.get(pluginId + ":" + deviceId);
        return (m != null) ? m.get(name) : null;
    }

    @Override
    public boolean hasDeviceVariable(String userId, String hubId, String pluginId, String deviceId, String name) {
        return false;
    }

    @Override
    public Long setDeviceVariable(String userId, String hubId, String pluginId, String deviceId, String name, Object value) {
        return null;
    }

    @Override
    public void writeDeviceVariableTelemetry(String userId, String hubId, String pluginId, String deviceId, String name, Object value, long time) {

    }

    @Override
    public Collection<TemporalValue> getDeviceVariableTelemetry(String userId, String hubId, String pluginId, String deviceId, String name, long endTime, TelemetryInterval interval) {
        return null;
    }

    @Override
    public void fireVariableUpdateNotification(String userId, String hubId, HobsonPlugin plugin, VariableUpdate update) {
        variableUpdates.add(update);
        String key = update.getPluginId() + ":" + update.getDeviceId();
        Map<String,HobsonVariable> m = publishedDeviceVariables.get(key);
        if (m != null && m.containsKey(update.getName())) {
            ((MockHobsonVariable)m.get(update.getName())).setValue(update.getValue());
        }
    }

    @Override
    public void fireVariableUpdateNotifications(String userId, String hubId, HobsonPlugin plugin, List<VariableUpdate> updates) {
        for (VariableUpdate update : updates) {
            fireVariableUpdateNotification(userId, hubId, plugin, update);
        }
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
