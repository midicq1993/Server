package net_package.main.mockup_server.handler.http_interface;

import net_package.main.mockup_server.handler.exception.NullHttpMethodException;
import net_package.main.mockup_server.handler.exception.NullHttpRequestException;

public interface HttpHandlerImpl {

    void handler(String method) throws NullHttpMethodException;
    String extractHttpMethod(String request) throws NullHttpRequestException;
}
