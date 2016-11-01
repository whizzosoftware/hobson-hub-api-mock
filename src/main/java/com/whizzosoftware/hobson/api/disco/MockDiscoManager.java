/*
 *******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************
*/
package com.whizzosoftware.hobson.api.disco;

import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.plugin.PluginContext;

import java.util.Collection;

public class MockDiscoManager implements DiscoManager {
    @Override
    public Collection<DeviceAdvertisement> getExternalDeviceAdvertisements(PluginContext ctx, String protocolId) {
        return null;
    }

    @Override
    public Collection<DeviceAdvertisement> getInternalDeviceAdvertisements(HubContext ctx, String protocolId) {
        return null;
    }

    @Override
    public DeviceAdvertisement getInternalDeviceAdvertisement(HubContext ctx, String protocolId, String advId) {
        return null;
    }

    @Override
    public void publishDeviceAdvertisement(HubContext ctx, DeviceAdvertisement advertisement, boolean internal) {

    }
}
