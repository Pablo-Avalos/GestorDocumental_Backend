package com.gestorDocumental.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gestorDocumental.entity.DocumentoDigital;
import com.gestorDocumental.entity.Proceso;
import com.gestorDocumental.model.MDocumentoDigital;
import com.gestorDocumental.model.MProceso;

@Component("convertidor")
public class Convertidor {
	public List<MDocumentoDigital> convertirLista (List<DocumentoDigital> documentos){
		List<MDocumentoDigital> mdigitales = new ArrayList<>();
		for(DocumentoDigital digital: documentos) {
			mdigitales.add(new MDocumentoDigital(digital));
		}
		return mdigitales;
	}
	
	public MProceso convertirProceso(Proceso proceso) {
		return new MProceso(proceso);
	}
	
	public List<MProceso> convertirListaProcesos (List<Proceso> procesos){
		List<MProceso> mprocesos = new ArrayList<>();
		for(Proceso proceso: procesos) {
			mprocesos.add(new MProceso(proceso));
		}
		return mprocesos;
	}
}
