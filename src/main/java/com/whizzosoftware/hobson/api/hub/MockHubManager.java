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

import java.util.ArrayList;
import java.util.Collection;

public class MockHubManager implements HubManager, LocalHubManager {
    @Override
    public Collection<HobsonHub> getHubs(String userId) {
        return new ArrayList<>();
    }

    @Override
    public HobsonHub getHub(HubContext ctx) {
        return null;
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
    public LineRange getLog(HubContext ctx, long startLine, long endLine, Appendable appendable) {
        return null;
    }

    @Override
    public LocalHubManager getLocalManager() {
        return this;
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

    @Override
    public HobsonHub addHub(String userId, String name) {
        return null;
    }

    @Override
    public void deleteConfiguration(HubContext ctx) {

    }

    @Override
    public void removeHub(HubContext ctx) {

    }

    @Override
    public boolean authenticateHub(HubContext ctx, HubCredentials credentials) {
        return false;
    }

    @Override
    public PropertyContainer getConfiguration(HubContext ctx) {
        return null;
    }

    @Override
    public PropertyContainerClass getContainerClass(PropertyContainerClassContext ctx) {
        return null;
    }
}
