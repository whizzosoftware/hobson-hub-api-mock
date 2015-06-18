/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.plugin;

import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.image.ImageInputStream;
import com.whizzosoftware.hobson.api.property.PropertyContainer;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockPluginManager implements PluginManager {
    private final Map<String,PropertyContainer> configMap = new HashMap<>();

    @Override
    public File getDataFile(PluginContext ctx, String filename) {
        return null;
    }

    @Override
    public HobsonPlugin getLocalPlugin(PluginContext ctx) {
        return null;
    }

    @Override
    public Collection<PluginDescriptor> getLocalPluginDescriptors(HubContext ctx) {
        return null;
    }

    @Override
    public Collection<PluginDescriptor> getRemotePluginDescriptors(HubContext ctx) {
        return null;
    }

    @Override
    public PluginDescriptor getRemotePluginDescriptor(PluginContext ctx, String version) {
        return null;
    }

    @Override
    public ImageInputStream getLocalPluginIcon(PluginContext ctx) {
        return null;
    }

    @Override
    public PropertyContainer getLocalPluginConfiguration(PluginContext ctx) {
        return configMap.get(ctx.getPluginId());
    }

    @Override
    public Object getLocalPluginConfigurationProperty(PluginContext ctx, String name) {
        Object value = null;
        PropertyContainer c = getLocalPluginConfiguration(ctx);
        if (c != null) {
            value = c.getPropertyValue(name);
        }
        return value;
    }

    @Override
    public void installRemotePlugin(PluginContext ctx, String pluginVersion) {

    }

    @Override
    public void setLocalPluginConfiguration(PluginContext ctx, PropertyContainer config) {
        configMap.put(ctx.getPluginId(), config);
    }

    @Override
    public void setLocalPluginConfigurationProperty(PluginContext ctx, String name, Object value) {
        PropertyContainer config = configMap.get(ctx.getPluginId());
        if (config == null) {
            config = new PropertyContainer();
            configMap.put(ctx.getPluginId(), config);
        }
        config.setPropertyValue(name, value);
    }

    @Override
    public void reloadLocalPlugin(PluginContext ctx) {

    }
}
