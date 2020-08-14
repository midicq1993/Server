package net_package.server20.interfaces;

import net_package.exception.HttpFormatException;

public interface SimpleHttpParser {
    void parseRequest() throws HttpFormatException;
}
