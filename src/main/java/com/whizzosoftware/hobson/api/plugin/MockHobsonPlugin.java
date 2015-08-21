package com.whizzosoftware.hobson.api.plugin;

import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.property.TypedProperty;
import com.whizzosoftware.hobson.api.task.TaskProvider;

public class MockHobsonPlugin extends AbstractHobsonPlugin {
    private TaskProvider taskProvider;

    public MockHobsonPlugin(String pluginId) {
        super(pluginId);
    }

    public void setTaskProvider(TaskProvider taskProvider) {
        this.taskProvider = taskProvider;
    }


    @Override
    public String getName() {
        return null;
    }

    @Override
    public void onStartup(PropertyContainer config) {
    }

    @Override
    public void onShutdown() {

    }

    @Override
    protected TypedProperty[] createSupportedProperties() {
        return null;
    }

    @Override
    public void onPluginConfigurationUpdate(PropertyContainer config) {
    }

    @Override
    public TaskProvider getTaskProvider() {
        return taskProvider;
    }
}
