package com.gestorDocumental.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gestorDocumental.entity.DocumentoDigital;
import com.gestorDocumental.model.MDocumentoDigital;

@Component("convertidor")
public class Convertidor {
	public List<MDocumentoDigital> convertirLista (List<DocumentoDigital> documentos){
		List<MDocumentoDigital> mdigitales = new ArrayList<>();
		System.out.println("se van a convertir los documentos");
		System.out.println("Hay: " + mdigitales.size() + " documentos");
		for(DocumentoDigital digital: documentos) {
			mdigitales.add(new MDocumentoDigital(digital));
		}
		return mdigitales;
	}
}
