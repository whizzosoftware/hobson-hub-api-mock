/*
 *******************************************************************************
 * Copyright (c) 2016 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************
*/
package com.whizzosoftware.hobson.api.config;

import com.whizzosoftware.hobson.api.device.DeviceContext;
import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.plugin.PluginContext;
import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.property.PropertyContainerClass;

import java.util.HashMap;
import java.util.Map;

public class MockConfigurationManager implements ConfigurationManager {
    private Map<PluginContext,PropertyContainer> configMap = new HashMap<>();

    @Override
    public PropertyContainer getHubConfiguration(HubContext ctx) {
        return null;
    }

    @Override
    public Object getHubConfigurationProperty(HubContext ctx, String name) {
        return null;
    }

    @Override
    public void setHubConfiguration(HubContext ctx, PropertyContainer configuration) {

    }

    @Override
    public void deleteHubConfiguration(HubContext ctx) {

    }

    @Override
    public PropertyContainer getLocalPluginConfiguration(PluginContext ctx, PropertyContainerClass configurationClass) {
        return configMap.get(ctx);
    }

    @Override
    public void setLocalPluginConfiguration(PluginContext ctx, PropertyContainer newConfig) {
        configMap.put(ctx, newConfig);
    }

    @Override
    public void setLocalPluginConfigurationProperty(PluginContext ctx, String name, Object value) {

    }

    @Override
    public PropertyContainer getDeviceConfiguration(DeviceContext ctx, PropertyContainerClass metas) {
        return null;
    }

    @Override
    public Object getDeviceConfigurationProperty(DeviceContext ctx, String name) {
        return null;
    }

    @Override
    public void setDeviceConfigurationProperty(DeviceContext ctx, PropertyContainerClass configClass, String name, Object value) {

    }

    @Override
    public void setDeviceConfigurationProperties(DeviceContext ctx, PropertyContainerClass configurationClass, Map<String, Object> values) {

    }
}
