/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.config.Configuration;
import com.whizzosoftware.hobson.api.plugin.HobsonPlugin;
import com.whizzosoftware.hobson.api.variable.telemetry.TelemetryInterval;
import com.whizzosoftware.hobson.api.variable.telemetry.TemporalValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MockDeviceManager implements DeviceManager {
    public List<HobsonDevice> publishedDevices = new ArrayList<>();

    @Override
    public void enableDeviceTelemetry(String s, String s1, String s2, String s3, boolean b) {

    }

    @Override
    public Collection<HobsonDevice> getAllDevices(String s, String s1) {
        return publishedDevices;
    }

    @Override
    public Collection<HobsonDevice> getAllPluginDevices(String s, String s1, String s2) {
        return null;
    }

    @Override
    public Collection<HobsonDevice> getAllTelemetryEnabledDevices(String s, String s1) {
        return null;
    }

    @Override
    public HobsonDevice getDevice(String s, String s1, String s2, String s3) {
        return null;
    }

    @Override
    public Configuration getDeviceConfiguration(String s, String s1, String s2, String s3) {
        return null;
    }

    @Override
    public Object getDeviceConfigurationProperty(String s, String s1, String s2, String s3, String s4) {
        return null;
    }

    @Override
    public Map<String, Collection<TemporalValue>> getDeviceTelemetry(String s, String s1, String s2, String s3, long l, TelemetryInterval telemetryInterval) {
        return null;
    }

    @Override
    public boolean hasDevice(String s, String s1, String s2, String s3) {
        return false;
    }

    @Override
    public boolean isDeviceTelemetryEnabled(String s, String s1, String s2, String s3) {
        return false;
    }

    @Override
    public void setDeviceConfigurationProperty(String s, String s1, String s2, String s3, String s4, Object o, boolean b) {

    }

    @Override
    public void setDeviceName(String s, String s1, String s2, String s3, String s4) {

    }

    @Override
    public void publishDevice(HobsonPlugin plugin, HobsonDevice device) {
        publishedDevices.add(device);
    }

    @Override
    public void unpublishDevice(HobsonPlugin hobsonPlugin, String s) {

    }

    @Override
    public void unpublishAllDevices(HobsonPlugin hobsonPlugin) {

    }

    @Override
    public void writeDeviceTelemetry(String s, String s1, String s2, String s3, Map<String, TemporalValue> map) {

    }
}
