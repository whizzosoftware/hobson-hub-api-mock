/*******************************************************************************
 * Copyright (c) 2016 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.user;

import com.whizzosoftware.hobson.api.HobsonAuthenticationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MockUserStore implements UserStore {
    private List<String> users = new ArrayList<>();

    @Override
    public boolean hasDefaultUser() {
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
}
