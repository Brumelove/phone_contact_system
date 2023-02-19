package com.mikro.phone_contact_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5218143265247846948L;

    public ElementNotFoundException(Long id) {
        super(String.format("Contact with id %s is not present", id));
    }
}
