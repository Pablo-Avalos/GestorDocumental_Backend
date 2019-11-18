package com.gestorDocumental.model;

import com.gestorDocumental.entity.TipoDocumento;

public class MTipoDocumento {
	private long id;
	private String descripcion;
	
	public MTipoDocumento(){}
	
	public MTipoDocumento(String descripcion){
		this.descripcion = descripcion;
	}
	
	public MTipoDocumento(TipoDocumento tipoDocumento){
		this.id = tipoDocumento.getId();
		this.descripcion = tipoDocumento.getDescripcion();
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
}
