package com.gestorDocumental.model;

import com.gestorDocumental.entity.Cliente;
import com.gestorDocumental.entity.DocumentoDigital;

public class MDocumentoDigital {
	public MDocumentoDigital() {}
	
	public MDocumentoDigital(DocumentoDigital docDigital) {
		this.id = docDigital.getId();
		this.proceso = docDigital.getProceso();
		this.subproceso = docDigital.getSubproceso();
		this.operacion = docDigital.getOperacion();
		this.documento = docDigital.getDocumento();
		this.legajo = docDigital.getLegajo();
		this.base64 = docDigital.getBase64();
		this.clienteObjeto = docDigital.getClienteObjeto();
		
	}
		
	public MDocumentoDigital(Integer id,String proceso, String subproceso, String operacion, String documento, 
			String legajo, String base64,Cliente clienteObjeto) {
		this.id = id;
		this.proceso = proceso;
		this.subproceso = subproceso;
		this.operacion = operacion;
		this.documento = documento;
		this.legajo= legajo;
		this.base64 = base64;
		this.clienteObjeto = clienteObjeto;
		
	}
	
	private long id;
	private String proceso;
	private String subproceso;
	private String operacion;
	private String documento;
	private String legajo;
	private String base64;
	private Cliente clienteObjeto;

	public Cliente getClienteObjeto() {
		return clienteObjeto;
	}

	public void setClienteObjeto(Cliente clienteObjeto) {
		this.clienteObjeto = clienteObjeto;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public String getSubproceso() {
		return subproceso;
	}

	public void setSubproceso(String subproceso) {
		this.subproceso = subproceso;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
}