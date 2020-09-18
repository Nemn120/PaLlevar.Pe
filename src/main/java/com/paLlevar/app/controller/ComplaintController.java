package com.paLlevar.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.paLlevar.app.model.entities.ComplaintEntity;
import com.paLlevar.app.model.services.ComplaintService;
import com.paLlevar.app.util.Constants;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {
	
	private static final Logger logger = LogManager.getLogger(ComplaintController.class);

	@Autowired
	private ComplaintService complaintService;
	
	@GetMapping(path="/glcpt")
	public ResponseEntity<?>  getListComplaint()throws IOException{
		Map<String,Object> response= new HashMap<>();
		logger.info("ComplaintController.getListComplaint()");
		try {
			response.put(Constants.DATA_RESPONSE,complaintService.getAll());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Se produjo un error: "+e.getMessage().toString());
			response.put(Constants.MESSAGE_BODY_RESPONSE,"Error al obtener la lista de reclamos");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/gpcpt/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getPhotoById(@PathVariable("id") Integer id) {
		logger.info("ComplaintController.getPhotoById()");
		ComplaintEntity c = complaintService.getOneById(id);
		 byte[]	data = c.getPhoto();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	@PostMapping(path="/scpt")
	public ComplaintEntity saveComplaint(@RequestPart("complaint") ComplaintEntity cpt, @RequestPart("file") MultipartFile file) throws IOException{
		logger.info("ComplaintController.saveComplaint()");
		if(file.getBytes().length >0)
			cpt.setPhoto(file.getBytes());
		else
			logger.warn("No se almaceno foto del Reclamo " + cpt.getTitulo());
		ComplaintEntity complaintSave = complaintService.save(cpt);
		return complaintSave;
	}
	
	@DeleteMapping(value="/dcpt/{id}")
	public void deletedComplaint(@PathVariable("id")Integer id) {
		logger.info("ComplaintController.deletedComplaint()");
		complaintService.deleteById(id);
	}
	
	@GetMapping(value="/gcpt/{id}")
	public ResponseEntity<?>  getComplaintById(@PathVariable("id")Integer id)throws IOException{
		Map<String,Object> response= new HashMap<>();
		logger.info("Funcion: "+"ComplaintController.getComplaintById()");
		try {
			response.put(Constants.DATA_RESPONSE,complaintService.getOneById(id));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Se produjo un error: "+e.getMessage().toString());
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error al obtener el reclamo por id");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);			
		}	
	}
	
	@PostMapping(path="/glcptbo")
	public ResponseEntity<?>  getListComplaintByOrg(@RequestBody ComplaintEntity cpt)throws IOException{
		Map<String,Object> response= new HashMap<>();
		logger.info("Funcion: "+"ComplaintController.getListComplaintByOrg()");
		try {
			response.put(Constants.DATA_RESPONSE,complaintService.getAllComplaintByOrganizationId(cpt.getOrganizationId()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Se produjo un error: "+e.getMessage().toString());
			response.put(Constants.MESSAGE_BODY_RESPONSE,"Error al obtener la lista de reclamos por Organizacion");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/gcboi/{id}")
	public ResponseEntity<?>  getComplaintByOrderId(@PathVariable("id")Integer id)throws IOException{
		Map<String,Object> response= new HashMap<>();
		logger.info("Funcion: "+"ComplaintController.getComplaintById()");
		try {
			response.put(Constants.DATA_RESPONSE,complaintService.findComplaintByOrderId(id));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Se produjo un error: "+e.getMessage().toString());
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error al obtener el reclamo de la orden");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);			
		}	
	}
	
	
	
	

}
