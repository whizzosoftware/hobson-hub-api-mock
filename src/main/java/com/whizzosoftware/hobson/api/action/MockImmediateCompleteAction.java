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

/**
 * A mock action that completes immediately.
 */
public class MockImmediateCompleteAction extends SingleAction {
    private boolean onStartCalled;
    private boolean onMessageCalled;
    private boolean onStopCalled;

    public MockImmediateCompleteAction(PluginContext pctx, ActionExecutionContext ectx, EventLoopExecutor executor) {
        super(pctx, ectx, executor);
    }

    public boolean isOnStartCalled() {
        return onStartCalled;
    }

    public boolean isOnMessageCalled() {
        return onMessageCalled;
    }

    public boolean isOnStopCalled() {
        return onStopCalled;
    }

    @Override
    public void onStart(ActionLifecycleContext ctx) {
        onStartCalled = true;
        ctx.complete();
    }

    @Override
    public void onMessage(ActionLifecycleContext ctx, String msgName, Object prop) {
        onMessageCalled = true;
    }

    @Override
    public void onStop(ActionLifecycleContext ctx) {
        onStopCalled = true;
    }
}
