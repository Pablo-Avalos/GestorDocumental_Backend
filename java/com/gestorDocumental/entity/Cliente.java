package com.gestorDocumental.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "cliente")
@Entity
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@GeneratedValue
	@Id	
	@Column(name = "NUMEROCLIENTE")
	private Integer numeroCliente;
	
	@Column(name = "RAZONSOCIAL")
	private String razonSocial;
	
	public Cliente() {}
	
	public Cliente(String razonSocial, Integer nroCliente) {
		this.razonSocial = razonSocial;
		this.numeroCliente = nroCliente;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Integer getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(Integer numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
