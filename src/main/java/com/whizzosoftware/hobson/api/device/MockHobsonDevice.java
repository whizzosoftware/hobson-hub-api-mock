package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.plugin.HobsonPlugin;
import com.whizzosoftware.hobson.api.property.TypedProperty;

public class MockHobsonDevice extends AbstractHobsonDevice {
    private DeviceType type;
    private String manufacturerName;
    private String manufacturerVersion;
    private String modelName;
    private String preferredVariableName;

    public MockHobsonDevice(HobsonPlugin plugin, String id) {
        super(plugin, id);
    }

    public MockHobsonDevice(HobsonPlugin plugin, String id, DeviceType type, String defaultName) {
        this(plugin, id);
        this.type = type;
        setDefaultName(defaultName);
    }

    public void setDefaultName(String name) {
        super.setDefaultName(name);
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    @Override
    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @Override
    public String getManufacturerVersion() {
        return manufacturerVersion;
    }

    public void setManufacturerVersion(String manufacturerVersion) {
        this.manufacturerVersion = manufacturerVersion;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setPreferredVariableName(String preferredVariableName) {
        this.preferredVariableName = preferredVariableName;
    }

    @Override
    public String getPreferredVariableName() {
        return preferredVariableName;
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
