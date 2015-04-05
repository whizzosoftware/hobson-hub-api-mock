package com.whizzosoftware.hobson.api.plugin;

import com.whizzosoftware.hobson.api.config.Configuration;

public class MockHobsonPlugin extends AbstractHobsonPlugin {
    public MockHobsonPlugin(String pluginId) {
        super(pluginId);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void onStartup(Configuration config) {

    }

    @Override
    public void onPluginConfigurationUpdate(Configuration config) {

    }
}
