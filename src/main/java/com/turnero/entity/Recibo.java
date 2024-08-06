package com.turnero.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.turnero.dto.ItemEnviable;
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
public class Recibo implements ItemEnviable {

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

		@Override
		public String getFilesName() {
			return getNombre();
		}

		@Override
		public String getFilesPath() {
			return this.getPath();
		}

}
