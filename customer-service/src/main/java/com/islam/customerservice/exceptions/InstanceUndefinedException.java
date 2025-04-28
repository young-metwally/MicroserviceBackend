package com.islam.customerservice.exceptions;

import javax.management.RuntimeErrorException;

public class InstanceUndefinedException extends RuntimeErrorException {

    private static final long serialVersionUID = 1L;

    public InstanceUndefinedException(Error e) {
        super(e);
    }

}
