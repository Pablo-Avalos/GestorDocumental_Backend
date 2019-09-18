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
	}
	
	public MDocumentoDigital(Integer id,String proceso, String subproceso, String operacion, String documento) {
		this.id = id;
		this.proceso = proceso;
		this.subproceso = subproceso;
		this.operacion = operacion;
		this.documento = documento;
	}
	
	private long id;
	private String proceso;
	private String subproceso;
	private String operacion;
	private String documento;
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
}
