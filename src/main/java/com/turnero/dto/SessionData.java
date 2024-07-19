package com.turnero.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SessionData {
    private String usuario;
    private String pathFolderName;
    private String pathFolderBandeja;
}
