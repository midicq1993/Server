package net_package.main.handler.abstract_classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class SimpleResponseWriter {

    private final PrintWriter WRITER;

    private static final String HTML_SIMPLE_RESPONSE = "<h1>Hello!</h1>";

    public SimpleResponseWriter(Socket socket) throws IOException {
        WRITER = new PrintWriter(socket.getOutputStream(), true);

    }

    public void sendHeaderResponse() {
        WRITER.println("HTTP/1.1 200 OK");
        WRITER.println("Content-Type: text/html; charset=utf-8");
        WRITER.println("Content-Language: en");
        WRITER.println("Content-Length: " + HTML_SIMPLE_RESPONSE.length());
        WRITER.println("Connection: keep-alive");
        WRITER.println();
    }

    public void sendBodyResponse() {
        WRITER.println(HTML_SIMPLE_RESPONSE);
    }


    public void writerClose() {
        WRITER.flush();
        WRITER.close();
    }

}
