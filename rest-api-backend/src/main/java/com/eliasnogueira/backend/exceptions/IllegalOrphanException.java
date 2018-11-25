package com.eliasnogueira.backend.exceptions;

import java.util.ArrayList;
import java.util.List;

public class IllegalOrphanException extends Exception {

    private final List<String> messages;

    public IllegalOrphanException(List<String> messages) {
        super((messages != null && messages.isEmpty() ? messages.get(0) : null));

        if (messages == null) {
            this.messages = new ArrayList<>();
        }
        else {
            this.messages = messages;
        }
    }
    public List<String> getMessages() {
        return messages;
    }
}
