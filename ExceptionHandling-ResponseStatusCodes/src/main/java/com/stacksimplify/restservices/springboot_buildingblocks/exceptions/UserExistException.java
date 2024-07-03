package com.stacksimplify.restservices.springboot_buildingblocks.exceptions;

import java.io.Serial;

public class UserExistException extends Exception{

    @Serial
    private static final long serialVersionUID = -8157109657436746995L;

    public UserExistException(String message) {
        super(message);
    }
}
