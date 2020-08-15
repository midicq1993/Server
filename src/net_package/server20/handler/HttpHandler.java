package net_package.server20.handler;


import net_package.exception.HttpFormatException;

import java.util.Map;

public interface HttpHandler {
    String createSimpleResponse(Map<String, String> headers) throws HttpFormatException;
}
