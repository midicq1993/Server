package net_package.server20;



import net_package.exception.HttpFormatException;
import net_package.exception.HttpMethodException;
import net_package.server20.handler.MyHttpHandler;
import net_package.server20.parser.MyHttpParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;


public class ThreadWorker implements Runnable {
    public Socket socket;

    public ThreadWorker(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run()  {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter writer = new PrintWriter(this.socket.getOutputStream(), true)) {

            //READ Request
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append(reader.readLine()).append("\n");
            }
            String wholeRequest = sb.toString();
            System.out.println(wholeRequest);

            //PARSE Request
            MyHttpParser parser = new MyHttpParser(wholeRequest);
            parser.parseRequest();

            String httpMethod = parser.getHttpMethod();
            String httpVersion = parser.getHttpVersion();
            Map<String, String> headers = parser.getRequestHeaders();


            //CREATE Response
            MyHttpHandler httpHandler = new MyHttpHandler(httpMethod, httpVersion);
            String response = httpHandler.createSimpleResponse(headers);

            //WRITE Response
            writer.print(response);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (HttpFormatException | HttpMethodException e) {
            System.out.println(e.getMessage());
        }

    }
}
