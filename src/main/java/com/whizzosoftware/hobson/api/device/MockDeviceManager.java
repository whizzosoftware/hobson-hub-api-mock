/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.config.Configuration;
import com.whizzosoftware.hobson.api.plugin.HobsonPlugin;

import java.util.*;

public class MockDeviceManager implements DeviceManager {
    public final Map<String,Map<String,Object>> deviceConfigProps = new HashMap<>();

    public final Map<String,Map<String,HobsonDevice>> publishedDevices = new HashMap<>();

    @Override
    public Collection<HobsonDevice> getAllDevices(String userId, String hubId) {
        return getPublishedDevices();
    }

    @Override
    public Collection<HobsonDevice> getAllPluginDevices(String userId, String hubId, String pluginId) {
        return getPublishedDevices(pluginId);
    }

    @Override
    public Collection<HobsonDevice> getAllTelemetryEnabledDevices(String userId, String hubId) {
        return null;
    }

    @Override
    public HobsonDevice getDevice(String userId, String hubId, String pluginId, String deviceId) {
        return getPublishedDevice(pluginId, deviceId);
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
    public boolean hasDevice(String userId, String hubId, String pluginId, String deviceId) {
        return false;
    }

    @Override
    public boolean isDeviceTelemetryEnabled(String userId, String hubId, String pluginId, String deviceId) {
        return false;
    }

    @Override
    public void publishDevice(String userId, String hubId, HobsonPlugin plugin, HobsonDevice device) {
        publishDevice(userId, hubId, plugin, device, false);
    }

    @Override
    public void publishDevice(String userId, String hubId, HobsonPlugin plugin, HobsonDevice device, boolean republish) {
        Map<String,HobsonDevice> deviceMap = publishedDevices.get(plugin.getId());
        if (deviceMap == null) {
            deviceMap = new HashMap<>();
            publishedDevices.put(plugin.getId(), deviceMap);
        }
        deviceMap.put(device.getId(), device);
    }

    @Override
    public void setDeviceConfiguration(String userId, String hubId, String pluginId, String deviceId, Configuration config) {

    }

    @Override
    public void setDeviceConfigurationProperty(String userId, String hubId, String pluginId, String deviceId, String name, Object value, boolean overwrite) {

    }

    @Override
    public void setDeviceName(String userId, String hubId, String pluginId, String deviceId, String name) {

    }

    @Override
    public void unpublishDevice(String userId, String hubId, HobsonPlugin plugin, String deviceId) {
        Map<String,HobsonDevice> deviceMap = publishedDevices.get(plugin.getId());
        if (deviceMap != null) {
            deviceMap.remove(deviceId);
        }
    }

    @Override
    public void unpublishAllDevices(String userId, String hubId, HobsonPlugin plugin) {
        publishedDevices.clear();
    }

    protected String createId(String pluginId, String deviceId) {
        return pluginId + ":" + deviceId;
    }

    public Collection<HobsonDevice> getPublishedDevices() {
        List<HobsonDevice> results = new ArrayList<>();
        for (Map<String,HobsonDevice> devices : publishedDevices.values()) {
            results.addAll(devices.values());
        }
        return results;
    }

    public Collection<HobsonDevice> getPublishedDevices(String pluginId) {
        Map<String,HobsonDevice> deviceMap = publishedDevices.get(pluginId);
        if (deviceMap != null) {
            return deviceMap.values();
        }
        return null;
    }

    public HobsonDevice getPublishedDevice(String pluginId, String deviceId) {
        HobsonDevice result = null;
        Map<String,HobsonDevice> map = publishedDevices.get(pluginId);
        if (map != null) {
            result = map.get(deviceId);
        }

        if (result != null) {
            return result;
        } else {
            throw new DeviceNotFoundException(pluginId, deviceId);
        }
    }
}
