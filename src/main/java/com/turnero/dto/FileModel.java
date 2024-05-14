package com.turnero.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileModel {
    private String name;
    private String url;

    public FileModel(String name, String url) {
       this.name = name;
       this.url = url;
    }
}
