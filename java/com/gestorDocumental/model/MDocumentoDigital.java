package com.gestorDocumental.model;

import com.gestorDocumental.entity.DocumentoDigital;

public class MDocumentoDigital {
	public MDocumentoDigital() {}
	
	public MDocumentoDigital(DocumentoDigital docDigital) {
		this.id = docDigital.getId();
		this.proceso = docDigital.getProceso();
		this.subproceso = docDigital.getSubproceso();
		this.operacion = docDigital.getOperacion();
		this.documento = docDigital.getDocumento();
		this.cliente = docDigital.getCliente();
		this.legajo = docDigital.getLegajo();
		this.base64 = docDigital.getBase64();
		
	}
	
	public MDocumentoDigital(Integer id,String proceso, String subproceso, String operacion, String documento, 
			String cliente, String legajo, String base64) {
		this.id = id;
		this.proceso = proceso;
		this.subproceso = subproceso;
		this.operacion = operacion;
		this.documento = documento;
		this.cliente= cliente;
		this.legajo= legajo;
		this.base64 = base64;
	}
	
	private long id;
	private String proceso;
	private String subproceso;
	private String operacion;
	private String documento;
	private String cliente;
	private String legajo;
	private String base64;
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

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}
}