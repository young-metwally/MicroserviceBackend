package com.islam.userservice.exceptions;

import javax.management.RuntimeErrorException;

public class DuplicateEntryException extends RuntimeErrorException {

    private static final long serialVersionUID = 1L;

    public DuplicateEntryException(Error e) {
        super(e);
    }

}

