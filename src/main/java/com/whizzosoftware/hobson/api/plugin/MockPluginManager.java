/*
 *******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************
*/
package com.whizzosoftware.hobson.api.plugin;

import com.whizzosoftware.hobson.api.HobsonNotFoundException;
import com.whizzosoftware.hobson.api.action.SingleAction;
import com.whizzosoftware.hobson.api.config.ConfigurationManager;
import com.whizzosoftware.hobson.api.device.DeviceContext;
import com.whizzosoftware.hobson.api.event.EventManager;
import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.image.ImageInputStream;
import com.whizzosoftware.hobson.api.property.PropertyContainer;

import java.util.*;

public class MockPluginManager extends AbstractPluginManager {
    private final Map<PluginContext,HobsonPlugin> pluginMap = new HashMap<>();
    private final Map<DeviceContext,Long> availabilityMap = new HashMap<>();
    private ConfigurationManager configManager;
    private EventManager eventManager;

    @Override
    public void addRemoteRepository(String uri) {

    }

    @Override
    public SingleAction createAction(PropertyContainer pc) {
        return null;
    }

    @Override
    protected HobsonPlugin getLocalPluginInternal(PluginContext ctx) {
        HobsonPlugin p = pluginMap.get(ctx);
        if (p != null) {
            return p;
        } else {
            throw new HobsonNotFoundException("Plugin not found: " + ctx);
        }
    }

    @Override
    protected ConfigurationManager getConfigurationManager() {
        return configManager;
    }

    public void setConfigurationManager(ConfigurationManager configManager) {
        this.configManager = configManager;
    }

    @Override
    protected EventManager getEventManager() {
        return eventManager;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public Collection<HobsonLocalPluginDescriptor> getLocalPlugins(HubContext ctx) {
        List<HobsonLocalPluginDescriptor> results = new ArrayList<>();
        for (HobsonPlugin p : pluginMap.values()) {
            results.add(p.getDescriptor());
        }
        return results;
    }

    public void addLocalPlugin(HobsonPlugin plugin) {
        pluginMap.put(plugin.getContext(), plugin);
    }

    @Override
    public Collection<HobsonPluginDescriptor> getRemotePlugins(HubContext ctx) {
        return null;
    }

    @Override
    public Collection<String> getRemoteRepositories() {
        return null;
    }

    @Override
    public HobsonPluginDescriptor getRemotePlugin(PluginContext ctx, String version) {
        return null;
    }

    @Override
    public ImageInputStream getLocalPluginIcon(PluginContext ctx) {
        return null;
    }

    @Override
    public void installRemotePlugin(PluginContext ctx, String pluginVersion) {
    }

    @Override
    public void reloadLocalPlugin(PluginContext ctx) {

    }

    @Override
    public void removeRemoteRepository(String uri) {

    }

    @Override
    public Long getLocalPluginDeviceLastCheckin(PluginContext ctx, String deviceId) {
        return availabilityMap.get(DeviceContext.create(ctx, deviceId));
    }

    public void setLastCheckin(DeviceContext dctx, Long checkin) {
        availabilityMap.put(dctx, checkin);
    }
}
