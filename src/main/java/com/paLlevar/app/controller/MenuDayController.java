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
import com.paLlevar.app.util.Constants;

@RestController
@RequestMapping("/menuDay")
public class MenuDayController {

	String path = "http://localhost:8080/menuday";

	@Autowired
	private MenuDayService menudayService;

	@GetMapping(path="/glmd") // TERMINADO 
	public ResponseEntity<List<MenuDayEntity>>  getListMenuDay(){
		List<MenuDayEntity> lista = menudayService.getAll();
		return new ResponseEntity<List<MenuDayEntity>>(lista,HttpStatus.OK);
		
	}
	@GetMapping(value="/gmbi/{id}") // PENDIENTE
	public ResponseEntity<MenuDayEntity>  getMenuByDay(@PathVariable("id")Integer id){
		MenuDayEntity menuDay = menudayService.getOneById(id);
		return new ResponseEntity<MenuDayEntity>(menuDay,HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/dmd/{id}") //  TERMINADO
	public void deletedMenuDay(@PathVariable("id")Integer id) {
		menudayService.deleteMenuDayAndProductDayList(id);
	}
	
	
	@PostMapping(path="/gmdbsos") // TERMINADO
	public ResponseEntity<List<MenuDayEntity>>  getMenuDayByStatusAndOrganizationIdAndSucursalId(@RequestBody MenuDayEntity md){
		List<MenuDayEntity> menuDayList= menudayService.getMenuDayListByStatusAndOrgAndSuc(md);
		return new ResponseEntity<List<MenuDayEntity>>(menuDayList,HttpStatus.OK);
	}
	
	@PostMapping(path="/gmdbso") // TERMINADO
	public ResponseEntity<List<MenuDayEntity>>  getMenuDayByStatusAndOrganizationId(@RequestBody MenuDayEntity md){
		md.setStatus(Constants.STATUS_ON_ENTITY);
		List<MenuDayEntity> menuDayList= menudayService.getMenuDayListByStatusAndOrg(md);
		return new ResponseEntity<List<MenuDayEntity>>(menuDayList,HttpStatus.OK);
	}
	
	@PostMapping(path="/smd") // TERMINADO 100%
	public Integer saveMenuDay(@RequestBody MenuDayEntity md) {
		MenuDayEntity menudaySave = menudayService.saveMenuDay(md);
		return menudaySave.getId();
	}
	@PostMapping(path="/gmdbf")  // PENDIENTE
	public ResponseEntity<List<MenuDayEntity>>  getMenuDayByFields(@RequestBody MenuDayEntity md){
		List<MenuDayEntity> menuDayList= menudayService.getMenuDayListByFields(md);
		return new ResponseEntity<List<MenuDayEntity>>(menuDayList,HttpStatus.OK);
	}
	
	@PostMapping(path="/emd")  // TERMINADO 
	public ResponseEntity<MenuDayEntity> editMenuDay(@RequestBody MenuDayEntity md){
		MenuDayEntity menuEdit= menudayService.editMenuDay(md);
		return new ResponseEntity<MenuDayEntity>(menuEdit,HttpStatus.OK);
	}
	
}
