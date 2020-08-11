package net_package.main.mockup_server.handler;

import net_package.main.mockup_server.handler.abstract_class.SimpleResponseWriter;
import net_package.main.mockup_server.handler.exception.NullHttpRequestException;
import net_package.main.mockup_server.handler.exception.NullHttpMethodException;
import net_package.main.mockup_server.handler.http_interface.HttpHandlerImpl;

import java.io.IOException;
import java.net.Socket;

public class HttpRequestHandler extends SimpleResponseWriter implements HttpHandlerImpl {

    public HttpRequestHandler(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public String extractHttpMethod(String wholeRequest) throws NullHttpRequestException {
        if (wholeRequest == null || wholeRequest.length() == 0) throw new NullHttpRequestException();

        String[] strings = wholeRequest.split(" / ");
        return strings[0];
    }

    @Override
    public void handler(String method) throws NullHttpMethodException {
        if (method == null || method.length() == 0) throw new NullHttpMethodException();

        if (method.equals(HttpMethodEnum.GET.getMethod())) {
            simpleHeaderResponse();
            sendResponse();
        }
    }
}
