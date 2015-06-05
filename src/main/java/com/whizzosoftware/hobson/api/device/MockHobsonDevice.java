package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.plugin.HobsonPlugin;
import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.property.TypedProperty;

public class MockHobsonDevice extends AbstractHobsonDevice {
    public MockHobsonDevice(HobsonPlugin plugin, String id) {
        super(plugin, id);
    }

    @Override
    public TypedProperty[] createConfigurationPropertyMetaData() {
        return new TypedProperty[0];
    }

    @Override
    public DeviceType getType() {
        return null;
    }

    @Override
    public void onStartup(PropertyContainer config) {

    }

    @Override
    public void onShutdown() {

    }

    @Override
    public void onSetVariable(String variableName, Object value) {

    }
}
