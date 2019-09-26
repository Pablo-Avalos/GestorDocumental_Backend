package com.gestorDocumental.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "proceso")
@Entity
public class Proceso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Proceso(long id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}
	
	public Proceso() {}
	
	@GeneratedValue
	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;

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
