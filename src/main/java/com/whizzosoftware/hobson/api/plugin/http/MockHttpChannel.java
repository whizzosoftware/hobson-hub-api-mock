/*******************************************************************************
 * Copyright (c) 2014 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package com.whizzosoftware.hobson.api.plugin.http;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MockHttpChannel extends HttpClientChannel {
    private final List<URI> getRequests = new ArrayList<>();
    private final List<URI> postRequests = new ArrayList<>();
    private final List<URI> putRequests = new ArrayList<>();
    private final List<URI> deleteRequests = new ArrayList<>();
    private final List<URI> patchRequests = new ArrayList<>();

    @Override
    public void setPlugin(AbstractHttpClientPlugin plugin) {
    }

    @Override
    protected HttpRequest createHttpRequest(URI uri, HttpRequest.Method method) {
        return null;
    }

    @Override
    protected WebSocketHandle createWebSocket(URI uri, Map<String, Collection<String>> map) {
        return null;
    }

    public List<URI> getGetRequests() {
        return getRequests;
    }

    public List<URI> getPostRequests() {
        return postRequests;
    }

    public List<URI> getPutRequests() {
        return putRequests;
    }

    public List<URI> getDeleteRequests() {
        return deleteRequests;
    }

    public List<URI> getPatchRequests() {
        return patchRequests;
    }
}
