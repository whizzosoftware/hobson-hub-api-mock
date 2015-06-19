package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.plugin.HobsonPlugin;

public class MockHobsonDevice extends AbstractHobsonDevice {
    public MockHobsonDevice(HobsonPlugin plugin, String id) {
        super(plugin, id);
    }

    @Override
    public DeviceType getType() {
        return null;
    }

    @Override
    public void onShutdown() {

    }

    @Override
    public void onSetVariable(String variableName, Object value) {

    }
}
