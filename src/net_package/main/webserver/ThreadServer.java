package net_package.main.webserver;

import net_package.main.handler.HttpHandlerImpl;
import net_package.main.handler.exception.NullHttpMethodException;
import net_package.main.handler.exception.NullHttpRequestException;

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
            System.out.println(Thread.currentThread().getName() + " - this thread is get port: " + socket.getPort());

            HttpHandlerImpl handler = new HttpHandlerImpl(socket);

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




