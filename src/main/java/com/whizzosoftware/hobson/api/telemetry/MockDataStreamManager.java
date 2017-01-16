/*******************************************************************************
 * Copyright (c) 2015 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.telemetry;

import com.whizzosoftware.hobson.api.data.*;
import com.whizzosoftware.hobson.api.hub.HubContext;
import com.whizzosoftware.hobson.api.variable.DeviceVariableContext;

import java.util.*;

public class MockDataStreamManager implements DataStreamManager {
    private Set<DeviceVariableContext> monitoredVariables = new HashSet<>();

    @Override
    public boolean isStub() {
        return false;
    }

    @Override
    public String createDataStream(HubContext ctx, String name, Collection<DataStreamField> data, Set<String> tags) {
        return null;
    }

    @Override
    public void deleteDataStream(HubContext ctx, String dataStreamId) {

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
    public void addData(HubContext ctx, String dataStreamId, long now, Map<String, Object> data) {

    }

    @Override
    public List<DataStreamValueSet> getData(HubContext ctx, String dataStreamId, long endTime, DataStreamInterval interval) {
        return null;
    }
}
