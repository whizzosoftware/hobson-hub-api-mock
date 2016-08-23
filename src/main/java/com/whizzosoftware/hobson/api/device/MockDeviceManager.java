/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.device.proxy.DeviceProxy;
import com.whizzosoftware.hobson.api.device.proxy.DeviceProxyVariable;
import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.plugin.PluginContext;
import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.property.PropertyContainerClass;
import com.whizzosoftware.hobson.api.property.TypedProperty;
import com.whizzosoftware.hobson.api.variable.DeviceVariable;
import com.whizzosoftware.hobson.api.variable.DeviceVariableContext;
import com.whizzosoftware.hobson.api.variable.DeviceVariableDescription;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.util.concurrent.Future;

import java.util.*;

public class MockDeviceManager implements DeviceManager {
    public final Map<String,Map<String,Object>> deviceConfigProps = new HashMap<>();

    public final Map<String,Map<String,DeviceDescription>> publishedDevices = new HashMap<>();
    public final Map<DeviceVariableContext,DeviceProxyVariable> variableMap = new HashMap<>();
    public final Map<DeviceContext,Boolean> availabilityMap = new HashMap<>();
    public final Map<DeviceContext,Long> checkInMap = new HashMap<>();
    protected EventLoopGroup eventLoop = new LocalEventLoopGroup(1);

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
    public Future sendDeviceHint(DeviceDescription desc, PropertyContainer config) {
        return eventLoop.submit(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void deleteDevicePassport(HubContext hubContext, String id) {

    }

    @Override
    public Collection<DeviceDescription> getAllDeviceDescriptions(HubContext hctx) {
        return getPublishedDevices();
    }

    @Override
    public Collection<DeviceDescription> getAllDeviceDescriptions(PluginContext pctx) {
        return getPublishedDevices();
    }

    @Override
    public Collection<String> getDeviceVariableNames(HubContext hubContext) {
        return null;
    }

    @Override
    public DeviceDescription getDeviceDescription(DeviceContext dctx) {
        return getPublishedDevice(dctx);
    }

    @Override
    public DeviceVariable getDeviceVariable(DeviceVariableContext vctx) {
        DeviceProxyVariable v = variableMap.get(vctx);
        return new DeviceVariable(getDeviceVariableDescription(vctx), v.getValue(), v.getLastUpdate());
    }

    @Override
    public Collection<DeviceVariable> getDeviceVariables(DeviceContext dctx) {
        List<DeviceVariable> results = new ArrayList<>();
        for (DeviceVariableContext ctx : variableMap.keySet()) {
            results.add(getDeviceVariable(ctx));
        }
        return results;
    }

    @Override
    public Collection<DeviceType> getPluginDeviceTypes(PluginContext pctx) {
        return null;
    }

    private DeviceVariableDescription getDeviceVariableDescription(DeviceVariableContext vctx) {
        Map<String,DeviceDescription> map = publishedDevices.get(vctx.getPluginId());
        return map.get(vctx.getDeviceId()).getVariableDescription(vctx);
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
    public PropertyContainer getDeviceConfiguration(DeviceContext ctx) {
        return null;
    }

    @Override
    public PropertyContainerClass getDeviceTypeConfigurationClass(PluginContext pluginContext, DeviceType deviceType) {
        return null;
    }

    @Override
    public PropertyContainerClass getDeviceConfigurationClass(DeviceContext deviceContext) {
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
        return availabilityMap.containsKey(ctx) ? availabilityMap.get(ctx) : false;
    }

    @Override
    public void publishDeviceType(PluginContext pluginContext, DeviceType deviceType, TypedProperty[] configProperties) {
    }

    @Override
    public Long getDeviceLastCheckIn(DeviceContext ctx) {
        return checkInMap.get(ctx);
    }

    @Override
    public Set<String> getDeviceTags(DeviceContext deviceContext) {
        return null;
    }

    @Override
    public boolean hasDevice(DeviceContext ctx) {
        return false;
    }

    @Override
    public boolean hasDeviceVariableValue(DeviceVariableContext vctx) {
        return false;
    }

    @Override
    public Future publishDevice(final PluginContext pctx, final DeviceProxy device, final Map<String,Object> config) {
        return eventLoop.submit(new Runnable() {
            @Override
            public void run() {
                String pluginId = pctx.getPluginId();
                DeviceContext dctx = DeviceContext.create(pctx, device.getDeviceId());
                Map<String,DeviceDescription> deviceMap = publishedDevices.get(pluginId);
                if (deviceMap == null) {
                    deviceMap = new HashMap<>();
                    publishedDevices.put(pluginId, deviceMap);
                }
                deviceMap.put(device.getDeviceId(), createDeviceDescription(dctx, device));
                setDeviceConfigurationProperties(dctx, config, true);
            }
        });
    }

    @Override
    public void setDeviceConfigurationProperty(DeviceContext ctx, String name, Object value, boolean overwrite) {

    }

    @Override
    public void setDeviceConfigurationProperties(DeviceContext ctx, Map<String, Object> values, boolean overwrite) {

    }

    @Override
    public void setDeviceName(DeviceContext dctx, String name) {

    }

    @Override
    public void setDeviceTags(DeviceContext deviceContext, Set<String> set) {

    }

    @Override
    public Future setDeviceVariable(final DeviceVariableContext dvctx, final Object o) {
        return eventLoop.submit(new Runnable() {
            @Override
            public void run() {
                variableMap.put(dvctx, new DeviceProxyVariable(dvctx, o, System.currentTimeMillis()));
            }
        });
    }

    @Override
    public Future setDeviceVariables(final Map<DeviceVariableContext, Object> map) {
        return eventLoop.submit(new Runnable() {
            @Override
            public void run() {
                for (DeviceVariableContext dvctx : map.keySet()) {
                    setDeviceVariable(dvctx, map.get(dvctx));
                }
            }
        });
    }

    protected String createId(String pluginId, String deviceId) {
        return pluginId + ":" + deviceId;
    }

    public Collection<DeviceDescription> getPublishedDevices() {
        List<DeviceDescription> results = new ArrayList<>();
        for (Map<String,DeviceDescription> devices : publishedDevices.values()) {
            results.addAll(devices.values());
        }
        return results;
    }

    public Collection<DeviceDescription> getPublishedDevices(String pluginId) {
        Map<String,DeviceDescription> deviceMap = publishedDevices.get(pluginId);
        if (deviceMap != null) {
            return deviceMap.values();
        }
        return null;
    }

    public DeviceDescription getPublishedDevice(DeviceContext ctx) {
        DeviceDescription result = null;
        Map<String,DeviceDescription> map = publishedDevices.get(ctx.getPluginId());
        if (map != null) {
            result = map.get(ctx.getDeviceId());
        }

        if (result != null) {
            return result;
        } else {
            throw new DeviceNotFoundException(ctx);
        }
    }

    protected DeviceDescription createDeviceDescription(DeviceContext dctx, DeviceProxy proxy) {
        DeviceDescription.Builder builder = new DeviceDescription.Builder(dctx).
                name(proxy.getDefaultName()).
                type(proxy.getDeviceType()).
                preferredVariableName(proxy.getPreferredVariableName()).
                manufacturerVersion(proxy.getManufacturerVersion()).
                manufacturerName(proxy.getManufacturerName()).
                modelName(proxy.getModelName());
        for (DeviceVariable dv : proxy.getVariables()) {
            builder.variableDescription(dv.getDescription());
        }
        return builder.build();
    }
}
