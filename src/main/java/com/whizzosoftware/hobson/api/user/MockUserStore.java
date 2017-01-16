/*
 *******************************************************************************
 * Copyright (c) 2016 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************
*/
package com.whizzosoftware.hobson.api.user;

import com.whizzosoftware.hobson.api.HobsonAuthenticationException;
import com.whizzosoftware.hobson.api.hub.PasswordChange;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MockUserStore implements UserStore {
    @Override
    public boolean hasDefaultUser() {
        return false;
    }

    @Override
    public boolean supportsUserManagement() {
        return false;
    }

    @Override
    public String getDefaultUser() {
        return null;
    }

    @Override
    public UserAuthentication authenticate(String username, String password) throws HobsonAuthenticationException {
        return new UserAuthentication(new HobsonUser(username), "token");
    }

    @Override
    public void changeUserPassword(String username, PasswordChange change) {

    }

    @Override
    public Collection<HobsonUser> getUsers() {
        return null;
    }

    @Override
    public HobsonUser getUser(String username) {
        return null;
    }

    @Override
    public void addUser(String username, String password, String givenName, String familyName, Collection<HobsonRole> roles) {

    }

    @Override
    public Collection<String> getHubsForUser(String username) {
        return null;
    }
}
