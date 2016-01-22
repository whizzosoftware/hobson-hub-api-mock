/*******************************************************************************
 * Copyright (c) 2015 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.telemetry;

import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.variable.VariableContext;

import java.util.*;

public class MockTelemetryManager implements TelemetryManager {
    private Set<VariableContext> monitoredVariables = new HashSet<>();

    @Override
    public boolean isStub() {
        return false;
    }

    @Override
    public String createDataStream(HubContext ctx, String name, Collection<VariableContext> data) {
        return null;
    }

    @Override
    public Collection<DataStream> getDataStreams(HubContext ctx) {
        return null;
    }

    @Override
    public DataStream getDataStream(HubContext ctx, String dataStreamId) {
        return null;
    }

    @Override
    public Set<VariableContext> getMonitoredVariables(HubContext ctx) {
        return monitoredVariables;
    }

    @Override
    public void addData(HubContext ctx, String streamName, long now, Map<VariableContext, Object> data) {

    }

    @Override
    public List<TemporalValue> getData(HubContext ctx, String streamName, long endTime, TelemetryInterval interval) {
        return null;
    }

    public void addMonitoredVariable(VariableContext vctx) {
        monitoredVariables.add(vctx);
    }
}
