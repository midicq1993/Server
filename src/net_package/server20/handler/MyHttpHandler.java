package net_package.server20.handler;



import net_package.exception.HttpFormatException;

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
    public String createSimpleResponse(Map<String, String> headers) throws HttpFormatException {
        StringBuilder strBuilder = new StringBuilder();

        strBuilder.append(createResponseLine()).append("\r\n");

        for (Map.Entry<String, String> pair : headers.entrySet()) {
            strBuilder
                    .append(pair.getKey())
                    .append(":")
                    .append(pair.getValue())
                    .append("\r\n");
        }
        strBuilder.append("\r\n");

        if (!(httpMethod.equalsIgnoreCase("POST")))
            strBuilder.append(createBodyMessage());

        return strBuilder.toString();
    }

    private String createResponseLine() throws HttpFormatException {
        if (httpVersion == null || httpVersion.length() == 0)
            throw new HttpFormatException("http version is invalid: " + httpVersion);

        return httpVersion+" "+ createHttpResponseCode();
    }

    private String createHttpResponseCode() throws HttpFormatException {
        if (httpMethod == null || httpMethod.length() == 0)
            throw new HttpFormatException("http method is invalid: " + httpMethod);

        String httpResponseCode = "";
        if (httpMethod.equalsIgnoreCase("GET")) {
            for (Map.Entry<String, String> pair : httpReplies.entrySet()) {
                if (pair.getKey().equals("200")){
                    httpResponseCode = pair.getKey()+" "+pair.getValue();
                }
            }
        } else if (httpMethod.equalsIgnoreCase("POST")) {
            for (Map.Entry<String, String> pair : httpReplies.entrySet()) {
                if (pair.getKey().equals("201")){
                    httpResponseCode = pair.getKey()+" "+pair.getValue();
                }
            }
        } else if (httpMethod.equalsIgnoreCase("DELETE")) {
            for (Map.Entry<String, String> pair : httpReplies.entrySet()) {
                if (pair.getKey().equals("400")){
                    httpResponseCode = pair.getKey()+" "+pair.getValue();
                }
            }
        }
        return httpResponseCode;
    }

    private String createBodyMessage() {
        if (httpMethod.equalsIgnoreCase("GET")) {
            return ResponseEnum.GET.getValue();
        }
        return ResponseEnum.DELETE.getValue();
    }
}
