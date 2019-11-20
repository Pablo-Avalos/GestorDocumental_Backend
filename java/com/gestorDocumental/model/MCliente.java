package com.gestorDocumental.model;

import java.util.HashSet;
import java.util.Set;

import com.gestorDocumental.entity.Cliente;
import com.gestorDocumental.entity.DocumentoDigital;
import com.gestorDocumental.entity.SubProceso;

public class MCliente {
	String razonSocial;
	long numeroCliente;
	Set<DocumentoDigital> documentos = new HashSet<>(); 
	
	public Set<DocumentoDigital> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(Set<DocumentoDigital> documentos) {
		this.documentos = documentos;
	}

	public long getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(long numeroCliente) {
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
	
	//public MCliente(Cliente c) {
	//	this.razonSocial = c.getRazonSocial();
	//	this.numeroCliente = c.getNumeroCliente();
	//	this.documentos = crearDocumentos(c.getDocumentos());
	//}
	
	public MCliente(long id, String razonSocial,Set<DocumentoDigital> documentos) {
		this.numeroCliente = id;
		this.razonSocial = razonSocial;		
		this.documentos = documentos;
	}
	
	/*
	public Set<MDocumentoDigital> crearDocumentos(Set<DocumentoDigital> documentos){
		Set<MDocumentoDigital> digitales = new HashSet<>();;
		for(DocumentoDigital documento :documentos) {
			digitales.add(new MDocumentoDigital(documento));
		}
		return digitales;
	}
	*/
}
