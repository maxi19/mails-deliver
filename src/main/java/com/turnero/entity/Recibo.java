package com.turnero.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.turnero.enums.Estado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Recibo {

		@Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "usuario_creador", nullable = false, length = 20)
		private String usuario;
			       
	    private String path;

	    private String nombre;

		@Enumerated(EnumType.STRING)
	    private Estado estado;

	    private LocalDateTime fecha;
	    
	    private String destinatario;
	    
	    private String email;
	    
}
