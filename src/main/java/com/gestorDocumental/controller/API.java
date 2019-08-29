package com.gestorDocumental.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class API {
	
	static final Logger LOG = LoggerFactory.getLogger(API.class);

	@RequestMapping(value = "/guardarDocumento", method = RequestMethod.POST)
	public String guardarDocumento(@Valid @RequestBody String body) throws IOException {
		try {
			JSONObject object = new JSONObject(body);
			Integer idDocumetal = object.getInt("idDocumetal");
			LOG.error("El id documental es: " + idDocumetal);
			return("Recibi un: " + idDocumetal);
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
		}finally{
		}
		return("Error");
			
	}
	
}
