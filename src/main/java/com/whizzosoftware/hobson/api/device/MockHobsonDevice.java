package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.config.Configuration;
import com.whizzosoftware.hobson.api.config.ConfigurationPropertyMetaData;
import com.whizzosoftware.hobson.api.plugin.HobsonPlugin;

public class MockHobsonDevice extends AbstractHobsonDevice {
    public MockHobsonDevice(HobsonPlugin plugin, String id) {
        super(plugin, id);
    }

    @Override
    public ConfigurationPropertyMetaData[] createConfigurationPropertyMetaData() {
        return new ConfigurationPropertyMetaData[0];
    }

    @Override
    public DeviceType getType() {
        return null;
    }

    @Override
    public void onStartup(Configuration config) {

    }

    @Override
    public void onShutdown() {

    }

    @Override
    public void onSetVariable(String variableName, Object value) {

    }
}
