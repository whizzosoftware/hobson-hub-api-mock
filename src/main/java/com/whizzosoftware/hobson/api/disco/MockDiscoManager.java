/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.disco;

public class MockDiscoManager implements DiscoManager {
    @Override
    public void requestDeviceAdvertisementSnapshot(String userId, String hubId, String pluginId, String protocolId) {

    }

    @Override
    public void fireDeviceAdvertisement(String userId, String hubId, DeviceAdvertisement advertisement) {

    }
}
