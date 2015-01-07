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
import java.util.List;
import java.util.Map;

public class MockHttpChannel implements HttpChannel {
    private final List<URI> getRequests = new ArrayList<>();
    private final List<URI> postRequests = new ArrayList<>();
    private final List<URI> putRequests = new ArrayList<>();
    private final List<URI> deleteRequests = new ArrayList<>();
    private final List<URI> patchRequests = new ArrayList<>();

    @Override
    public void setPlugin(AbstractHttpClientPlugin plugin) {
    }

    @Override
    public void sendHttpGetRequest(URI uri, Map<String, String> headers, Object context) {
        getRequests.add(uri);
    }

    @Override
    public void sendHttpPostRequest(URI uri, Map<String, String> headers, byte[] data, Object context) {
        postRequests.add(uri);
    }

    @Override
    public void sendHttpPutRequest(URI uri, Map<String, String> headers, byte[] data, Object context) {
        putRequests.add(uri);
    }

    @Override
    public void sendHttpDeleteRequest(URI uri, Map<String, String> headers, byte[] data, Object context) {
        deleteRequests.add(uri);
    }

    @Override
    public void sendHttpPatchRequest(URI uri, Map<String, String> headers, byte[] data, Object context) {
        patchRequests.add(uri);
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
