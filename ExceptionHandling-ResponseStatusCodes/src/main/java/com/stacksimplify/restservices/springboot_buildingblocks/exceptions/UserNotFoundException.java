package com.stacksimplify.restservices.springboot_buildingblocks.exceptions;

import java.io.Serial;

/*Handling custom exceptions
* Serialization: It's the process of converting an object into a stream of bytes that can be stored or transmitted.
Deserialization: It's the process of reconstructing an object from a stream of bytes.
*
* The @Serial annotation (introduced in Java 11) simplifies the process.
* It automatically calculates a serialVersionUID based on the class definition at compile time.
* This eliminates the need to manually maintain a version number, which can become outdated if the
* class structure changes.
* */
public class UserNotFoundException extends Exception{
    @Serial
    private static final long serialVersionUID = 4441390041344039823L;

    public UserNotFoundException(String cause) {
        super(cause);
    }
}
