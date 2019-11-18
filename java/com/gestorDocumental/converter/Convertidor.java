package com.gestorDocumental.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

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

@Component("convertidor")
public class Convertidor {
	public MDocumentoDigital convertirDocumento (DocumentoDigital documento){
			return (new MDocumentoDigital(documento));
	}
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
	
	public MSubProceso convertirSubProceso(SubProceso subProceso) {
		return new MSubProceso(subProceso);
	}
	
	public List<MProceso> convertirListaProcesos (List<Proceso> procesos){
		List<MProceso> mprocesos = new ArrayList<>();
		for(Proceso proceso: procesos) {
			mprocesos.add(new MProceso(proceso));
		}
		return mprocesos;
	}
	
	public List<MSubProceso> convertirListaSubProcesos (List<SubProceso> subProcesos){
		List<MSubProceso> msubProcesos = new ArrayList<>();
		for(SubProceso subProceso: subProcesos) {
			msubProcesos.add(new MSubProceso(subProceso));
		}
		return msubProcesos;
	}
	
	public MCliente convertirCliente(Cliente cliente) {
		return new MCliente(cliente);
	}
	
	public List<MTipoDocumento> convertirListaTiposDocumentos(List<TipoDocumento> tiposDocumentales) {
		List<MTipoDocumento> mTiposDocumentos = new ArrayList<>();
		for(TipoDocumento tipoDoc: tiposDocumentales) {
			mTiposDocumentos.add(new MTipoDocumento(tipoDoc));
		}
		return mTiposDocumentos;
	}

	public List<MSubProceso> convertirSubProcesos(List<String> list){
		List<MSubProceso> lmsp = new ArrayList<>();
		list.forEach((listSubproceso) -> {
			//listSubproceso.forEach(subproceso ->{
				//List<Object> lo = (List<Object>) listSubproceso;
				MSubProceso sp = new MSubProceso();
				//sp.setId((Integer)listSubproceso.get(0));
				//sp.setSubproceso((Integer)listSubproceso.get(1));
				sp.setDescripcion(listSubproceso);
				lmsp.add(sp);
			//});
		});
		return lmsp;
		
	}
}
