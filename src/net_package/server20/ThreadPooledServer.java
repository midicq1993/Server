package net_package.server20;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class ThreadPooledServer implements Runnable {

    public final int port;
    public ServerSocket serverSocket;
    public ExecutorService threadPool = new ThreadPoolExecutor(
            10, 64,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(256));


    public ThreadPooledServer(int port) {
        this.port = port;
    }


    @Override
    public void run()  {
        openServerSocket();
        while (!serverSocket.isClosed()) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("Client is connected. Port: " + socket.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
            threadPool.submit(new ThreadWorker(socket));
        }

    }

    private void openServerSocket()  {
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("Server created on port: " + this.serverSocket.getLocalPort());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
