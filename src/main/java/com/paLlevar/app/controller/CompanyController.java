package com.paLlevar.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

import com.paLlevar.app.model.entities.CompanyEntity;
import com.paLlevar.app.model.services.CompanyService;
import com.paLlevar.app.model.services.dto.RequesDTO;
import com.paLlevar.app.util.Constants;

@RestController
@RequestMapping("/company")
public class CompanyController {

	String path = "http://localhost:8080/company";
	private static final Logger logger = LogManager.getLogger(CompanyController.class);	

	@Autowired
	private CompanyService companyService;
	
	
	@GetMapping(path="/glco")
	public ResponseEntity<List<CompanyEntity>>  getListCompany(){
		logger.info("CompanyController.getListCompany()");
		List<CompanyEntity>  lista=companyService.getAll();
		return new ResponseEntity<List<CompanyEntity>>(lista,HttpStatus.OK);
	}
	
	@GetMapping(path="/glcoa")
	public ResponseEntity<List<CompanyEntity>>  getListCompanyActive(){
		logger.info("CompanyController.getListCompanyActive()");
		List<CompanyEntity>  lista=companyService.getCompanyListByStatus(Constants.STATUS_ON_ENTITY);
		return new ResponseEntity<List<CompanyEntity>>(lista,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/gcobi/{id}")
	public ResponseEntity<CompanyEntity>  getCompanyById(@PathVariable("id")Integer id){
		logger.info("CompanyController.getCompanyById()");
		CompanyEntity  company=companyService.getOneById(id);
		return new ResponseEntity<CompanyEntity>(company,HttpStatus.OK);
	}
	
	@GetMapping(value = "/gp/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getPhotoById(@PathVariable("id") Integer id) {
		CompanyEntity c = companyService.getOneById(id);
		 byte[]	data = c.getPhoto();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}

	@PostMapping(path="/sco")
	public CompanyEntity saveCompany(@RequestPart("company") CompanyEntity pr, @RequestPart("logoImage") MultipartFile logoImage) throws IOException{
		logger.info("CompanyController.saveCompany()");
		if(logoImage != null && logoImage.getBytes().length >0)
			pr.setPhoto(logoImage.getBytes());
	//	if(panelImage != null && panelImage.getBytes().length>0)
	//		pr.setImagePanel(panelImage.getBytes());

		CompanyEntity companySave = companyService.save(pr);
		return companySave;
	}
	
	@PostMapping(path="/uli")
	public ResponseEntity<Map<String,Object>> updateLogoImage(@RequestPart("company") RequesDTO request, @RequestPart("logoImage") MultipartFile logoImage) throws IOException{
		logger.info("CompanyController.updateLogoImage()");
		Map<String,Object> response = new HashMap<>();
		try {
			CompanyEntity comp = new CompanyEntity();
			comp.setId(request.getId());
			if(logoImage != null && logoImage.getBytes().length >0)
				comp.setPhoto(logoImage.getBytes());
			
			response.put(Constants.DATA_RESPONSE,companyService.save(comp));
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Logo actualizado con éxito");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			
		}catch(Exception e){
			response.put("error", "Error al actualizar imagen");
			logger.error("ERORR ==> ",e);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping(path="/upi")
	public ResponseEntity<Map<String,Object>> updatePanelImage(@RequestPart("company")  RequesDTO request,@RequestPart("panelImage") MultipartFile panelImage) throws IOException{
		logger.info("CompanyController.updatePanelImage()");
		Map<String,Object> response = new HashMap<>();
		try {
			CompanyEntity comp = new CompanyEntity();
			comp.setId(request.getId());
			if(panelImage != null && panelImage.getBytes().length>0)
				comp.setImagePanel(panelImage.getBytes());
			 
			response.put(Constants.DATA_RESPONSE,companyService.save(comp));
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Imagen actualizado con éxito");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}catch(Exception e){
			response.put("error", "Error al actualizar imagen");
			logger.error("ERORR ==> ",e);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@DeleteMapping(value="/dco/{id}")
	public void deletedCompany(@PathVariable("id")Integer id) {
		logger.info("CompanyController.deletedCompany()");
		companyService.deleteById(id);
	}
	
	@GetMapping(value = "/gpi/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getPanelImageById(@PathVariable("id") Integer id) {
		CompanyEntity c = companyService.getOneById(id);
		 byte[]	data = c.getImagePanel();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	@PostMapping(path="/udc")
	public ResponseEntity<Map<String,Object>> updateDataCompany(@RequestBody CompanyEntity company) {
		logger.info("CompanyController.updateDataCompany()");
		Map<String,Object> response = new HashMap<>();
		try {
			response.put(Constants.DATA_RESPONSE,companyService.updateDataCompany(company));
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Información actualizado con éxito");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}catch(Exception e){
			response.put("error", "Error al actualizar información");
			logger.error("ERORR ==> ",e);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
