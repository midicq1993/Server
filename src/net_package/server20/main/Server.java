package net_package.server20.main;


public class Server {
    public static void main(String[] args) {
        ThreadPooledServer threadPooledServer = new ThreadPooledServer(80);
        new Thread(threadPooledServer).start();

    }
}
