package net_package.server10;

import net_package.server10.handler.MyHttpHandler;
import net_package.exception.HttpFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadPooledServer implements Runnable {
    private final Socket socket;

    public ThreadPooledServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Client is connected. Port: " + socket.getPort());
        System.out.println("This thread - \"" + Thread.currentThread().getName() + "\" get port: " + socket.getPort());

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
             PrintWriter writer = new PrintWriter(this.socket.getOutputStream(), true)) {

            MyHttpHandler handler = new MyHttpHandler(this.socket);

            String request = handler.readRequest(bufferedReader);
            System.out.println(request);

            String httpMethod = handler.extractHttpMethod(request);

            handler.methodHandler(httpMethod, writer);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}




