package net_package.server10.handler;

public enum HttpMethodEnum {
    GET("GET");


    private String method;

    HttpMethodEnum(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
