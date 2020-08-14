package net_package.server20.handler;

public enum ResponseEnum {
    GET("<h1>Server get GET method!</h1>"),
    DELETE("<h1>This request is currently not supported!</h1>");

    private String value;

    ResponseEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
