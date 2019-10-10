package com.gestorDocumental.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gestorDocumental.converter.Convertidor;
import com.gestorDocumental.entity.DocumentoDigital;
import com.gestorDocumental.model.MCliente;
import com.gestorDocumental.model.MDocumentoDigital;
import com.gestorDocumental.model.MProceso;
import com.gestorDocumental.repository.ClienteRepositorio;
import com.gestorDocumental.repository.DocumentoDigitalRepositorio;
import com.gestorDocumental.repository.ProcesoRepositorio;

@Service("DigitalService")
public class DocumentoDigitalService {
	
	
	@Autowired 
	@Qualifier("convertidor")
	private Convertidor convertidor;
	
	@Autowired 
	@Qualifier("repositorio")
	private DocumentoDigitalRepositorio repositorio;

	@Autowired 
	@Qualifier("procesoRepositorio")
	private ProcesoRepositorio procesoRepositorio;
	
	@Autowired 
	@Qualifier("clienteRepositorio")
	private ClienteRepositorio clienteRepositorio;

	public long crearDocumentoDigital(DocumentoDigital digital) {
		try {
			repositorio.save(digital);
			//repositorio.findByProcesoAndSubprocesoAndOperacion (digital.getProceso(),digital.getSubproceso(),digital.getOperacion());
			return digital.getId();
		}catch(Exception e) {
			return 0;	
		}
	}
	
	public boolean actualizarDocumentoDigital(DocumentoDigital digital) {
		try {
			//verificar si existe y si no crear el digital
			repositorio.save(digital);
			return true;
		}catch(Exception e) {
			return false;	
		}
	}
	
	public boolean borraDocumentoDigital(long id) {
		try {
			DocumentoDigital digital = repositorio.findById(id);
			repositorio.delete(digital);
			return true;
		}catch(Exception e) {
			return false;	
		}
	}
	
	public List<MDocumentoDigital> obtenerDigitales(){
		return convertidor.convertirLista(repositorio.findAll());
	}
	
	public List<MDocumentoDigital> obtenerPorProcesoSubProcesoYOperacion(String proceso, String subproceso, String operacion){
		System.out.println("Se va a buscar en el repositorio");
		List<MDocumentoDigital> digitales = new ArrayList<>();
		digitales.addAll(convertidor.convertirLista(repositorio.findByProcesoAndSubprocesoAndOperacion(proceso, subproceso, operacion)));
		System.out.println("Se encontraron: " + digitales.size());
		return (digitales);
	}
	
	public List<MProceso> obtenerProcesos(){
		List<MProceso> procesos = new ArrayList<>();
		procesos.addAll(convertidor.convertirListaProcesos(procesoRepositorio.findAll()));
		return procesos;
	}

	public List<MDocumentoDigital> obtenerPorCliente(String clienteDU) {
		List<MDocumentoDigital> digitales = new ArrayList<>(); 
		digitales.addAll(convertidor.convertirLista(repositorio.findByCliente(clienteDU)));
		return digitales;
	}

	public List<MDocumentoDigital> obtenerPorProcesoSubProceso(String proceso, String subproceso) {
		List<MDocumentoDigital> digitales = new ArrayList<>(); 
		digitales.addAll(convertidor.convertirLista(repositorio.findByProcesoAndSubproceso(proceso,subproceso)));
		return digitales;
	}

	public MCliente obtenerPorNumero(Integer numero) {
		return (convertidor.convertirCliente(this.clienteRepositorio.findByNumeroCliente(numero)));
	}

}
