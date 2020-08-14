package net_package.server20.handler;



import net_package.exception.HttpMethodException;
import net_package.server20.interfaces.HttpHandler;

import java.util.Map;


public class MyHttpHandler implements HttpHandler {
    public static final Map<String, String> httpReplies = Map.of(
            "200", "OK",
            "201", "Created",
            "400", "Bad Request"
    );

    private final String httpMethod;
    private final String httpVersion;

    public MyHttpHandler(String httpMethod, String httpVersion) {
        this.httpMethod = httpMethod;
        this.httpVersion = httpVersion;
    }

    @Override
    public String createSimpleResponse(Map<String, String> headers) throws HttpMethodException {
        StringBuilder sb = new StringBuilder();
        sb.append(createResponseLine()).append("\r\n");

        for (Map.Entry<String, String>pair:headers.entrySet()) {
            sb.append(pair.getKey()).append(":").append(pair.getValue()).append("\r\n");
        }
        sb.append("\r\n");

        if (!this.httpMethod.equalsIgnoreCase("POST"))
            sb.append(createBodyMessage());

        return sb.toString();
    }

    private String createResponseLine() throws HttpMethodException {
        return this.httpVersion+" "+ createHttpResponseCode();
    }

    private String createHttpResponseCode() throws HttpMethodException {
        if (this.httpMethod == null || this.httpMethod.length() == 0)
            throw new HttpMethodException("HTTP method is incorrect");

        String httpResponseCode = "";
        if (this.httpMethod.equalsIgnoreCase("GET")) {
            for (Map.Entry<String, String> pair : httpReplies.entrySet()) {
                if (pair.getKey().equals("200")){
                    httpResponseCode = pair.getKey()+" "+pair.getValue();
                }
            }
        } else if (this.httpMethod.equalsIgnoreCase("POST")) {
            for (Map.Entry<String, String> pair : httpReplies.entrySet()) {
                if (pair.getKey().equals("201")){
                    httpResponseCode = pair.getKey()+" "+pair.getValue();
                }
            }
        } else if (this.httpMethod.equalsIgnoreCase("DELETE")) {
            for (Map.Entry<String, String> pair : httpReplies.entrySet()) {
                if (pair.getKey().equals("400")){
                    httpResponseCode = pair.getKey()+" "+pair.getValue();
                }
            }
        }
        return httpResponseCode;
    }

    private String createBodyMessage() throws HttpMethodException {
        if (this.httpMethod.equalsIgnoreCase("POST"))
            throw new HttpMethodException("can`t return body message as response to POST request");

        if (this.httpMethod.equalsIgnoreCase("GET")) {
            return ResponseEnum.GET.getValue();
        } else return ResponseEnum.DELETE.getValue();
    }
}
