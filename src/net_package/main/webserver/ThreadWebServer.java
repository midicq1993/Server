package net_package.main.webserver;

import net_package.main.handler.HttpHandlerImpl;
import net_package.main.handler.exception.NullHttpMethodException;
import net_package.main.handler.exception.NullHttpRequestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadWebServer implements Runnable {
    private final Socket socket;

    public ThreadWebServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Client is connected. Port: " + socket.getPort());
        System.out.println("This thread - \"" + Thread.currentThread().getName() + "\" get port: " + socket.getPort());

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter writer = new PrintWriter(this.socket.getOutputStream(), true)) {

            HttpHandlerImpl handler = new HttpHandlerImpl(this.socket);

            String request = handler.readRequest(bufferedReader);
            System.out.println(request);

            String httpMethod = handler.extractHttpMethod(request);

            handler.methodHandler(httpMethod, writer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullHttpRequestException e) {
            System.out.println("HTTP request is incorrect or null");
        } catch (NullHttpMethodException e) {
            System.out.println("HTTP method is incorrect or null");
        }
    }
}




