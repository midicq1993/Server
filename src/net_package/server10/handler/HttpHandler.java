package net_package.server10.handler;

import net_package.exception.HttpFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface HttpHandler {

    void methodHandler(String method, PrintWriter writer) throws HttpFormatException;
    String readRequest(BufferedReader reader) throws IOException;
    String extractHttpMethod(String request) throws HttpFormatException;

}
