/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.hub;


import com.whizzosoftware.hobson.api.config.EmailConfiguration;

import java.util.ArrayList;
import java.util.Collection;

public class MockHubManager implements HubManager, HubRegistrar, LocalHubManager {
    private EmailConfiguration emailConfiguration;

    @Override
    public Collection<HobsonHub> getHubs(String userId) {
        return new ArrayList<>();
    }

    @Override
    public HobsonHub getHub(HubContext ctx) {
        return null;
    }

    @Override
    public void setHubDetails(HobsonHub hubDetails) {

    }

    @Override
    public void clearHubDetails(HubContext ctx) {

    }

    @Override
    public void setHubPassword(HubContext ctx, PasswordChange passwordChange) {

    }

    @Override
    public boolean authenticateAdmin(HubContext ctx, String password) {
        return false;
    }

    @Override
    public void sendTestEmail(HubContext ctx, EmailConfiguration config) {

    }

    @Override
    public void sendEmail(HubContext ctx, String recipientAddress, String subject, String body) {

    }

    @Override
    public LineRange getLog(HubContext ctx, long startLine, long endLine, Appendable appendable) {
        return null;
    }

    @Override
    public HubRegistrar getRegistrar() {
        return this;
    }

    @Override
    public LocalHubManager getLocalManager() {
        return this;
    }

    @Override
    public void addErrorLogAppender(Object o) {

    }

    @Override
    public void removeLogAppender(Object o) {

    }

    @Override
    public HobsonHub addHub(String userId, String name) {
        return null;
    }

    @Override
    public void removeHub(String userId, String hubId) {

    }
}
