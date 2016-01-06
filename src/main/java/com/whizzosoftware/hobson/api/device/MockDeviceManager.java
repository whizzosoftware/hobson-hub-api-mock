/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.plugin.EventLoopExecutor;
import com.whizzosoftware.hobson.api.plugin.PluginContext;
import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.property.PropertyContainerClass;

import java.util.*;

public class MockDeviceManager implements DeviceManager {
    public final Map<String,Map<String,Object>> deviceConfigProps = new HashMap<>();

    public final Map<String,Map<String,HobsonDevice>> publishedDevices = new HashMap<>();
    public final Map<DeviceContext,Boolean> availabilityMap = new HashMap<>();
    public final Map<DeviceContext,Long> checkInMap = new HashMap<>();

    @Override
    public DevicePassport createDevicePassport(HubContext hubContext, String deviceId) {
        return null;
    }

    @Override
    public Collection<DevicePassport> getDevicePassports(HubContext hubContext) {
        return null;
    }

    @Override
    public DevicePassport getDevicePassport(HubContext hubContext, String id) {
        return null;
    }

    @Override
    public DevicePassport activateDevicePassport(HubContext hubContext, String deviceId) {
        return null;
    }

    @Override
    public void deleteDevicePassport(HubContext hubContext, String id) {

    }

    @Override
    public boolean verifyDevicePassport(HubContext hubContext, String id, String secret) {
        return false;
    }

    @Override
    public void resetDevicePassport(HubContext hubContext, String id) {

    }

    @Override
    public void setDeviceAvailability(DeviceContext ctx, boolean available, Long checkInTime) {
        availabilityMap.put(ctx, available);
        if (checkInTime != null) {
            checkInMap.put(ctx, checkInTime);
        }
    }

    @Override
    public Collection<HobsonDevice> getAllDevices(HubContext ctx) {
        return getPublishedDevices();
    }

    @Override
    public Collection<HobsonDevice> getAllDevices(PluginContext ctx) {
        return getPublishedDevices();
    }

    @Override
    public HobsonDevice getDevice(DeviceContext ctx) {
        return getPublishedDevice(ctx);
    }

    @Override
    public PropertyContainerClass getDeviceConfigurationClass(DeviceContext deviceContext) {
        return null;
    }

    @Override
    public PropertyContainer getDeviceConfiguration(DeviceContext ctx) {
        return null;
    }

    @Override
    public Object getDeviceConfigurationProperty(DeviceContext ctx, String name) {
        Object value = null;
        Map<String,Object> map = deviceConfigProps.get(createId(ctx.getPluginId(), ctx.getDeviceId()));
        if (map != null) {
            value = map.get(name);
        }
        return value;
    }

    @Override
    public boolean isDeviceAvailable(DeviceContext ctx) {
        return availabilityMap.get(ctx);
    }

    @Override
    public Long getDeviceLastCheckIn(DeviceContext ctx) {
        return checkInMap.get(ctx);
    }

    @Override
    public boolean hasDevice(DeviceContext ctx) {
        return false;
    }

    @Override
    public void publishDevice(HobsonDevice device) {
        publishDevice(device, false);
    }

    @Override
    public void publishDevice(HobsonDevice device, boolean republish) {
        String pluginId = device.getContext().getPluginId();
        Map<String,HobsonDevice> deviceMap = publishedDevices.get(pluginId);
        if (deviceMap == null) {
            deviceMap = new HashMap<>();
            publishedDevices.put(pluginId, deviceMap);
        }
        deviceMap.put(device.getContext().getDeviceId(), device);
        if (device instanceof AbstractHobsonDevice) {
            ((AbstractHobsonDevice)device).onStartup(null);
        }
    }

    @Override
    public void setDeviceConfigurationProperty(DeviceContext ctx, String name, Object value, boolean overwrite) {

    }

    @Override
    public void setDeviceConfigurationProperties(DeviceContext ctx, Map<String, Object> values, boolean overwrite) {

    }

    @Override
    public void unpublishDevice(DeviceContext ctx, EventLoopExecutor executor) {
        Map<String,HobsonDevice> deviceMap = publishedDevices.get(ctx.getPluginId());
        if (deviceMap != null) {
            deviceMap.remove(ctx.getDeviceId());
        }
    }

    @Override
    public void unpublishAllDevices(PluginContext ctx, EventLoopExecutor executor) {
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

    public HobsonDevice getPublishedDevice(DeviceContext ctx) {
        HobsonDevice result = null;
        Map<String,HobsonDevice> map = publishedDevices.get(ctx.getPluginId());
        if (map != null) {
            result = map.get(ctx.getDeviceId());
        }

        if (result != null) {
            return result;
        } else {
            throw new DeviceNotFoundException(ctx);
        }
    }
}
