package com.gestorDocumental.model;

import java.util.HashSet;
import java.util.Set;

import com.gestorDocumental.entity.Proceso;
import com.gestorDocumental.entity.SubProceso;
import com.gestorDocumental.entity.TipoDocumento;

public class MSubProceso {
	String descripcion;
	long id;
	long subproceso;
	Proceso procesoObjeto;
	private Set<TipoDocumento> tiposDocumentos = new HashSet<>();

	public MSubProceso() {}
	
	public MSubProceso(long subProceso, String descripcion, Proceso proceso, Set<TipoDocumento> tiposDocumentos) {
		this.subproceso = subProceso;
		this.descripcion = descripcion;
		this.procesoObjeto = proceso;
		this.tiposDocumentos = tiposDocumentos;
	}
	
	public MSubProceso(SubProceso subProceso) {
		this.descripcion = subProceso.getDescripcion();
		this.subproceso = subProceso.getSubproceso();
		this.id = subProceso.getId();
		this.procesoObjeto = subProceso.getProcesoObjeto();
		this.tiposDocumentos = subProceso.getTiposDocumentos();
	}
	
	public Proceso getProcesoObjeto() {
		return procesoObjeto;
	}

	public void setProcesoObjeto(Proceso procesoObjeto) {
		this.procesoObjeto = procesoObjeto;
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
	public long getSubproceso() {
		return subproceso;
	}

	public void setSubproceso(long subproceso) {
		this.subproceso = subproceso;
	}
	
	public Set<TipoDocumento> getTiposDocumentos() {
		return tiposDocumentos;
	}

	public void setTiposDocumentos(Set<TipoDocumento> tiposDocumentos) {
		this.tiposDocumentos = tiposDocumentos;
	}
	
}
