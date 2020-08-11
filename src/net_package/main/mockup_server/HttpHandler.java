package net_package.main.mockup_server;

import net_package.main.mockup_server.http_handler.HttpRequestHandler;
import net_package.main.mockup_server.http_handler.RequestReader;
import net_package.main.mockup_server.http_handler.exception.NullHttpMethodException;
import net_package.main.mockup_server.http_handler.exception.NullHttpRequestException;

import java.io.IOException;
import java.net.Socket;

public class HttpHandler implements Runnable {
    private final Socket socket;

    public HttpHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client is connected to server. Client port: " + socket.getPort());

            //Read request and print to console
            RequestReader reader = new RequestReader(socket);
            String requestAsString = reader.readHttpRequest();
            System.out.println(requestAsString);

            //Process request and send response
            HttpRequestHandler requestHandler = new HttpRequestHandler(socket);
            String httpMethod = requestHandler.extractHttpMethod(requestAsString);
            requestHandler.handler(httpMethod);

            if (socket.isClosed()) {
                reader.close();
                requestHandler.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullHttpRequestException e) {
            System.out.println("HTTP request is incorrect or null");
        } catch (NullHttpMethodException e) {
            System.out.println("HTTP method is incorrect or null");
        }

    }
}




