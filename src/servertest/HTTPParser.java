package servertest;


import net_package.main.handler.exception.NullHttpRequestException;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class HTTPParser implements Parser {
    public String wholeRequest;
    public String requestLine;
    public Map<String, String> requestHeaders = new HashMap<>();


    public HTTPParser(String wholeRequest) {
        this.wholeRequest = wholeRequest;
    }

    @Override
    public String extractHttpMethod() {
        String[] strings = this.wholeRequest.split(" / ");
        return strings[0];
    }

    @Override
    public void parseRequest(String wholeRequest) throws NullHttpRequestException {
        if (wholeRequest == null || wholeRequest.length() < 4)
            throw new NullHttpRequestException("request is invalid: " + wholeRequest);

        String delim = "\r\n";
        StringTokenizer tokenizer = new StringTokenizer(this.wholeRequest, delim);

        setRequestLine(tokenizer.nextToken());
        while (tokenizer.hasMoreTokens()) {
            appendHeaderParameter(tokenizer.nextToken());
        }

    }

    public void appendHeaderParameter(String header) throws NullHttpRequestException {
        int index = header.indexOf(":");
        if (index == -1) throw new NullHttpRequestException("Invalid header parameter: " + header);

        requestHeaders.put(header.substring(0, index), header.substring(index+1));
    }


    public void setRequestLine(String requestLine) throws NullHttpRequestException {
        if (requestLine == null || requestLine.length() == 0)
            throw new NullHttpRequestException("Request line is incorrect: " + requestLine);
        this.requestLine = requestLine;
    }
}
