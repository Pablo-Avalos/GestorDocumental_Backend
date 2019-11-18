package com.gestorDocumental.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "subProceso")
@Entity
public class SubProceso implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@GeneratedValue
	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "SUBPROCESO")
	private long subproceso;
	
	@Column(name = "DESCRIPCION",unique=true)
	private String descripcion;
	
	@Column(name = "PROCESO")
	private long proceso;
	
	@Column(name = "DESCRIPCIONPROCESO")
	private String descripcionproceso;

	@JsonIgnore
	@ManyToOne
	private Proceso procesoObjeto;
	
	//@OneToMany(mappedBy = "proceso")
	//private Set<SubProceso> subProcesos = new HashSet<>();

	@ManyToMany(cascade ={CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
			name = "TiposDocumentosDeSubProceso"
			,joinColumns= @JoinColumn(name="subProcesoId")
			,inverseJoinColumns = @JoinColumn(name="tipoDocumentoId")
	)
	private Set<TipoDocumento> tiposDocumentos = new HashSet<>();
	//@ManyToMany(mappedBy = "tipoDocumento")
	
	

	//public Set<SubProceso> getSubProcesos() {
	//	return subProcesos;
	//}

	//public void setSubProcesos(Set<SubProceso> subProcesos) {
	//	this.subProcesos = subProcesos;
	//}

	public Set<TipoDocumento> getTiposDocumentos() {
		return tiposDocumentos;
	}

	public void setTiposDocumentos(Set<TipoDocumento> tiposDocumentos) {
		this.tiposDocumentos = tiposDocumentos;
	}

	public SubProceso() {}

	//public Set<TipoDocumento> getTiposDocumentos() {
	//	return tiposDocumentos;
	//}

	//public void setTiposDocumentos(Set<TipoDocumento> tiposDocumentos) {
	//	this.tiposDocumentos = tiposDocumentos;
	//}

	public SubProceso(long subProceso, String descripcion, Proceso proceso,Set<TipoDocumento> documentos) {
		this.subproceso = subProceso;
		this.descripcion = descripcion;
		this.procesoObjeto = proceso;
		this.tiposDocumentos = documentos;
	}
	
	public long getSubproceso() {
		return subproceso;
	}

	public void setSubproceso(long subproceso) {
		this.subproceso = subproceso;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public long getProceso() {
		return proceso;
	}

	public void setProceso(long proceso) {
		this.proceso = proceso;
	}

	public String getDescripcionproceso() {
		return descripcionproceso;
	}

	public void setDescripcionproceso(String descripcionproceso) {
		this.descripcionproceso = descripcionproceso;
	}
	
}
