package net_package.main.handler;

import net_package.main.handler.abstract_classes.SimpleResponseWriter;
import net_package.main.handler.exception.NullHttpRequestException;
import net_package.main.handler.exception.NullHttpMethodException;
import net_package.main.handler.interfaces.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HttpHandlerImpl extends SimpleResponseWriter implements HttpHandler {
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
    public void processingMethodAndSendResponse(String method) throws NullHttpMethodException {
        if (method == null || method.length() == 0) throw new NullHttpMethodException();

        if (method.equals(HttpMethodEnum.GET.getMethod())) {
            sendHeaderResponse();
            sendBodyResponse();
        }
    }
}
