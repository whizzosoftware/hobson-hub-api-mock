/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.hub;


import com.whizzosoftware.hobson.api.property.PropertyContainer;
import com.whizzosoftware.hobson.api.property.PropertyContainerClass;
import com.whizzosoftware.hobson.api.property.PropertyContainerClassContext;
import com.whizzosoftware.hobson.api.telemetry.TelemetryManager;

import java.io.IOException;
import java.util.*;

public class MockHubManager implements HubManager, LocalHubManager {
    private Map<HubContext,HobsonHub> hubs = new HashMap<>();


    @Override
    public String getVersion(HubContext hubContext) {
        return null;
    }

    @Override
    public Collection<HubContext> getAllHubs() {
        return null;
    }

    @Override
    public Collection<HubContext> getHubs(String userId) {
        List<HubContext> results = new ArrayList<>();
        for (HubContext ctx : hubs.keySet()) {
            if (ctx.getUserId().equals(userId)) {
                results.add(hubs.get(ctx).getContext());
            }
        }
        return results;
    }

    @Override
    public HobsonHub getHub(HubContext ctx) {
        return hubs.get(ctx);
    }

    @Override
    public void sendTestEmail(HubContext ctx, PropertyContainer config) {

    }

    @Override
    public void sendEmail(HubContext ctx, String recipientAddress, String subject, String body) {

    }

    @Override
    public void setConfiguration(HubContext ctx, PropertyContainer configuration) {

    }

    @Override
    public void addTelemetryManager(TelemetryManager telemetryManager) {

    }

    @Override
    public LineRange getLog(HubContext ctx, long startLine, long endLine, Appendable appendable) {
        return null;
    }

    @Override
    public LocalHubManager getLocalManager() {
        return this;
    }

    @Override
    public NetworkInfo getNetworkInfo() throws IOException {
        return null;
    }

    @Override
    public boolean authenticateLocal(HubContext ctx, String password) {
        return false;
    }

    @Override
    public void setLocalPassword(HubContext ctx, PasswordChange change) {

    }

    @Override
    public void addErrorLogAppender(Object o) {

    }

    @Override
    public void removeLogAppender(Object o) {

    }

    public HobsonHub addHub(String userId, String name) {
        HobsonHub hub = new HobsonHub(HubContext.create(userId, UUID.randomUUID().toString()), name);
        hubs.put(hub.getContext(), hub);
        return hub;
    }

    @Override
    public String getUserIdForHubId(String hubId) {
        return null;
    }

    @Override
    public void deleteConfiguration(HubContext ctx) {

    }

    @Override
    public boolean authenticateHub(HubCredentials credentials) {
        return false;
    }

    @Override
    public PropertyContainerClass getConfigurationClass(HubContext hubContext) {
        return new HubConfigurationClass();
    }

    @Override
    public PropertyContainer getConfiguration(HubContext ctx) {
        PropertyContainer pc = new PropertyContainer(getConfigurationClass(ctx).getContext(), null);
        pc.setName("configuration");
        return pc;
    }

    @Override
    public PropertyContainerClass getContainerClass(PropertyContainerClassContext ctx) {
        return null;
    }
}
