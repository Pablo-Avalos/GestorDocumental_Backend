package com.gestorDocumental.controller;

import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.mapping.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gestorDocumental.entity.Cliente;
import com.gestorDocumental.entity.DocumentoDigital;
import com.gestorDocumental.entity.Proceso;
import com.gestorDocumental.entity.SubProceso;
import com.gestorDocumental.entity.TipoDocumento;
import com.gestorDocumental.helper.SQLHelper;
//import com.gestorDocumental.helper.FTPHelper;
import com.gestorDocumental.model.MCliente;
import com.gestorDocumental.model.MDocumentoDigital;
import com.gestorDocumental.model.MProceso;
import com.gestorDocumental.model.MSubProceso;
import com.gestorDocumental.model.MTipoDocumento;
import com.gestorDocumental.service.DocumentoDigitalService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class API {

	static final Logger LOG = LoggerFactory.getLogger(API.class);
	@Autowired
	private SQLHelper sqlHelper;
	@Autowired
	@Qualifier("DigitalService")
	private DocumentoDigitalService docDigiService;

	@RequestMapping(value = "/obtenerSubproceso", method = RequestMethod.POST)
	public ResponseEntity<List<Object>> obtenerSubproceso(@Valid @RequestBody String body) throws IOException {
		List<Object> subprocesos = new ArrayList<>();
		try {
			JSONObject object = new JSONObject(body);
			Integer id = object.getInt("codigoProceso");
			//subprocesos = docDigiService.obtenerSubproceso(proceso);
			
			Proceso proceso = new Proceso();
			//proceso.setDescripcion("Comex");
			proceso.setId(id);
			subprocesos = docDigiService.obtenerSubProcesosDeProceso(proceso);
			
			new ResponseEntity<List<Object>>(subprocesos,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<List<Object>>(subprocesos,HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerProceso", method = RequestMethod.GET)
	public ResponseEntity<List<MProceso>> obtenerProceso() throws IOException {
		List<MProceso> procesos = new ArrayList<>();
		try {
			procesos = docDigiService.obtenerProcesos();
			return new ResponseEntity<List<MProceso>>(procesos,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
		} finally {
		}
		return new ResponseEntity<List<MProceso>>(procesos,HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerTiposDocumentosDeSubproceso", method = RequestMethod.POST)
	public ResponseEntity<List<Object>> obtenerTiposDocumentosDeSubproceso(@Valid @RequestBody String body) throws IOException {
		List<Object> tiposDocumentales = new ArrayList<Object>();
		// String legajo = "pablo";
		try {
			JSONObject object = new JSONObject(body);
			String subproceso = object.getString("subproceso");
			tiposDocumentales = docDigiService.documentosDeSubproceso(subproceso);
			return new ResponseEntity<List<Object>>(tiposDocumentales,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<List<Object>>(tiposDocumentales,HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerDocumentos", method = RequestMethod.POST)
	public ResponseEntity<List<MDocumentoDigital>> obtenerDocumentos(@Valid @RequestBody String body) throws IOException {
		List<MDocumentoDigital> tiposDocumentales = new ArrayList<>();
		try {
			JSONObject object = new JSONObject(body);
			String proceso = object.getString("proceso");
			String subproceso = object.getString("subproceso");
			String operacion = object.getString("operacion");
			if (operacion.isEmpty()) {
				tiposDocumentales = docDigiService.obtenerPorProcesoSubProceso(proceso, subproceso);
			} else {
				tiposDocumentales = docDigiService.obtenerPorProcesoSubProcesoYOperacion(proceso, subproceso,
						operacion);
			}
			return new ResponseEntity<List<MDocumentoDigital>>(tiposDocumentales,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<List<MDocumentoDigital>>(tiposDocumentales,HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerDocumentosPorCliente", method = RequestMethod.POST)
	public ResponseEntity<List<MDocumentoDigital>> obtenerDocumentosPorCliente(@Valid @RequestBody String body) throws IOException {
		List<MDocumentoDigital> documentos = new ArrayList<>();
		try {
			JSONObject object = new JSONObject(body);
			String cliente = object.getString("cliente");
			documentos = docDigiService.obtenerPorCliente(cliente);
			return new ResponseEntity<List<MDocumentoDigital>>(documentos,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<List<MDocumentoDigital>>(documentos,HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerClientePorNumero", method = RequestMethod.POST)
	public ResponseEntity<MCliente> obtenerClientePorNumero(@Valid @RequestBody String body) throws IOException {
		MCliente cliente = null;
		try {
			JSONObject object = new JSONObject(body);
			Integer nroCliente = object.getInt("numeroCliente");
			cliente = docDigiService.obtenerPorNumero(nroCliente);
			return new ResponseEntity<MCliente>(cliente,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<MCliente>(cliente,HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerNumerosDeClientes", method = RequestMethod.GET)
	public ResponseEntity<List<Object>> obtenerNumerosDeClientes() throws IOException {
		List<Object> cliente = null;
		try {
			cliente = docDigiService.obtenerNumerosCliente();
			return new ResponseEntity<List<Object>>(cliente,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
		} finally {
		}
		return new ResponseEntity<List<Object>>(cliente,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/guardarDigital", method = RequestMethod.POST)
	public ResponseEntity<Integer> guardarDigital(@Valid @RequestBody String body) throws IOException {
		DocumentoDigital documento = null;
		try {
			JSONObject object = new JSONObject(body);
			Integer solicitud = object.getInt("solicitud");
			String proceso = object.getString("proceso");
			String subProceso = object.getString("subProceso");
			String operacion = object.getString("operacion");
			String tipoDocumento = object.getString("tipoDocumento");
			String razonSocial = object.getString("razonSocial");

			documento = new DocumentoDigital(proceso, subProceso, operacion, tipoDocumento, razonSocial, "pablo", null);
			documento.setId(solicitud);
			documento.setBase64(docDigiService.obtenerPorIdDeDocumento(solicitud).getBase64());
			docDigiService.actualizarDocumentoDigital(documento);
			return new ResponseEntity<Integer>((Integer)(int)documento.getId(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<Integer>((Integer)(int)documento.getId(),HttpStatus.OK);
	}

	@RequestMapping(value = "/enviarPdf", method = RequestMethod.POST)
	public ResponseEntity<Integer> guardarPDF(InputStream is) throws IOException, InterruptedException {

		DocumentoDigital documento = new DocumentoDigital();
		Integer id = (Integer)(int)docDigiService.crearDocumentoDigital(documento);
		
		try {
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}

			buffer.toByteArray();

			String pdf = Base64.getEncoder().encodeToString(buffer.toByteArray());
			documento.setBase64(pdf);
			docDigiService.actualizarDocumentoDigital(documento);
			System.out.println("se guardo el base 64 del documento");
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
		} finally {
		}
		return new ResponseEntity<Integer>(id,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/todosLosSubprocesos", method = RequestMethod.GET)
	public ResponseEntity<List<String>> todosLosSubprocesos() throws IOException {
		List<String> subProcesos = new ArrayList<>();
		try {
			subProcesos = docDigiService.obtenerSubProcesos();
			return new ResponseEntity<List<String>>(subProcesos,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
		} finally {
		}
		return new ResponseEntity<List<String>>(subProcesos,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/guardarProceso", method = RequestMethod.POST)
	public ResponseEntity<Integer> guardarProceso(@Valid @RequestBody String body) throws IOException {
		try {
			JSONObject object = new JSONObject(body);
			//JSONArray procesos = object.getJSONArray("procesos");
			//for (int i = 0; i < procesos.length(); i++) {
			    String descripcion = object.getString("procesoTipo");
			    Proceso proceso = new Proceso();
			    proceso.setDescripcion(descripcion);
				docDigiService.altaDeProceso(proceso);
			//}
			return new ResponseEntity<Integer>(0,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<Integer>(0,HttpStatus.OK);
	}

	/*
	@RequestMapping(value = "/eliminarProceso", method = RequestMethod.POST)
	public ResponseEntity<Boolean> eliminarProceso(@Valid @RequestBody String body) throws IOException {
		try {
			JSONObject object = new JSONObject(body);
			String descripcion = object.getString("descripcion");
			TipoDocumento documento;
			Boolean eliminado = false;
			documento= docDigiService.tiposDocumentosPorDescripcion(descripcion);
			if(documento == null) {
				eliminado = false;
			}else {
				docDigiService.eliminarTipoDocumento(documento);
				eliminado = true;
			}
			return new ResponseEntity<Boolean>(eliminado,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.OK);
	}
	*/
	
	@RequestMapping(value = "/todosLosTiposDocumentales", method = RequestMethod.GET)
	public ResponseEntity<List<MTipoDocumento>> todosLosTiposDocumentales() throws IOException {
		List<MTipoDocumento> tiposDocumentales = new ArrayList<>();
		try {
			tiposDocumentales = docDigiService.obtenerTiposDocumentales();
			return new ResponseEntity<List<MTipoDocumento>>(tiposDocumentales,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
		} finally {
		}
		return new ResponseEntity<List<MTipoDocumento>>(tiposDocumentales,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/documentosDeSubproceso", method = RequestMethod.POST)
	public ResponseEntity<List<Object>> documentosDeSubproceso(@Valid @RequestBody String body) throws IOException {
		List<Object> tiposDocumentales = new ArrayList<>();
		try {
			JSONObject object = new JSONObject(body);
			String subproceso = object.getString("subproceso");
			tiposDocumentales = docDigiService.documentosDeSubproceso(subproceso);
			return new ResponseEntity<List<Object>>(tiposDocumentales,HttpStatus.OK);
			//return new ResponseEntity<List<Object>>(tiposDocumentales,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<List<Object>>(tiposDocumentales,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/guardarTipoDocumento", method = RequestMethod.POST)
	public ResponseEntity<Boolean> guardarTipoDocumento(@Valid @RequestBody String body) throws IOException {
		try {
			JSONObject object = new JSONObject(body);
			String descripcion = object.getString("descripcion");
			Integer id = object.getInt("id");
			Integer subprocesoid = object.getInt("subproceso");
			String descripcionS = object.getString("descripcionS");
			SubProceso s = new SubProceso();
			s.setId(id);
			s.setDescripcion(descripcionS);
			s.setSubproceso(subprocesoid);
			//ArrayList<SubProceso> ss = new ArrayList<>();
			//ss.add(s);
			TipoDocumento tipoDocumento = new TipoDocumento();
			tipoDocumento.setDescripcion(descripcion);
			//tipoDocumento.getSubProcesos().add(s);
			//System.out.println(tipoDocumento.getSubProcesos());
			docDigiService.altaDeTipoDocumento(tipoDocumento);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.OK);
	}
	/*
	@RequestMapping(value = "/eliminarTipoDocumento", method = RequestMethod.POST)
	public ResponseEntity<Boolean> eliminarTipoDocumento(@Valid @RequestBody String body) throws IOException {
		try {
			JSONObject object = new JSONObject(body);
			String descripcion = object.getString("descripcion");
			TipoDocumento documento;
			Boolean eliminado = false;
			documento= docDigiService.tiposDocumentosPorDescripcion(descripcion);
			SubProceso s = new SubProceso();
			s.setId(1);
			if(documento == null) {
				eliminado = false;
			}else {
				docDigiService.eliminarTiposDcouemntalesDeSubProceso(documento,s);
				docDigiService.eliminarTipoDocumento(documento);
				eliminado = true;
			}
			return new ResponseEntity<Boolean>(eliminado,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.OK);
	}
	*/
	
	@RequestMapping(value = "/guardarSubproceso", method = RequestMethod.POST)
	public ResponseEntity<Boolean> guardarSubproceso(@Valid @RequestBody String body) throws IOException {
		SubProceso subproceso = new SubProceso();
		try {
			HashSet<TipoDocumento> documentos = new HashSet<TipoDocumento>();
			JSONObject object = new JSONObject(body);
			String subProcesoDescripcion = object.getString("subProcesoDescripcion");
			String procesoDescripcion = object.getString("procesoDescripcion");
			Integer procesoId = object.getInt("procesoId");
			JSONArray tiposDocumentos = object.getJSONArray("tiposDocumentos");
			Proceso proceso = new Proceso();
			proceso.setDescripcion(procesoDescripcion);
			proceso.setId(procesoId);
			for (int i = 0; i < tiposDocumentos.length(); i++) {
			    String descripcion = tiposDocumentos.getJSONObject(i).getString("descripcion");
			    Integer id = tiposDocumentos.getJSONObject(i).getInt("id");
			    TipoDocumento tipoDocumento = new TipoDocumento();
			    tipoDocumento.setDescripcion(descripcion);
			    tipoDocumento.setId(id);
			    documentos.add(tipoDocumento);
			}
			subproceso.setDescripcion(subProcesoDescripcion);
			subproceso.setProcesoObjeto(proceso);
			subproceso.setTiposDocumentos(documentos);
			docDigiService.altaDeSubProceso(subproceso);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.OK);
	}

	@RequestMapping(value = "/eliminarSubproceso", method = RequestMethod.POST)
	public ResponseEntity<Integer> eliminarSubproceso(@Valid @RequestBody String body) throws IOException {
		SubProceso subproceso = new SubProceso();
		try {
			JSONObject object = new JSONObject(body);
			String descripcion = object.getString("descripcion");
			Integer id = object.getInt("id");
			Integer nroSubProceso = object.getInt("subproceso");
			subproceso.setDescripcion(descripcion);
			subproceso.setId(id);
			subproceso.setProceso(nroSubProceso);
			docDigiService.borrarSubProceso(subproceso);
			return new ResponseEntity<Integer>(1,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<Integer>(0,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/guardarCliente", method = RequestMethod.POST)
	public ResponseEntity<Boolean> guardarCliente(@Valid @RequestBody String body) throws IOException {
		Cliente cliente = new Cliente();
		try {
			JSONObject object = new JSONObject(body);
			String razonSocial = object.getString("razonSocial");
			//System.out.println("El cliente es: " + Integer.parseInt(object.getString("numeroCliente")));
			long numeroCliente = object.getLong("numeroCliente");
			cliente.setNumeroCliente(numeroCliente);
			cliente.setRazonSocial(razonSocial);
			docDigiService.guardarCliente(cliente);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/eliminarCliente", method = RequestMethod.POST)
	public ResponseEntity<Boolean> eliminarCliente(@Valid @RequestBody String body) throws IOException {
		Cliente cliente = new Cliente();
		try {
			JSONObject object = new JSONObject(body);
			String razonSocial = object.getString("razonSocial");
			Integer numeroCliente = object.getInt("numeroCliente");
			cliente.setNumeroCliente(numeroCliente);
			cliente.setRazonSocial(razonSocial);
			if(docDigiService.obtenerNumerosCliente().contains(numeroCliente)) {
				docDigiService.eliminarCliente(cliente);
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}else {
				return new ResponseEntity<Boolean>(false,HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.OK);
	}
}
