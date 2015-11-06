package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.plugin.HobsonPlugin;
import com.whizzosoftware.hobson.api.property.TypedProperty;

public class MockHobsonDevice extends AbstractHobsonDevice {
    private DeviceType type;

    public MockHobsonDevice(HobsonPlugin plugin, String id) {
        super(plugin, id);
    }

    public void setDefaultName(String name) {
        super.setDefaultName(name);
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    @Override
    protected TypedProperty[] createSupportedProperties() {
        return null;
    }

    @Override
    public DeviceType getType() {
        return type;
    }

    @Override
    public void onShutdown() {
    }

    @Override
    public void onSetVariable(String variableName, Object value) {

    }
}
