package net_package.main.mockup_server.http_handler.http_interface;

import java.io.IOException;

public interface HttpRequestImpl {

    String readHttpRequest() throws IOException;
}
