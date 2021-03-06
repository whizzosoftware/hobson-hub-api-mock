/*******************************************************************************
 * Copyright (c) 2015 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.presence;

import com.whizzosoftware.hobson.api.hub.HubContext;

import java.util.*;

public class MockPresenceManager implements PresenceManager {
    private Map<PresenceEntityContext,PresenceEntity> presenceEntityList = new HashMap<>();
    private Map<PresenceLocationContext,PresenceLocation> locationMap = new HashMap<>();
    private Map<PresenceEntityContext,PresenceLocationContext> entityLocations = new HashMap<>();

    @Override
    public Collection<PresenceEntity> getAllPresenceEntities(HubContext ctx) {
        return presenceEntityList.values();
    }

    @Override
    public PresenceEntity getPresenceEntity(PresenceEntityContext ctx) {
        return presenceEntityList.get(ctx);
    }

    @Override
    public PresenceEntityContext addPresenceEntity(HubContext hctx, String name) {
        PresenceEntityContext ctx = PresenceEntityContext.create(hctx, UUID.randomUUID().toString());
        presenceEntityList.put(ctx, new PresenceEntity(ctx, name));
        return ctx;
    }

    @Override
    public void deletePresenceEntity(PresenceEntityContext ctx) {
        presenceEntityList.remove(ctx);
    }

    @Override
    public PresenceLocation getPresenceEntityLocation(PresenceEntityContext ctx) {
        return getPresenceLocation(entityLocations.get(ctx));
    }

    @Override
    public void updatePresenceEntityLocation(PresenceEntityContext ectx, PresenceLocationContext lctx) {
        entityLocations.put(ectx, lctx);
    }

    @Override
    public Collection<PresenceLocation> getAllPresenceLocations(HubContext ctx) {
        return locationMap.values();
    }

    @Override
    public PresenceLocation getPresenceLocation(PresenceLocationContext ctx) {
        return locationMap.get(ctx);
    }

    @Override
    public PresenceLocationContext addPresenceLocation(HubContext hctx, String name, Double latitude, Double longitude, Double radius, Integer beaconMajor, Integer beaconMinor) {
        PresenceLocationContext ctx = PresenceLocationContext.create(hctx, UUID.randomUUID().toString());
        locationMap.put(ctx, new PresenceLocation(ctx, name, latitude, longitude, radius, beaconMajor, beaconMinor));
        return ctx;
    }

    @Override
    public void deletePresenceLocation(PresenceLocationContext ctx) {

    }
}
