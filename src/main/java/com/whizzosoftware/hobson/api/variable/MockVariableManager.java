/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.variable;

import com.whizzosoftware.hobson.api.variable.telemetry.TelemetryInterval;
import com.whizzosoftware.hobson.api.variable.telemetry.TemporalValue;

import java.util.*;

public class MockVariableManager implements VariableManager {
    private MockVariablePublisher publisher;

    public MockVariableManager() {
        this(new MockVariablePublisher());
    }

    public MockVariableManager(MockVariablePublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public Collection<HobsonVariable> getGlobalVariables(String userId, String hubId) {
        return publisher.getPublishedGlobalVariables().values();
    }

    @Override
    public HobsonVariable getGlobalVariable(String userId, String hubId, String name) {
        return publisher.getPublishedGlobalVariables().get(name);
    }

    @Override
    public Collection<HobsonVariable> getDeviceVariables(String userId, String hubId, String pluginId, String deviceId) {
        Map<String,HobsonVariable> m = publisher.getPublishedDeviceVariables().get(pluginId + ":" + deviceId);
        return (m != null) ? m.values() : null;
    }

    @Override
    public Collection<String> getDeviceVariableChangeIds(String userId, String hubId, String pluginId, String deviceId) {
        return null;
    }

    @Override
    public HobsonVariable getDeviceVariable(String userId, String hubId, String pluginId, String deviceId, String name) {
        Map<String,HobsonVariable> m = publisher.getPublishedDeviceVariables().get(pluginId + ":" + deviceId);
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
    public VariablePublisher getPublisher() {
        return publisher;
    }
}
