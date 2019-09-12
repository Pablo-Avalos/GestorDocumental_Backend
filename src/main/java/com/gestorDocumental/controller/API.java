package com.gestorDocumental.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gestorDocumental.helper.SQLHelper;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class API {
	
	static final Logger LOG = LoggerFactory.getLogger(API.class);
	@Autowired
	private SQLHelper sqlHelper;
	
	@RequestMapping
	(value = "/guardarDocumento", method = RequestMethod.POST)
	public String guardarDocumento(@Valid @RequestBody String body) throws IOException {
		String ubicacion = null;
		try {
			JSONObject object = new JSONObject(body);
//			{"documentoTipo":"VERAZ"}
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
	public List<String> obtenerProceso() throws IOException {
		List<String> procesos=new ArrayList<String>();
		//String legajo = "pablo";
		try {
			procesos = sqlHelper.obtenerProceso();
			return(procesos);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
		}finally{
		}
		return(procesos);			
	}

}
