package net_package.server20;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class ThreadPooledServer implements Runnable {
    private final int port;
    private ServerSocket serverSocket;
    private final ExecutorService threadPool = new ThreadPoolExecutor(
            10, 64,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(256));

    private boolean isStopped = false;

    public ThreadPooledServer(int port) {
        this.port = port;
    }

    @Override
    public void run()  {
        openServerSocket();
        while (!isStopped()) {
            Socket socket;
            try {
                socket = serverSocket.accept();
                System.out.println("Client is connected. Port: " + socket.getPort());
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    break;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            threadPool.submit(new ThreadWorker(socket));
        }
        this.threadPool.shutdown();
        System.out.println("Server stopped");

    }

    public synchronized boolean isStopped() {
        return isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
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
