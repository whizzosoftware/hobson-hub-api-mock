/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.plugin;

import com.whizzosoftware.hobson.api.config.Configuration;
import com.whizzosoftware.hobson.api.config.ConfigurationProperty;
import com.whizzosoftware.hobson.api.config.ConfigurationPropertyMetaData;
import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.image.ImageInputStream;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockPluginManager implements PluginManager {
    private final Map<String,Configuration> configMap = new HashMap<>();

    @Override
    public File getDataFile(PluginContext ctx, String filename) {
        return null;
    }

    @Override
    public Collection<HobsonPlugin> getAllPlugins(HubContext ctx) {
        return null;
    }

    @Override
    public HobsonPlugin getPlugin(PluginContext ctx) {
        return null;
    }

    @Override
    public PluginList getPluginDescriptors(HubContext ctx, boolean includeRemoteInfo) {
        return null;
    }

    @Override
    public Configuration getPluginConfiguration(HobsonPlugin plugin) {
        return getPluginConfiguration(plugin.getContext());
    }

    @Override
    public ImageInputStream getPluginIcon(PluginContext ctx) {
        return null;
    }

    @Override
    public Configuration getPluginConfiguration(PluginContext ctx) {
        return configMap.get(ctx.getPluginId());
    }

    @Override
    public Object getPluginConfigurationProperty(PluginContext ctx, String name) {
        Object value = null;
        Configuration c = getPluginConfiguration(ctx);
        if (c != null) {
            ConfigurationProperty cp = c.getProperty(name);
            if (cp != null) {
                value = cp.getValue();
            }
        }
        return value;
    }

    @Override
    public String getPluginCurrentVersion(PluginContext ctx) {
        return null;
    }

    @Override
    public void publishPlugin(HobsonPlugin plugin) {

    }

    @Override
    public void installPlugin(PluginContext ctx, String pluginVersion) {

    }

    @Override
    public void setPluginConfiguration(PluginContext ctx, Configuration config) {
        configMap.put(ctx.getPluginId(), config);
    }

    @Override
    public void setPluginConfigurationProperty(PluginContext ctx, String name, Object value) {
        Configuration config = configMap.get(ctx.getPluginId());
        if (config == null) {
            config = new Configuration();
            configMap.put(ctx.getPluginId(), config);
        }
        config.addProperty(new ConfigurationProperty(new ConfigurationPropertyMetaData(name), value));
    }

    @Override
    public void reloadPlugin(PluginContext ctx) {

    }
}
