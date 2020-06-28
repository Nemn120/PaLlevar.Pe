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

import com.paLlevar.app.model.entities.MenuDayEntity;
import com.paLlevar.app.model.services.MenuDayService;

@RestController
@RequestMapping("/menuday")
public class MenuDayController {

	String path = "http://localhost:8080/menuday";

	@Autowired
	private MenuDayService menudayService;

	@GetMapping(path="/glmd")
	public ResponseEntity<List<MenuDayEntity>>  getListMenuDay(){
		List<MenuDayEntity> lista = menudayService.getAll();
		return new ResponseEntity<List<MenuDayEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/smd")
	public MenuDayEntity saveMenuDay(@RequestBody MenuDayEntity md) {
		MenuDayEntity menudaySave = menudayService.save(md);
		return menudaySave;
	}
	
	@DeleteMapping(value="/dmd/{id}")
	public void deletedMenuDay(@PathVariable("id")Integer id) {
		menudayService.deleteById(id);
	}
	
	@GetMapping(path="/gmdbdo")
	public ResponseEntity<MenuDayEntity>  getMenuDayByDayAndOrganizationIdAndSucursalId(@RequestBody Integer org, @RequestBody Integer sucursalId){
		MenuDayEntity menuDay= menudayService.getMenuDayByDayAndOrganizationIdAndSucursalId(sucursalId, org);
		return new ResponseEntity<>(menuDay,HttpStatus.OK);
		
	}
	
}
