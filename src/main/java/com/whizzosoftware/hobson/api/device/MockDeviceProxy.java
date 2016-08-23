package com.whizzosoftware.hobson.api.device;

import com.whizzosoftware.hobson.api.device.proxy.AbstractDeviceProxy;
import com.whizzosoftware.hobson.api.plugin.HobsonPlugin;
import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.property.TypedProperty;
import com.whizzosoftware.hobson.api.variable.DeviceVariableContext;
import com.whizzosoftware.hobson.api.variable.DeviceVariableDescription;

public class MockDeviceProxy extends AbstractDeviceProxy {
    private DeviceType deviceType;
    private String manufacturerName;
    private String manufacturerVersion;
    private String modelName;
    private String preferredVariableName;
    private boolean isStarted;

    public MockDeviceProxy(HobsonPlugin plugin, String id, DeviceType deviceType) {
        this(plugin, id, deviceType, null);
    }

    public MockDeviceProxy(HobsonPlugin plugin, String id, DeviceType deviceType, String defaultName) {
        super(plugin, id, defaultName);
        this.deviceType = deviceType;
    }

    @Override
    protected TypedProperty[] createConfigurationPropertyTypes() {
        return null;
    }

    @Override
    protected DeviceVariableDescription[] createVariableDescriptions() {
        return null;
    }

    @Override
    public DeviceType getDeviceType() {
        return deviceType;
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

    @Override
    public String getPreferredVariableName() {
        return preferredVariableName;
    }

    @Override
    public void onShutdown() {
    }

    @Override
    public void onDeviceConfigurationUpdate(PropertyContainer config) {

    }

    @Override
    public void onSetVariable(String variableName, Object value) {

    }
}
