package servertest;

import net_package.main.handler.exception.NullHttpRequestException;

public interface Parser {


    String extractHttpMethod();
    void parseRequest(String wholeRequest) throws NullHttpRequestException;
}
