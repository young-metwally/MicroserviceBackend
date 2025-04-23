package com.islam.productservice.exceptions;

import javax.management.RuntimeErrorException;

public class InstanceUndefinedException extends RuntimeErrorException {

    public InstanceUndefinedException(Error e) {
        super(e);
    }
}
