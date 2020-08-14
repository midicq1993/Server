package net_package.server20.parser;

import net_package.exception.HttpFormatException;

public interface SimpleHttpParser {
    void parseRequest() throws HttpFormatException;
}
