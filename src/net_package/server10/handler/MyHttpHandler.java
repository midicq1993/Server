package net_package.server10.handler;

import net_package.exception.HttpFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MyHttpHandler implements HttpHandler {
    public Socket socket;
    private static final String HTML_SIMPLE_RESPONSE = "<h1>Hello World!</h1>";

    public MyHttpHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String readRequest(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String extractHttpMethod(String wholeRequest) throws HttpFormatException {
        if (wholeRequest == null || wholeRequest.length() == 0)
            throw new HttpFormatException("Request is invalid: " + wholeRequest);

        String[] strings = wholeRequest.split(" / ");
        return strings[0];
    }


    @Override
    public void methodHandler(String method, PrintWriter writer) throws HttpFormatException {
        if (method == null || method.length() == 0)
            throw new HttpFormatException("http method is incorrect: " + method);

        if (method.equals(HttpMethodEnum.GET.getMethod())) {
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/html; charset=utf-8");
            writer.println("Content-Language: en");
            writer.println("Content-Length: " + HTML_SIMPLE_RESPONSE.length());
            writer.println("Connection: keep-alive");
            writer.println();
            writer.println(HTML_SIMPLE_RESPONSE);
        }
    }
}
