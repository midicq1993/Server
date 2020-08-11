package net_package.main.web_server.handler;

import net_package.main.web_server.handler.abstract_http.SimpleHttpResponse;
import net_package.main.web_server.handler.exception_http.NullHttpRequestException;
import net_package.main.web_server.handler.exception_http.NullHttpMethodException;
import net_package.main.web_server.handler.interface_http.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpHandlerImpl extends SimpleHttpResponse implements HttpHandler {
    private final BufferedReader READER;
    private final Socket socket;

    public HttpHandlerImpl(Socket socket) throws IOException {
        super(socket);
        this.socket = socket;
        READER = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public String readRequest() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (READER.ready()) {
            sb.append(READER.readLine()).append("\n");
        }
        if (sb.toString().isEmpty() || sb.toString().isBlank()) {
            return "Postman test a connection. Port: " + socket.getPort();
        }
        return sb.toString();
    }

    @Override
    public String extractHttpMethod(String wholeRequest) throws NullHttpRequestException {
        if (wholeRequest == null || wholeRequest.length() == 0) throw new NullHttpRequestException();

        String[] strings = wholeRequest.split(" / ");
        return strings[0];
    }


    @Override
    public void processingRequestAndSendResponse(String method) throws NullHttpMethodException {
        if (method == null || method.length() == 0) throw new NullHttpMethodException();

        if (method.equals(HttpMethodEnum.GET.getMethod())) {
            simpleHeaderResponse();
            sendResponse();
        }
    }

    public void readerClose() throws IOException {
        READER.close();
    }

}
