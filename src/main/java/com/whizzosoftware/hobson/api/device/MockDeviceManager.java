/*
 *******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************
*/
package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.HobsonNotFoundException;
import com.whizzosoftware.hobson.api.device.proxy.HobsonDeviceProxy;
import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.plugin.PluginContext;
import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.variable.*;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.util.concurrent.Future;

import java.util.*;

import static com.whizzosoftware.hobson.api.device.HobsonDeviceDescriptor.AVAILABILITY_TIMEOUT_INTERVAL;

public class MockDeviceManager implements DeviceManager {
    public final Map<String,Map<String,Object>> deviceConfigProps = new HashMap<>();

    public final Map<String,Map<String,HobsonDeviceProxy>> publishedDevices = new HashMap<>();
    protected EventLoopGroup eventLoop = new LocalEventLoopGroup(1);

    @Override
    public Collection<HobsonDeviceDescriptor> getDevices(HubContext hctx) {
        return getPublishedDevices();
    }

    @Override
    public Collection<HobsonDeviceDescriptor> getDevices(PluginContext pctx) {
        return getPublishedDevices();
    }

    @Override
    public Collection<String> getDeviceVariableNames(HubContext hctx) {
        return null;
    }

    @Override
    public void deleteDevice(DeviceContext dctx) {
        Map<String,HobsonDeviceProxy> devices = publishedDevices.get(dctx.getPluginId());
        devices.remove(dctx.getDeviceId());
    }

    @Override
    public HobsonDeviceDescriptor getDevice(DeviceContext dctx) {
        return getPublishedDevice(dctx).getDescriptor();
    }

    @Override
    public void updateDevice(HobsonDeviceDescriptor device) {

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
    public Long getDeviceLastCheckin(DeviceContext dctx) {
        return publishedDevices.get(dctx.getPluginId()).get(dctx.getDeviceId()).getLastCheckin();
    }

    @Override
    public DeviceVariableState getDeviceVariable(DeviceVariableContext ctx) {
        return getPublishedDevice(ctx.getDeviceContext()).getVariableState(ctx.getName());
    }

    @Override
    public boolean hasDeviceVariable(DeviceVariableContext ctx) {
        try {
            return (getPublishedDevice(ctx.getDeviceContext()).hasVariable(ctx.getName()));
        } catch (HobsonNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean isDeviceAvailable(DeviceContext ctx) {
        return isDeviceAvailable(ctx, System.currentTimeMillis());
    }

    boolean isDeviceAvailable(DeviceContext ctx, long now) {
        Long lastCheckin = getDeviceLastCheckin(ctx);
        return (lastCheckin != null && now - lastCheckin < AVAILABILITY_TIMEOUT_INTERVAL);
    }

    @Override
    public Future publishDevice(final HobsonDeviceProxy device, final Map<String, Object> config, final Runnable runnable) {
        return eventLoop.submit(new Runnable() {
            @Override
            public void run() {
                String pluginId = device.getContext().getPluginId();
                Map<String,HobsonDeviceProxy> deviceMap = publishedDevices.get(pluginId);
                if (deviceMap == null) {
                    deviceMap = new HashMap<>();
                    publishedDevices.put(pluginId, deviceMap);
                }
                deviceMap.put(device.getContext().getDeviceId(), device);
                setDeviceConfiguration(device.getContext(), config);
                device.start(null, null);
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    @Override
    public boolean hasDevice(DeviceContext ctx) {
        return false;
    }

    @Override
    public void setDeviceConfigurationProperty(DeviceContext dctx, String name, Object value) {

    }

    @Override
    public void setDeviceConfiguration(DeviceContext dctx, Map<String, Object> values) {

    }

    @Override
    public void setDeviceName(DeviceContext dctx, String name) {

    }

    @Override
    public void setDeviceTags(DeviceContext deviceContext, Set<String> set) {

    }

    public void setDeviceVariable(final DeviceVariableContext dvctx, final Object o) {
        Map<String,HobsonDeviceProxy> map = publishedDevices.get(dvctx.getPluginId());
        HobsonDeviceProxy d = map.get(dvctx.getDeviceId());
        d.onSetVariables(Collections.singletonMap(dvctx.getName(), o));
    }

    public void setDeviceVariables(final Map<DeviceVariableContext, Object> map) {
        for (DeviceVariableContext dvctx : map.keySet()) {
            setDeviceVariable(dvctx, map.get(dvctx));
        }
    }

    protected String createId(String pluginId, String deviceId) {
        return pluginId + ":" + deviceId;
    }

    public Collection<HobsonDeviceDescriptor> getPublishedDevices() {
        List<HobsonDeviceDescriptor> results = new ArrayList<>();
        for (Map<String,HobsonDeviceProxy> devices : publishedDevices.values()) {
            for (HobsonDeviceProxy device : devices.values()) {
                results.add(device.getDescriptor());
            }
        }
        return results;
    }

    public Collection<HobsonDeviceProxy> getPublishedDevices(String pluginId) {
        Map<String,HobsonDeviceProxy> deviceMap = publishedDevices.get(pluginId);
        if (deviceMap != null) {
            return new ArrayList<>(deviceMap.values());
        }
        return null;
    }

    public HobsonDeviceProxy getPublishedDevice(DeviceContext ctx) {
        HobsonDeviceProxy result = null;
        Map<String,HobsonDeviceProxy> map = publishedDevices.get(ctx.getPluginId());
        if (map != null) {
            result = map.get(ctx.getDeviceId());
        }

        if (result != null) {
            return result;
        } else {
            throw new DeviceNotFoundException(ctx);
        }
    }

    public int getPublishedDeviceCount(PluginContext pctx) {
        Map<String,HobsonDeviceProxy> devices = publishedDevices.get(pctx.getPluginId());
        return (devices != null) ? devices.size() : 0;
    }
}
