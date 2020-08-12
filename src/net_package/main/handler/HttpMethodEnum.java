package net_package.main.handler;

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