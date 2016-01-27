/*******************************************************************************
 * Copyright (c) 2015 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.telemetry;

import com.whizzosoftware.hobson.api.variable.VariableContext;

import java.util.*;

public class MockTelemetryManager implements TelemetryManager {
    private Set<VariableContext> monitoredVariables = new HashSet<>();

    @Override
    public boolean isStub() {
        return false;
    }

    @Override
    public String createDataStream(String userId, String name, Collection<VariableContext> data) {
        return null;
    }

    @Override
    public Collection<DataStream> getDataStreams(String userId) {
        return null;
    }

    @Override
    public DataStream getDataStream(String userId, String dataStreamId) {
        return null;
    }

    @Override
    public Set<VariableContext> getMonitoredVariables(String userId) {
        return monitoredVariables;
    }

    @Override
    public void addData(String userId, String dataStreamId, long now, Map<VariableContext, Object> data) {

    }

    @Override
    public List<TemporalValueSet> getData(String userId, String dataStreamId, long endTime, TelemetryInterval interval) {
        return null;
    }

    public void addMonitoredVariable(VariableContext vctx) {
        monitoredVariables.add(vctx);
    }
}
