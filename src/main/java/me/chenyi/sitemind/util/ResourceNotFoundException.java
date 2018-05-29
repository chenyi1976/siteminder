package me.chenyi.sitemind.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String name) {
        super("There is no order with name: " + name);
    }

    public ResourceNotFoundException(long id) {
        super("There is no order with id: " + id);
    }
}
