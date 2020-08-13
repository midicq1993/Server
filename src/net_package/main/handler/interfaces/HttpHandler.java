package net_package.main.handler.interfaces;

import net_package.main.handler.exception.NullHttpMethodException;
import net_package.main.handler.exception.NullHttpRequestException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface HttpHandler {

    void methodHandler(String method, PrintWriter writer) throws NullHttpMethodException;
    String readRequest(BufferedReader reader) throws IOException;
    String extractHttpMethod(String request) throws NullHttpRequestException;

}
