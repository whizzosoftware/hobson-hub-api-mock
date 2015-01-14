/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.config.Configuration;
import com.whizzosoftware.hobson.api.variable.telemetry.TelemetryInterval;
import com.whizzosoftware.hobson.api.variable.telemetry.TemporalValue;

import java.util.*;

public class MockDeviceManager implements DeviceManager {
    public final Map<String,Map<String,Object>> deviceConfigProps = new HashMap<>();

    private MockDevicePublisher publisher;

    public MockDeviceManager() {
        this(new MockDevicePublisher());
    }

    public MockDeviceManager(MockDevicePublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void enableDeviceTelemetry(String userId, String hubId, String pluginId, String deviceId, boolean enabled) {
    }

    @Override
    public Collection<HobsonDevice> getAllDevices(String userId, String hubId) {
        return publisher.getPublishedDevices();
    }

    @Override
    public Collection<HobsonDevice> getAllPluginDevices(String userId, String hubId, String pluginId) {
        return publisher.getPublishedDevices(pluginId);
    }

    @Override
    public Collection<HobsonDevice> getAllTelemetryEnabledDevices(String userId, String hubId) {
        return null;
    }

    @Override
    public HobsonDevice getDevice(String userId, String hubId, String pluginId, String deviceId) {
        return publisher.getPublishedDevice(pluginId, deviceId);
    }

    @Override
    public Configuration getDeviceConfiguration(String userId, String hubId, String pluginId, String deviceId) {
        return null;
    }

    @Override
    public Object getDeviceConfigurationProperty(String userId, String hubId, String pluginId, String deviceId, String name) {
        Object value = null;
        Map<String,Object> map = deviceConfigProps.get(createId(pluginId, deviceId));
        if (map != null) {
            value = map.get(name);
        }
        return value;
    }

    @Override
    public DevicePublisher getPublisher() {
        return publisher;
    }

    @Override
    public Map<String,Collection<TemporalValue>> getDeviceTelemetry(String userId, String hubId, String pluginId, String deviceId, long endTime, TelemetryInterval interval) {
        return null;
    }

    @Override
    public boolean hasDevice(String userId, String hubId, String pluginId, String deviceId) {
        return false;
    }

    @Override
    public boolean isDeviceTelemetryEnabled(String userId, String hubId, String pluginId, String deviceId) {
        return false;
    }

    @Override
    public void setDeviceConfigurationProperty(String userId, String hubId, String pluginId, String deviceId, String name, Object value, boolean overwrite) {
        Map<String,Object> map = deviceConfigProps.get(createId(pluginId, deviceId));
        if (map == null) {
            map = new HashMap<>();
            deviceConfigProps.put(createId(pluginId, deviceId), map);
        }
        if (!map.containsKey(name) || overwrite) {
            map.put(name, value);
        }
    }

    @Override
    public void setDeviceName(String userId, String hubId, String pluginId, String deviceId, String name) {
    }

    @Override
    public void writeDeviceTelemetry(String userId, String hubId, String pluginId, String deviceId, Map<String,TemporalValue> values) {
    }

    protected String createId(String pluginId, String deviceId) {
        return pluginId + ":" + deviceId;
    }
}
