package com.whizzosoftware.hobson.api.plugin;

import com.whizzosoftware.hobson.api.property.PropertyContainer;

public class MockHobsonPlugin extends AbstractHobsonPlugin {
    public MockHobsonPlugin(String pluginId) {
        super(pluginId);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void onStartup(PropertyContainer config) {

    }

    @Override
    public void onPluginConfigurationUpdate(PropertyContainer config) {

    }
}
