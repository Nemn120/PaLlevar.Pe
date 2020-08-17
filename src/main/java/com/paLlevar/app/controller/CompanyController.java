package com.paLlevar.app.controller;

import java.io.IOException;
import java.util.List;

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
import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.services.CompanyService;
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
	public CompanyEntity saveCompany(@RequestPart("company") CompanyEntity pr, @RequestPart("file") MultipartFile file) throws IOException{
		logger.info("CompanyController.saveCompany()");
		if(file.getBytes().length >0)
			pr.setPhoto(file.getBytes());
		else
			logger.warn("No se almaceno foto en la compañia " + pr.getNombre());
		CompanyEntity companySave = companyService.save(pr);
		return companySave;
	}
	
	@DeleteMapping(value="/dco/{id}")
	public void deletedCompany(@PathVariable("id")Integer id) {
		logger.info("CompanyController.deletedCompany()");
		companyService.deleteById(id);
	}
}
