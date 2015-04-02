package com.whizzosoftware.hobson.api.action;

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
    public void publishAction(String userId, String hubId, HobsonAction action) {

    }

    @Override
    public void unpublishAllActions(String userId, String hubId, String pluginId) {

    }

    @Override
    public void executeAction(String userId, String hubId, String pluginId, String actionId, Map<String, Object> properties) {
        if ("log".equals(actionId)) {
            logCalls++;
        } else if ("setVariable".equals(actionId)) {
            setVariableCount++;
        }
    }

    @Override
    public Collection<HobsonAction> getAllActions(String userId, String hubId) {
        return null;
    }

    @Override
    public HobsonAction getAction(String userId, String hubId, String pluginId, String actionId) {
        return null;
    }

    public int getLogCalls() {
        return logCalls;
    }

    public int getSetVariableCount() {
        return setVariableCount;
    }
}
