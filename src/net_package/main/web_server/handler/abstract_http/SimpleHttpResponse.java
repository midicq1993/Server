package net_package.main.web_server.handler.abstract_http;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class SimpleHttpResponse {

    private final PrintWriter WRITER;

    private static final String HTML_Response = "<h1>Hello!</h1>";

    public SimpleHttpResponse(Socket socket) throws IOException {
        WRITER = new PrintWriter(socket.getOutputStream(), true);

    }

    public void simpleHeaderResponse() {
        WRITER.println("HTTP/1.1 200 OK");
        WRITER.println("Content-Type: text/html; charset=utf-8");
        WRITER.println("Content-Language: en");
        WRITER.println("Content-Length: " + HTML_Response.length());
        WRITER.println("Connection: close");
        WRITER.println();
    }

    public void sendResponse() {
        WRITER.println(HTML_Response);
    }


    public void writerClose() {
        WRITER.flush();
        WRITER.close();
    }

}
