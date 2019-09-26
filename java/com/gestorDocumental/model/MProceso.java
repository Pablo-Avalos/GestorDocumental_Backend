package com.gestorDocumental.model;

import com.gestorDocumental.entity.Proceso;

public class MProceso {
	String descripcion;
	long id;
	
	public MProceso () {}
	
	public MProceso(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MProceso(Proceso proceso) {
		this.descripcion = proceso.getDescripcion();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
