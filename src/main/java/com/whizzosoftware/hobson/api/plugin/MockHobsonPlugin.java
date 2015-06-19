package com.whizzosoftware.hobson.api.plugin;

import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.property.TypedProperty;

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
    protected TypedProperty[] createSupportedProperties() {
        return null;
    }

    @Override
    public void onPluginConfigurationUpdate(PropertyContainer config) {
    }
}
