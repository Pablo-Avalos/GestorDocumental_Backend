package com.gestorDocumental.model;

import com.gestorDocumental.entity.Cliente;

public class MCliente {
	String razonSocial;
	Integer numeroCliente;
	public Integer getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(Integer numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	//public abstract Integer obtenerPorNroCliente();
	
	public MCliente() {}
	
	public MCliente(Cliente c) {
		this.razonSocial = c.getRazonSocial();
		this.numeroCliente = c.getNumeroCliente();
	}
	
}
