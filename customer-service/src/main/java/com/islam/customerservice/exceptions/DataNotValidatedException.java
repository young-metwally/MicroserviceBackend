package com.islam.customerservice.exceptions;

import javax.management.RuntimeErrorException;

public class DataNotValidatedException extends RuntimeErrorException {

    private static final long serialVersionUID = 1L;

    public DataNotValidatedException(Error e) {
        super(e);
    }


}
