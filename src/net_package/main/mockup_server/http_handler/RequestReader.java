package net_package.main.mockup_server.http_handler;

import net_package.main.mockup_server.http_handler.http_interface.HttpRequestImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestReader implements HttpRequestImpl {
    private final BufferedReader READER;
    private final Socket socket;


    public RequestReader(Socket socket) throws IOException {
        this.socket = socket;
        READER = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public String readHttpRequest() throws IOException {
        StringBuilder sb = new StringBuilder();
        while (READER.ready()) {
            sb.append(READER.readLine()).append("\n");
        }
        if (sb.toString().isEmpty() || sb.toString().isBlank()) {
            return "Postman is testing a connection. Port: " + this.socket.getPort();
        }

        return sb.toString();
    }

    public void close() throws IOException {
        READER.close();
    }

}
