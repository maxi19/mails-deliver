package com.turnero.dto;

import com.turnero.entity.Personal;
import com.turnero.entity.ReciboSinIdentificar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioSinMatchDto {

    private Personal personal;

    private ReciboSinIdentificar reciboSinIdentificar;
}
