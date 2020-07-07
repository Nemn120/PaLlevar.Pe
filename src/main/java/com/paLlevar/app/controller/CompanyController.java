package com.paLlevar.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paLlevar.app.model.entities.CompanyEntity;
import com.paLlevar.app.model.services.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {

	String path = "http://localhost:8080/company";
	
	@Autowired
	private CompanyService companyService;
	
	
	@GetMapping(path="/glco")
	public ResponseEntity<List<CompanyEntity>>  getListCompany(){
		List<CompanyEntity>  lista=companyService.getAll();
		return new ResponseEntity<List<CompanyEntity>>(lista,HttpStatus.OK);
	}
	@GetMapping(value="/gcobi")
	public ResponseEntity<CompanyEntity>  getCompanyById(@PathVariable("id")Integer id){
		CompanyEntity  company=companyService.getOneById(id);
		return new ResponseEntity<CompanyEntity>(company,HttpStatus.OK);
	}
	
	@PostMapping(path="/sco")
	public CompanyEntity saveCompany(@RequestBody CompanyEntity com){
		CompanyEntity companySave = companyService.save(com);
		return companySave;
	}
	
	@DeleteMapping(value="/dco/{id}")
	public void deletedCompany(@PathVariable("id")Integer id) {
		companyService.deleteById(id);
	}
}
