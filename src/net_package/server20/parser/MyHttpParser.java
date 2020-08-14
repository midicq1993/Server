package net_package.server20.parser;


import net_package.exception.HttpFormatException;
import net_package.server20.interfaces.SimpleHttpParser;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MyHttpParser implements SimpleHttpParser {
    private final String wholeRequest;
    private final Map<String, String> requestHeaders = new HashMap<>();

    private String httpVersion;
    private String httpMethod;

    public MyHttpParser(String wholeRequest) {
        this.wholeRequest = wholeRequest;
    }

    @Override
    public void parseRequest() throws HttpFormatException {
        if (this.wholeRequest == null || this.wholeRequest.length() == 0)
            throw new HttpFormatException("Request is invalid: " + this.wholeRequest);

        String delim = "\r\n";
        StringTokenizer tokenizer = new StringTokenizer(this.wholeRequest, delim);

        parseRequestLine(tokenizer.nextToken());

        while (tokenizer.hasMoreTokens()) {
            splitHeaderLine(tokenizer.nextToken());
        }
    }

    private void parseRequestLine(String requestLine) throws HttpFormatException {
        String[] strings = requestLine.split(" / ");
        setHttpMethod(strings[0]);
        setHttpVersion(strings[1]);
    }

    private void splitHeaderLine(String header) throws HttpFormatException {
        int index = header.indexOf(":");
        if (index == -1) throw new HttpFormatException("Invalid header parameter: " + header);

        requestHeaders.put(header.substring(0, index), header.substring(index+1));
    }

    private void setHttpVersion(String httpVersion) throws HttpFormatException {
        if (httpVersion == null || httpVersion.length() == 0)
            throw new HttpFormatException("Request line is incorrect: " + httpVersion);
        this.httpVersion = httpVersion;
    }

    private void setHttpMethod(String httpMethod) throws HttpFormatException {
        if (httpMethod == null || httpMethod.length() == 0)
            throw new HttpFormatException("Request line is incorrect: " + httpMethod);
        this.httpMethod = httpMethod;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public String getHttpMethod() {
        return httpMethod;
    }
}
