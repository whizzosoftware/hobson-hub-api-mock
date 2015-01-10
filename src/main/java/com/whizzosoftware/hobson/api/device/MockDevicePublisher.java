/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.plugin.HobsonPlugin;

import java.util.ArrayList;
import java.util.List;

public class MockDevicePublisher implements DevicePublisher {
    public List<HobsonDevice> publishedDevices = new ArrayList<>();

    public List<HobsonDevice> getPublishedDevices() {
        return publishedDevices;
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
}
