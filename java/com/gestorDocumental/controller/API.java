package com.gestorDocumental.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.hql.internal.ast.tree.IsNullLogicOperatorNode;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gestorDocumental.helper.SQLHelper;
import com.gestorDocumental.model.MDocumentoDigital;
import com.gestorDocumental.model.MProceso;
import com.gestorDocumental.service.DocumentoDigitalService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class API {
	
	static final Logger LOG = LoggerFactory.getLogger(API.class);
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	@Qualifier("DigitalService")
	private DocumentoDigitalService docDigiService;
	
	@RequestMapping
	(value = "/guardarDocumento", method = RequestMethod.POST)
	public String guardarDocumento(@Valid @RequestBody String body) throws IOException {
		String ubicacion = null;
		try {
			JSONObject object = new JSONObject(body);
			String documentoDescripcion = object.getString("documentoTipo");
			ubicacion= sqlHelper.guardarDocumento(documentoDescripcion,1,2);
			return(ubicacion);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		}finally{
		}
		return(ubicacion);			
	}
	
	@RequestMapping
	(value = "/obtenerSubproceso", method = RequestMethod.POST)
	public List<String> obtenerSubproceso(@Valid @RequestBody String body) throws IOException {
		List<String> subprocesos=new ArrayList<String>();
		//String legajo = "pablo";
		try {
			JSONObject object = new JSONObject(body);
			String proceso = object.getString("procesoTipo");
			subprocesos = sqlHelper.obtenerSubproceso(proceso);
			return(subprocesos);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		}finally{
		}
		return(subprocesos);			
	}

	@RequestMapping
	(value = "/obtenerProceso", method = RequestMethod.POST)
	public List<MProceso> obtenerProceso() throws IOException {
		List<MProceso> procesos=new ArrayList<>();
		try {
			procesos = docDigiService.obtenerProcesos();
			return(procesos);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
		}finally{
		}
		return(procesos);			
	}

	@RequestMapping
	(value = "/obtenerTiposDocumentosDeSubproceso", method = RequestMethod.POST)
	public List<String> obtenerTiposDocumentosDeSubproceso(@Valid @RequestBody String body) throws IOException {
		List<String> tiposDocumentales=new ArrayList<String>();
		//String legajo = "pablo";
		try {
			JSONObject object = new JSONObject(body);
			String proceso = object.getString("proceso");
			String subproceso = object.getString("subproceso");
			tiposDocumentales = sqlHelper.obtenerTiposDocumentosDeSubproceso(proceso,subproceso);
			return(tiposDocumentales);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		}finally{
		}
		return(tiposDocumentales);			
	}
	
	@RequestMapping
	(value = "/obtenerDocumentos", method = RequestMethod.POST)
	public List<MDocumentoDigital> obtenerDocumentos(@Valid @RequestBody String body) throws IOException {
		List<MDocumentoDigital> tiposDocumentales=new ArrayList<>();
		try {
			JSONObject object = new JSONObject(body);
			String proceso = object.getString("proceso");
			String subproceso = object.getString("subproceso");
			String operacion = object.getString("operacion");
			System.out.println("La operacion es: " + operacion);
			if(operacion.isEmpty()) {
				tiposDocumentales = docDigiService.obtenerPorProcesoSubProceso(proceso,subproceso);
				System.out.println("Busqueda solo por proceso y subproceso");
			}else {
				System.out.println("Busqueda solo por proceso, subproceso y operacion");
				tiposDocumentales = docDigiService.obtenerPorProcesoSubProcesoYOperacion(proceso,subproceso,operacion);
			}
			return(tiposDocumentales);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		}finally{
		}
		return(tiposDocumentales);			
	}
	
	@RequestMapping
	(value = "/obtenerDocumentosPorCliente", method = RequestMethod.POST)
	public List<MDocumentoDigital> obtenerDocumentosPorCliente(@Valid @RequestBody String body) throws IOException {
		List<MDocumentoDigital> documentos = new ArrayList<>();
		try {
			JSONObject object = new JSONObject(body);
			String cliente = object.getString("cliente");
			documentos = docDigiService.obtenerPorCliente(cliente);
			return(documentos);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		}finally{
		}
		return(documentos);			
	}
}
