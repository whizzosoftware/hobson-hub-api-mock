/*
 *******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************
*/
package com.whizzosoftware.hobson.api.event;

import com.whizzosoftware.hobson.api.hub.HubContext;

import java.util.ArrayList;
import java.util.List;

public class MockEventManager implements EventManager {
    private List<Object> listeners = new ArrayList<>();
    private List<HobsonEvent> events = new ArrayList<>();

    @Override
    public void addListener(HubContext ctx, Object listener) {
        listeners.add(listener);
    }

    @Override
    public void addListener(HubContext ctx, Object listener, EventCallbackInvoker runnable) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HubContext ctx, Object listener) {
        listeners.remove(listener);
    }

    @Override
    public void postEvent(HubContext ctx, HobsonEvent event) {
        events.add(event);
    }

    public boolean hasEvents() {
        return (getEventCount() > 0);
    }

    public int getEventCount() {
        return events.size();
    }

    public HobsonEvent getEvent(int ix) {
        return events.get(ix);
    }

    public void clearEvents() {
        events.clear();
    }
}
