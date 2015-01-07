/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.hub;

public class MockHubManager implements HubManager {
    private EmailConfiguration emailConfiguration;

    @Override
    public String getHubName(String s, String s1) {
        return null;
    }

    @Override
    public void setHubName(String s, String s1, String s2) {

    }

    @Override
    public void setHubPassword(String s, String s1, PasswordChange passwordChange) {

    }

    @Override
    public boolean authenticateAdmin(String s, String s1, String s2) {
        return false;
    }

    @Override
    public HubLocation getHubLocation(String s, String s1) {
        return null;
    }

    @Override
    public void setHubLocation(String s, String s1, HubLocation hubLocation) {

    }

    @Override
    public EmailConfiguration getHubEmailConfiguration(String s, String s1) {
        return emailConfiguration;
    }

    @Override
    public void setHubEmailConfiguration(String s, String s1, EmailConfiguration emailConfiguration) {
        this.emailConfiguration = emailConfiguration;
    }

    @Override
    public boolean isSetupWizardComplete(String s, String s1) {
        return false;
    }

    @Override
    public void setSetupWizardComplete(String s, String s1, boolean b) {

    }

    @Override
    public String getLogLevel(String s, String s1) {
        return null;
    }

    @Override
    public void setLogLevel(String s, String s1, String s2) {

    }

    @Override
    public LogContent getLog(String s, String s1, long l, long l1) {
        return null;
    }

    @Override
    public void addErrorLogAppender(Object o) {

    }

    @Override
    public void removeLogAppender(Object o) {

    }
}
