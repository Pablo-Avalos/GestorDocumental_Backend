package com.gestorDocumental.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "proceso")
@Entity
public class Proceso implements Serializable{

	private static final long serialVersionUID = 1L;

	public Proceso(long id, String descripcion,Set<SubProceso> subProcesos) {
		this.id = id;
		this.descripcion = descripcion;
		this.subProcesos = subProcesos;
	}
	
	public Proceso() {}
	
	@GeneratedValue
	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "DESCRIPCION",unique=true)
	private String descripcion;
	
	@OneToMany(mappedBy = "proceso")
	private Set<SubProceso> subProcesos = new HashSet<>();

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
