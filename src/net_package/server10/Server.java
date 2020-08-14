package net_package.server10;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {

    public static void main(String[] args) throws IOException {
        ExecutorService threadPool = new ThreadPoolExecutor(
                8, 64,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(256));

        ServerSocket serverSocket = new ServerSocket(80);
        System.out.println("Server is created. Port: " + serverSocket.getLocalPort());

        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            threadPool.submit(new ThreadPooledServer(socket));
        }
    }
}
