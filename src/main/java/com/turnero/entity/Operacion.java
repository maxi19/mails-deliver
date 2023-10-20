package com.turnero.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Operacion {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne (optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "recibo_id", referencedColumnName = "id")
    private ReciboEnviado recibo;


    private boolean enviado;


    private Date Fecha;

    public Operacion() {
    }

    public Operacion(UUID id, ReciboEnviado recibo, boolean enviado, boolean matcheado, Date fecha) {
        this.id = id;
        this.recibo = recibo;
        this.enviado = false;
        Fecha = fecha;
    }
}
