package com.turnero.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileMessage {
    private String message;

    public FileMessage(String message) {
        this.message = message;
    }

}
