package com.gestorDocumental.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestorDocumental.converter.Convertidor;
import com.gestorDocumental.entity.Cliente;
import com.gestorDocumental.entity.DocumentoDigital;
import com.gestorDocumental.entity.Proceso;
import com.gestorDocumental.entity.SubProceso;
import com.gestorDocumental.entity.TipoDocumento;
import com.gestorDocumental.model.MCliente;
import com.gestorDocumental.model.MDocumentoDigital;
import com.gestorDocumental.model.MProceso;
import com.gestorDocumental.model.MSubProceso;
import com.gestorDocumental.model.MTipoDocumento;
import com.gestorDocumental.repository.ClienteRepositorio;
import com.gestorDocumental.repository.DocumentoDigitalRepositorio;
import com.gestorDocumental.repository.ProcesoRepositorio;
import com.gestorDocumental.repository.SubProcesoRepositorio;
import com.gestorDocumental.repository.TipoDocumentoRepositorio;
//import com.gestorDocumental.repository.TiposDocumentosDeSubProcesoRepositorio;

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
	@Qualifier("subProcesoRepositorio")
	private SubProcesoRepositorio subProcesoRepositorio;
	
	
	@Autowired 
	@Qualifier("clienteRepositorio")
	private ClienteRepositorio clienteRepositorio;

	@Autowired 
	@Qualifier("tipoDocumentoRepositorio")
	private TipoDocumentoRepositorio tipoDocumentoRepositorio;
	
	
	//@Autowired 
	//@Qualifier("tiposDocumentoPorSubprocesoRepositorio")
	//private TiposDocumentosDeSubProcesoRepositorio tiposDocumentoPorSubprocesoRepositorio;
	
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
		//List<MProceso> procesos = new ArrayList<>();
		return this.convertidor.convertirListaProcesos(procesoRepositorio.findAll());
		//return procesos;
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

	public MCliente obtenerPorNumero(long nroCliente) {
		return (convertidor.convertirCliente(this.clienteRepositorio.findByNumeroCliente(nroCliente)));
	}
	
	public Cliente obtenerClientePorNumero(long nroCliente) {
		return this.clienteRepositorio.findByNumeroCliente(nroCliente);
	}
	
	public MDocumentoDigital obtenerPorIdDeDocumento(long numero) {
		
		return (convertidor.convertirDocumento(this.repositorio.findById(numero)));
	}
	
	public DocumentoDigital obtenerDigitalPorIdDeDocumento(long numero) {
		
		return (this.repositorio.findById(numero));
	}
	
	//public List<String> base64(long numero) {
	//	return (this.repositorio.obtenerBase64(numero));
	//}

	public List<Object> obtenerSubProcesosDeProceso(Proceso proceso) {
		List<Object> subProcesos = new ArrayList<>();
		subProcesos.addAll(subProcesoRepositorio.obtenerSubProcesosDeProceso(proceso));
		return subProcesos;
	}

	public void altaDeProceso(Proceso proceso) {
		this.procesoRepositorio.save(proceso);
	}

	public List<String> obtenerSubProcesos() {
		return subProcesoRepositorio.obtenerSubProcesos();
	}

	public List<MTipoDocumento> obtenerTiposDocumentales() {
		List<MTipoDocumento> tiposDocumentos = new ArrayList<>();
		tiposDocumentos.addAll(convertidor.convertirListaTiposDocumentos(tipoDocumentoRepositorio.findAll()));
		return tiposDocumentos;
	}

	public List<Object> documentosDeSubproceso(String subproceso) {
		List<Object> tiposDocumentos = new ArrayList<>();
		tiposDocumentos.addAll(subProcesoRepositorio.obtenerTipoDeDocumento(subproceso));
		return tiposDocumentos;
	}

	public void altaDeSubProceso(SubProceso subProceso) {
		this.subProcesoRepositorio.save(subProceso);
	}

	public void borrarSubProceso(SubProceso subproceso) {
		this.subProcesoRepositorio.delete(subproceso);		
	}
	
	public void altaDeTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumentoRepositorio.save(tipoDocumento);
	}
	
	/*
	public void eliminarTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumentoRepositorio.delete(tipoDocumento);
		List<SubProceso> subprocesos = this.subProcesoRepositorio.findAll();
		for(SubProceso s :subprocesos) {
			Set<TipoDocumento> documentos= s.getTiposDocumentos();
			for(TipoDocumento documento :documentos) {
				if(documento.getDescripcion() == tipoDocumento.getDescripcion()) {
					documentos.remove(tipoDocumento);
					s.setTiposDocumentos(documentos);
					this.subProcesoRepositorio.save(s);
				}else {
				}
			}
			
		}
	}
	*/
	
	public TipoDocumento tiposDocumentosPorDescripcion(String descripcion){
		return this.tipoDocumentoRepositorio.findByDescripcion(descripcion);
	}
	
	//public void eliminarTiposDcouemntalesDeSubProceso(TipoDocumento doc,SubProceso s) {
		//this.subProcesoRepositorio.eliminarTipoDeDocumento(doc);
	//}

	public void eliminarCliente(Cliente cliente) {
		this.clienteRepositorio.delete(cliente);
	}
	
	public void guardarCliente(Cliente cliente) {
		this.clienteRepositorio.save(cliente);
	}
	
	public List<Object> obtenerNumerosCliente(){
		return this.clienteRepositorio.obtenerNumerosCliente();
	}
}
