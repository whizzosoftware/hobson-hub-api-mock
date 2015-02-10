/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.plugin.HobsonPlugin;

import java.util.*;

public class MockDevicePublisher implements DevicePublisher {
    public final Map<String,Map<String,HobsonDevice>> publishedDevices = new HashMap<>();

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

    @Override
    public void publishDevice(HobsonPlugin plugin, HobsonDevice device) {
        publishDevice(plugin, device, false);
    }

    @Override
    public void publishDevice(HobsonPlugin plugin, HobsonDevice device, boolean republish) {
        Map<String,HobsonDevice> deviceMap = publishedDevices.get(plugin.getId());
        if (deviceMap == null) {
            deviceMap = new HashMap<>();
            publishedDevices.put(plugin.getId(), deviceMap);
        }
        deviceMap.put(device.getId(), device);
    }

    @Override
    public void unpublishDevice(HobsonPlugin plugin, String deviceId) {
        Map<String,HobsonDevice> deviceMap = publishedDevices.get(plugin.getId());
        if (deviceMap != null) {
            deviceMap.remove(deviceId);
        }
    }

    @Override
    public void unpublishAllDevices(HobsonPlugin hobsonPlugin) {
        publishedDevices.clear();
    }
}
