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

import com.whizzosoftware.hobson.api.device.proxy.HobsonDeviceProxy;
import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.property.TypedProperty;
import io.netty.util.concurrent.Future;

public class MockHobsonPlugin extends AbstractHobsonPlugin {
    private String name;
    private String description;

    public MockHobsonPlugin(String pluginId, String version, String description) {
        super(pluginId, version, description);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    protected String getName() {
        return name;
    }

    @Override
    protected String getDescription() {
        return description;
    }

    @Override
    public Future publishDeviceProxy(HobsonDeviceProxy device) {
        return super.publishDeviceProxy(device);
    }

    @Override
    public void onStartup(PropertyContainer config) {
    }

    @Override
    public void onShutdown() {

    }

    @Override
    protected TypedProperty[] getConfigurationPropertyTypes() {
        return null;
    }

    @Override
    public void onPluginConfigurationUpdate(PropertyContainer config) {
    }
}
