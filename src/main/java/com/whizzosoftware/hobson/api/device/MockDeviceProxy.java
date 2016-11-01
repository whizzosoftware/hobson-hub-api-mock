/*
 *******************************************************************************
 * Copyright (c) 2016 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************
*/
package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.device.proxy.AbstractDeviceProxy;
import com.whizzosoftware.hobson.api.plugin.HobsonPlugin;
import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.property.TypedProperty;

public class MockDeviceProxy extends AbstractDeviceProxy {
    private String manufacturerName;
    private String manufacturerVersion;
    private String modelName;

    public MockDeviceProxy(HobsonPlugin plugin, String id, DeviceType deviceType) {
        this(plugin, id, deviceType, null);
    }

    public MockDeviceProxy(HobsonPlugin plugin, String id, DeviceType deviceType, String defaultName) {
        super(plugin, id, defaultName, deviceType);
    }

    @Override
    public String getManufacturerName() {
        return manufacturerName;
    }

    @Override
    public String getManufacturerVersion() {
        return manufacturerVersion;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public String getPreferredVariableName() {
        return null;
    }

    @Override
    protected TypedProperty[] createConfigurationPropertyTypes() {
        return null;
    }

    @Override
    public void onStartup(String name, PropertyContainer config) {
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setManufacturerVersion(String manufacturerVersion) {
        this.manufacturerVersion = manufacturerVersion;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public void onShutdown() {
    }

    @Override
    public void onDeviceConfigurationUpdate(PropertyContainer config) {

    }

    @Override
    public void onSetVariable(String name, Object value) {
        setVariableValue(name, value, System.currentTimeMillis());
    }
}
