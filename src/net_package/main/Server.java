package net_package.main;


import net_package.main.mockup_server.HttpHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {


    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2, 32,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(256));

        ServerSocket serverSocket = new ServerSocket(80);
        System.out.println("Server is created. Port: " + serverSocket.getLocalPort());

        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            threadPool.submit(new HttpHandler(socket));
        }
    }
}
