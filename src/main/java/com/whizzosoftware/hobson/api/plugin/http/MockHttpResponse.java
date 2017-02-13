/*
 *******************************************************************************
 * Copyright (c) 2017 Whizzo Software, LLC.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************
*/
package com.whizzosoftware.hobson.api.plugin.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Collection;
import java.util.Map;

public class MockHttpResponse implements HttpResponse {
    private int statusCode;
    private String body;
    private Map<String,Collection<String>> headers;
    private Collection<Cookie> cookies;

    public MockHttpResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public MockHttpResponse(int statusCode, String body, Map<String, Collection<String>> headers) {
        this(statusCode, body);
        this.headers = headers;
    }

    public MockHttpResponse(int statusCode, String body, Map<String, Collection<String>> headers, Collection<Cookie> cookies) {
        this(statusCode, body, headers);
        this.cookies = cookies;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getStatusText() {
        return null;
    }

    @Override
    public boolean hasHeader(String name) {
        return (headers != null && headers.containsKey(name));
    }

    @Override
    public Collection<String> getHeader(String name) {
        return (headers != null) ? headers.get(name) : null;
    }

    @Override
    public Map<String, Collection<String>> getHeaders() {
        return headers;
    }

    @Override
    public boolean hasCookies() {
        return (cookies != null);
    }

    @Override
    public Collection<Cookie> getCookies() {
        return cookies;
    }

    @Override
    public String getBody() throws IOException {
        return body;
    }

    @Override
    public InputStream getBodyAsStream() throws IOException {
        return new StringBufferInputStream(body);
    }
}
