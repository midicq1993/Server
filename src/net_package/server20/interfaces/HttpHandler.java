package net_package.server20.interfaces;


import net_package.exception.HttpMethodException;

import java.util.Map;

public interface HttpHandler {
    String createSimpleResponse(Map<String, String> headers) throws HttpMethodException;
}
