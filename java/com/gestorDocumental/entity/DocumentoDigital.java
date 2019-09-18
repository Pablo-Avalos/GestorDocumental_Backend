package com.gestorDocumental.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "documentodigital")
@Entity
public class DocumentoDigital implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DocumentoDigital(long id, String proceso, String subproceso, String operacion, String documento) {
		this.id = id;
		this.proceso = proceso;
		this.subproceso = subproceso;
		this.operacion = operacion;
		this.documento = documento;
	}
	
	public DocumentoDigital() {}
	
	@GeneratedValue
	@Id
	@Column(name = "ID")
	private long id;
	
	@Column(name = "PROCESO")
	private String proceso;
	
	@Column(name = "SUBPROCESO")
	private String subproceso;
	
	@Column(name = "OPERACION")
	private String operacion;
	
	@Column(name = "DOCUMENTO")
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
