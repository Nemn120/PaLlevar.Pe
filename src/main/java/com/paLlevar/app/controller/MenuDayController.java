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
@RequestMapping("/menuDay")
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
	@PostMapping(path="/gmdbf")
	public ResponseEntity<List<MenuDayEntity>>  getMenuDayByFields(@RequestBody MenuDayEntity md){
		List<MenuDayEntity> menuDayList= menudayService.getMenuDayListByFields(md);
		return new ResponseEntity<List<MenuDayEntity>>(menuDayList,HttpStatus.OK);
	}
	
	@PostMapping(path="/emd")
	public ResponseEntity<MenuDayEntity> editMenuDay(@RequestBody MenuDayEntity md){
		MenuDayEntity menuEdit= menudayService.editMenuDay(md);
		return new ResponseEntity<MenuDayEntity>(menuEdit,HttpStatus.OK);
	}
	
	@PostMapping(path="/gmdbdos")
	public ResponseEntity<List<MenuDayEntity>>  getMenuDayByDayAndOrganizationIdAndSucursalId(@RequestBody MenuDayEntity md){
		List<MenuDayEntity> menuDayList= menudayService.getMenuDayListByFields(md);
		return new ResponseEntity<List<MenuDayEntity>>(menuDayList,HttpStatus.OK);
	}
	
	@PostMapping(path="/gmdbdo")
	public ResponseEntity<List<MenuDayEntity>>  getMenuDayByDayAndOrganizationId(@RequestBody MenuDayEntity md){
		List<MenuDayEntity> menuDayList= menudayService.getMenuDayListByFields(md);
		return new ResponseEntity<List<MenuDayEntity>>(menuDayList,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/dmd/{id}")
	public void deletedMenuDay(@PathVariable("id")Integer id) {
		menudayService.deleteById(id);
	}
	
	
	
}
