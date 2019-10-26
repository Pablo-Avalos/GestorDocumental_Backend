package com.gestorDocumental.controller;

import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.io.ObjectOutputStream;
//import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;
//import javax.xml.bind.DatatypeConverter;

//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.aspectj.bridge.Message;
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

import com.gestorDocumental.entity.DocumentoDigital;
import com.gestorDocumental.helper.SQLHelper;
//import com.gestorDocumental.helper.FTPHelper;
import com.gestorDocumental.model.MCliente;
import com.gestorDocumental.model.MDocumentoDigital;
import com.gestorDocumental.model.MProceso;
import com.gestorDocumental.service.DocumentoDigitalService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class API {

	static final Logger LOG = LoggerFactory.getLogger(API.class);
	@Autowired
	private SQLHelper sqlHelper;
	//@Autowired
	//private FTPHelper ftpHelper;
	@Autowired
	@Qualifier("DigitalService")
	private DocumentoDigitalService docDigiService;

	@RequestMapping(value = "/guardarDocumento", method = RequestMethod.POST)
	public ResponseEntity<String> guardarDocumento(@Valid @RequestBody String body) throws IOException {
		String ubicacion = null;
		try {
			JSONObject object = new JSONObject(body);
			String documentoDescripcion = object.getString("documentoTipo");
			ubicacion = sqlHelper.guardarDocumento(documentoDescripcion, 1, 2);
			return new ResponseEntity<String>(ubicacion,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<String>(ubicacion,HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerSubproceso", method = RequestMethod.POST)
	public ResponseEntity<List<String>> obtenerSubproceso(@Valid @RequestBody String body) throws IOException {
		List<String> subprocesos = new ArrayList<String>();
		// String legajo = "pablo";
		try {
			JSONObject object = new JSONObject(body);
			String proceso = object.getString("procesoTipo");
			subprocesos = sqlHelper.obtenerSubproceso(proceso);
			new ResponseEntity<List<String>>(subprocesos,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<List<String>>(subprocesos,HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerProceso", method = RequestMethod.POST)
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
	public ResponseEntity<List<String>> obtenerTiposDocumentosDeSubproceso(@Valid @RequestBody String body) throws IOException {
		List<String> tiposDocumentales = new ArrayList<String>();
		// String legajo = "pablo";
		try {
			JSONObject object = new JSONObject(body);
			String proceso = object.getString("proceso");
			String subproceso = object.getString("subproceso");
			tiposDocumentales = sqlHelper.obtenerTiposDocumentosDeSubproceso(proceso, subproceso);
			return new ResponseEntity<List<String>>(tiposDocumentales,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
			System.out.println(body);
		} finally {
		}
		return new ResponseEntity<List<String>>(tiposDocumentales,HttpStatus.OK);
	}

	@RequestMapping(value = "/obtenerDocumentos", method = RequestMethod.POST)
	public ResponseEntity<List<MDocumentoDigital>> obtenerDocumentos(@Valid @RequestBody String body) throws IOException {
		List<MDocumentoDigital> tiposDocumentales = new ArrayList<>();
		try {
			JSONObject object = new JSONObject(body);
			String proceso = object.getString("proceso");
			String subproceso = object.getString("subproceso");
			String operacion = object.getString("operacion");
			System.out.println("La operacion es: " + operacion);
			if (operacion.isEmpty()) {
				tiposDocumentales = docDigiService.obtenerPorProcesoSubProceso(proceso, subproceso);
				System.out.println("Busqueda solo por proceso y subproceso");
			} else {
				System.out.println("Busqueda solo por proceso, subproceso y operacion");
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

			//FileOutputStream outStream = null;

			// String filePath = "src/main/resources/";
			// String newFileName = "doc_"+ id + ".pdf";
			// outStream = new FileOutputStream(filePath + newFileName);

			// byte[] decoded;
			// decoded = DatatypeConverter.parseBase64Binary(pdf);
			// outStream.write(decoded);
			// outStream.flush();
			// outStream.close();

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error: " + e.getMessage());
		} finally {
			// Thread.sleep(20000);
			// System.out.println("se va a mover el doc");
			// File file = new File("src/main/resources/doc_" + id +".pdf");
			// String nombre = ("doc_" + id + ".pdf");
			// outStream.close();
			// ftpHelper.open();
			// ftpHelper.putFileToFolder(file, nombre);
			// ftpHelper.close();
		}
		return new ResponseEntity<Integer>(id,HttpStatus.OK);
	}
/*
	@RequestMapping(value = "/obtenerBase64PDF", method = RequestMethod.POST)
	public MDocumentoDigital enviarBase64PDF(@Valid @RequestBody String body) throws IOException {

		System.out.println("se llamo al servicio");
		MDocumentoDigital pdf = null;
		// DocumentoDigital pdf = new DocumentoDigital();
		try {

			JSONObject object = new JSONObject(body);
			Integer id = object.getInt("id");
			pdf = docDigiService.obtenerPorIdDeDocumento(id);
		} catch (Exception e) {
			LOG.error("Error al ejecutar query " + e.getMessage());
			e.printStackTrace();
		}
		return pdf;
	}
	*/
	/*
	 * @RequestMapping(value = "/recibirPDF", method = RequestMethod.POST) public
	 * long recibirPDF(InputStream is) throws IOException {
	 * 
	 * DocumentoDigital documento = new DocumentoDigital(); long id =
	 * docDigiService.crearDocumentoDigital(documento);
	 * 
	 * ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	 * 
	 * int nRead; byte[] data = new byte[16384];
	 * 
	 * while ((nRead = is.read(data, 0, data.length)) != -1) { buffer.write(data, 0,
	 * nRead); }
	 * 
	 * buffer.toByteArray();
	 * 
	 * documento.setBase64(Base64.getEncoder().encodeToString(buffer.toByteArray()))
	 * ;
	 * 
	 * return id;
	 * 
	 * 
	 * }
	 */
}
