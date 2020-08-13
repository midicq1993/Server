package servertest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface HttpHandler2 {

    String readRequest(BufferedReader reader) throws IOException;
    void sendSimpleResponse(PrintWriter writer) throws IOException;
}
