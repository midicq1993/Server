package net_package.main.handler.interfaces;

import net_package.main.handler.exception.NullHttpMethodException;
import net_package.main.handler.exception.NullHttpRequestException;

import java.io.IOException;

public interface HttpHandler {

    void processingMethodAndSendResponse(String method) throws NullHttpMethodException;
    String readRequest() throws IOException;
    String extractHttpMethod(String request) throws NullHttpRequestException;

}
