package com.paLlevar.app.controller;

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

import com.paLlevar.app.model.services.BranchOfficeService;
import com.paLlevar.app.model.entities.BranchOfficeEntity;
import java.util.List;
@RestController
@RequestMapping("/branchoffice")
public class BranchOfficeController {
	
	String path = "http://localhost:8080/branch";
	
	@Autowired
	private BranchOfficeService branchOfficeService;
	
	
	@GetMapping(path="/glbo")
	public ResponseEntity<List<BranchOfficeEntity>>  getListBranchOffice(){
		List<BranchOfficeEntity>  lista=branchOfficeService.getAll();
		return new ResponseEntity<List<BranchOfficeEntity>>(lista,HttpStatus.OK);
	}
	@PostMapping(path="/sbo")
	public BranchOfficeEntity saveBranchOffice(@RequestBody BranchOfficeEntity boe){
		BranchOfficeEntity branchOfficeEntity = branchOfficeService.save(boe);
		return branchOfficeEntity;
	}
	@DeleteMapping(value="/dbo/{id}")
	public void deletedBranchOffice(@PathVariable("id")Integer id) {
		branchOfficeService.deleteById(id);
	}

}