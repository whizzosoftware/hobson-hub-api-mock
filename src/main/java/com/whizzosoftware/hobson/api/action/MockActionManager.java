package com.whizzosoftware.hobson.api.action;

import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.plugin.PluginContext;

import java.util.Collection;
import java.util.Map;

public class MockActionManager implements ActionManager {
    private int setVariableCount;
    private int logCalls;

    public MockActionManager() {
        reset();
    }

    public void reset() {
        logCalls = 0;
        setVariableCount = 0;
    }

    @Override
    public void publishAction(HobsonAction action) {

    }

    @Override
    public void unpublishAllActions(PluginContext ctx) {

    }

    @Override
    public void executeAction(ActionContext ctx, Map<String, Object> properties) {
        if ("log".equals(ctx.getActionId())) {
            logCalls++;
        } else if ("setVariable".equals(ctx.getActionId())) {
            setVariableCount++;
        }
    }

    @Override
    public Collection<HobsonAction> getAllActions(HubContext ctx) {
        return null;
    }

    @Override
    public HobsonAction getAction(ActionContext ctx) {
        return null;
    }

    public int getLogCalls() {
        return logCalls;
    }

    public int getSetVariableCount() {
        return setVariableCount;
    }
}
