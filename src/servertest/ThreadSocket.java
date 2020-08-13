package servertest;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ThreadSocket implements Runnable {
    public Socket socket;

    public ThreadSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run()  {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter writer = new PrintWriter(this.socket.getOutputStream(), true)) {

            HTTPHandlerImpl httpHandler = new HTTPHandlerImpl(this.socket);

            String request = httpHandler.readRequest(reader);
            System.out.println(request);

            //String method = httpHandler.extractHttpMethod(request);

            httpHandler.sendSimpleResponse(writer);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
