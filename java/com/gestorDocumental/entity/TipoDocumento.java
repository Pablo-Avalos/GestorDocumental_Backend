package com.gestorDocumental.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tipoDocumento")
@Entity

public class TipoDocumento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "id")
	private long id;
	
	
	@Column(name = "DESCRIPCION",unique=true)
	private String descripcion;

	public TipoDocumento() {}
	
	public TipoDocumento(String descripcion,Set<SubProceso> subProcesos) {
		this.descripcion = descripcion;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
