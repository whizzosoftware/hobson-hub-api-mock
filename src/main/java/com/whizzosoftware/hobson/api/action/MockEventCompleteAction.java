/*
 *******************************************************************************
 * Copyright (c) 2016 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************
*/
package com.whizzosoftware.hobson.api.action;

import com.whizzosoftware.hobson.api.plugin.EventLoopExecutor;
import com.whizzosoftware.hobson.api.plugin.PluginContext;

public class MockEventCompleteAction extends SingleAction {
    private boolean onStartCalled;

    public MockEventCompleteAction(PluginContext pluginContext, ActionExecutionContext executionContext, EventLoopExecutor executor) {
        super(pluginContext, executionContext, executor);
    }

    public boolean isOnStartCalled() {
        return onStartCalled;
    }

    @Override
    public void onStart(ActionLifecycleContext ctx) {
        onStartCalled = true;
    }

    @Override
    public void onMessage(ActionLifecycleContext ctx, String msgName, Object prop) {
        if ("complete".equals(msgName)) {
            ctx.complete();
        }
    }

    @Override
    public void onStop(ActionLifecycleContext ctx) {

    }
}
