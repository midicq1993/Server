package servertest;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HTTPHandlerImpl implements HttpHandler2 {
    public Socket socket;
    private static final String HTML_SIMPLE_RESPONSE = "<h1>Hello World!</h1>";

    public HTTPHandlerImpl(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String readRequest(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine()).append("\n");
        }
        return sb.toString();
    }


    @Override
    public void sendSimpleResponse(PrintWriter writer) {
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println("Content-Language: en");
        writer.println("Content-Length: " + HTML_SIMPLE_RESPONSE.length());
        writer.println("Connection: keep-alive");
        writer.println();

        writer.println(HTML_SIMPLE_RESPONSE);
    }
}
