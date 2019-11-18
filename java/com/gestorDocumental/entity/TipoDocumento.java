package com.gestorDocumental.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	//@ManyToMany(mappedBy = "subProceso")
	//private Set<SubProceso> subProcesos = new HashSet<>();
	
	//public Set<SubProceso> getSubProcesos() {
	//	return subProcesos;
	//}

	
//	@ManyToMany(cascade ={CascadeType.MERGE, CascadeType.REFRESH})
//	@JoinTable(
//			name = "TiposDocumentosDeSubProceso"
//			,joinColumns= @JoinColumn(name="tipoDocumentoId")
	//		,inverseJoinColumns = @JoinColumn(name="subProcesoId")
	//)
	//private Set<SubProceso> subProcesos = new HashSet<>();
	
	
	
	//public void setSubProcesos(Set<SubProceso> subProcesos) {
	//	this.subProcesos = subProcesos;
//	}

	//public Set<SubProceso> getSubProcesos() {
	//	return subProcesos;
	//}

	//public void setSubProcesos(Set<SubProceso> subProcesos) {
	//	this.subProcesos = subProcesos;
	//}

	public TipoDocumento() {}
	
	public TipoDocumento(String descripcion,Set<SubProceso> subProcesos) {
		this.descripcion = descripcion;
		//this.subProcesos = subProcesos;
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
