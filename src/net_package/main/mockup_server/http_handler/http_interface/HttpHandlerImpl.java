package net_package.main.mockup_server.http_handler.http_interface;

import net_package.main.mockup_server.http_handler.exception.NullHttpMethodException;
import net_package.main.mockup_server.http_handler.exception.NullHttpRequestException;

public interface HttpHandlerImpl {

    void handler(String method) throws NullHttpMethodException, NullHttpMethodException;
    String extractHttpMethod(String request) throws NullHttpRequestException, NullHttpRequestException;
}
