package net_package.main.web_server;

import net_package.main.web_server.handler.HttpHandlerImpl;
import net_package.main.web_server.handler.exception_http.NullHttpMethodException;
import net_package.main.web_server.handler.exception_http.NullHttpRequestException;

import java.io.IOException;
import java.net.Socket;

public class ThreadServer implements Runnable {
    private final Socket socket;

    public ThreadServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client is connected to server. Client port: " + socket.getPort());

            HttpHandlerImpl handler = new HttpHandlerImpl(socket);

            if (socket.isClosed()) {
                handler.writerClose();
                handler.readerClose();
            }

            String request = handler.readRequest();
            System.out.println(request);

            String httpMethod = handler.extractHttpMethod(request);
            handler.processingMethodAndSendResponse(httpMethod);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullHttpRequestException e) {
            System.out.println("HTTP request is incorrect or null");
        } catch (NullHttpMethodException e) {
            System.out.println("HTTP method is incorrect or null");
        }

    }
}




