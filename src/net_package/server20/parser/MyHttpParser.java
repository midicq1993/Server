package net_package.server20.parser;


import net_package.exception.HttpFormatException;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MyHttpParser implements SimpleHttpParser {
    private final Map<String, String> allHeaders = new HashMap<>();

    private String httpMethod;
    private String httpPath;
    private String httpVersion;

    public MyHttpParser() {
    }

    @Override
    public void parseRequest(String wholeRequest) throws HttpFormatException {
        if (wholeRequest == null || wholeRequest.length() == 0)
            throw new HttpFormatException("Request is invalid: " + wholeRequest);

        String delim = "\r\n";
        StringTokenizer tokenizer = new StringTokenizer(wholeRequest, delim);

        parseRequestLine(tokenizer.nextToken());

        while (tokenizer.hasMoreTokens()) {
            splitHeaderLine(tokenizer.nextToken());
        }
    }

    private void parseRequestLine(String requestLine) throws HttpFormatException {
        String[] strings = requestLine.split(" ");
        setHttpMethod(strings[0]);
        setHttpPath(strings[1]);
        setHttpVersion(strings[2]);
    }

    private void splitHeaderLine(String header) throws HttpFormatException {
        int index = header.indexOf(":");
        if (index == -1) throw new HttpFormatException("Invalid header parameter: " + header);

        allHeaders.put(header.substring(0, index), header.substring(index+1));
    }

    private void setHttpVersion(String httpVersion) throws HttpFormatException {
        if (httpVersion == null || httpVersion.length() == 0)
            throw new HttpFormatException("Request version is incorrect: " + httpVersion);
        this.httpVersion = httpVersion;
    }

    private void setHttpMethod(String httpMethod) throws HttpFormatException {
        if (httpMethod == null || httpMethod.length() == 0)
            throw new HttpFormatException("Request method is incorrect: " + httpMethod);
        this.httpMethod = httpMethod;
    }

    public void setHttpPath(String httpPath) throws HttpFormatException {
        if (httpPath == null || httpPath.length() == 0)
            throw new HttpFormatException("Request method is incorrect: " + httpPath);
        this.httpPath = httpPath;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public Map<String, String> getAllHeaders() {
        return allHeaders;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getHttpPath() {
        return httpPath;
    }
}
