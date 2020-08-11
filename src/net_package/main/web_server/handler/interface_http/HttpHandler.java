package net_package.main.web_server.handler.interface_http;

import net_package.main.web_server.handler.exception_http.NullHttpMethodException;
import net_package.main.web_server.handler.exception_http.NullHttpRequestException;

import java.io.IOException;

public interface HttpHandler {

    void processingRequestAndSendResponse(String method) throws NullHttpMethodException;
    String readRequest() throws IOException;
    String extractHttpMethod(String request) throws NullHttpRequestException;

}
