package com.gestorDocumental.model;

import java.util.Set;

import com.gestorDocumental.entity.Proceso;
import com.gestorDocumental.entity.SubProceso;

public class MProceso {
	String descripcion;
	long id;
	Set<SubProceso> subProcesos;
	
	public MProceso () {}
	
	public MProceso(Integer id, String descripcion,Set<SubProceso> subProcesos) {
		this.id = id;
		this.descripcion = descripcion;		
		this.subProcesos = subProcesos;
	}
	
	public Set<SubProceso> getSubProcesos() {
		return subProcesos;
	}

	public void setSubProcesos(Set<SubProceso> subProcesos) {
		this.subProcesos = subProcesos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MProceso(Proceso proceso) {
		this.id = proceso.getId();
		this.descripcion = proceso.getDescripcion();
		this.subProcesos = proceso.getSubProcesos();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
