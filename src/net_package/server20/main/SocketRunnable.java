package net_package.server20.main;


import net_package.exception.HttpFormatException;
import net_package.server20.handler.MyHttpHandler;
import net_package.server20.parser.MyHttpParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;


public class SocketRunnable implements Runnable {
    public Socket socket;

    public SocketRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            //Read and print whole request
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine()).append("\n");
            }
            String wholeRequest = sb.toString();
            System.out.println(wholeRequest);

            //Parse request
            MyHttpParser parser = new MyHttpParser();
            parser.parseRequest(wholeRequest);

            String httpMethod = parser.getHttpMethod();
            String httpVersion = parser.getHttpVersion();
            Map<String, String> headers = parser.getAllHeaders();


            //Create and send response
            MyHttpHandler httpHandler = new MyHttpHandler(httpMethod, httpVersion);
            String response = httpHandler.createSimpleResponse(headers);

            writer.print(response);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
