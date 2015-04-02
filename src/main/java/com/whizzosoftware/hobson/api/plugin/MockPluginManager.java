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
import com.whizzosoftware.hobson.api.image.ImageInputStream;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MockPluginManager implements PluginManager {
    private final Map<String,Configuration> configMap = new HashMap<>();

    @Override
    public File getDataFile(String s, String s1) {
        return null;
    }

    @Override
    public Collection<HobsonPlugin> getAllPlugins(String userId, String hubId) {
        return null;
    }

    @Override
    public HobsonPlugin getPlugin(String s, String s1, String s2) {
        return null;
    }

    @Override
    public PluginList getPluginDescriptors(String s, String s1, boolean b) {
        return null;
    }

    @Override
    public Configuration getPluginConfiguration(String userId, String hubId, HobsonPlugin plugin) {
        return getPluginConfiguration(userId, hubId, plugin.getId());
    }

    @Override
    public ImageInputStream getPluginIcon(String userId, String hubId, String pluginId) {
        return null;
    }

    @Override
    public Configuration getPluginConfiguration(String userId, String hubId, String pluginId) {
        return configMap.get(pluginId);
    }

    @Override
    public Object getPluginConfigurationProperty(String userId, String hubId, String pluginId, String name) {
        Object value = null;
        Configuration c = getPluginConfiguration(userId, hubId, pluginId);
        if (c != null) {
            ConfigurationProperty cp = c.getProperty(name);
            if (cp != null) {
                value = cp.getValue();
            }
        }
        return value;
    }

    @Override
    public String getPluginCurrentVersion(String s, String s1, String s2) {
        return null;
    }

    @Override
    public void publishPlugin(String userId, String hubId, HobsonPlugin plugin) {

    }

    @Override
    public void installPlugin(String s, String s1, String s2, String s3) {

    }

    @Override
    public void setPluginConfiguration(String userId, String hubId, String pluginId, Configuration config) {
        configMap.put(pluginId, config);
    }

    @Override
    public void setPluginConfigurationProperty(String userId, String hubId, String pluginId, String name, Object value) {
        Configuration config = configMap.get(pluginId);
        if (config == null) {
            config = new Configuration();
            configMap.put(pluginId, config);
        }
        config.addProperty(new ConfigurationProperty(new ConfigurationPropertyMetaData(name), value));
    }

    @Override
    public void reloadPlugin(String s, String s1, String s2) {

    }
}
