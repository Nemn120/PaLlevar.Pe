package com.paLlevar.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.paLlevar.app.model.entities.MenuDayProductEntity;
import com.paLlevar.app.model.services.MenuDayService;
import com.paLlevar.app.util.Constants;

@RestController
@RequestMapping("/menuDay")
public class MenuDayController {

	String path = "http://localhost:8080/menuday";
	private static final Logger logger = LogManager.getLogger(MenuDayController.class);	

	@Autowired
	private MenuDayService menudayService;

	@GetMapping(path="/glmd") 
	public ResponseEntity<List<MenuDayEntity>>  getListMenuDay(){
		logger.info("MenuDayController.getListMenuDay()");
		List<MenuDayEntity> lista = menudayService.getAll();
		return new ResponseEntity<List<MenuDayEntity>>(lista,HttpStatus.OK);
	}
	
	@GetMapping(value="/gmbi/{id}")
	public ResponseEntity<MenuDayEntity>  getMenuDayByID(@PathVariable("id")Integer id){
		logger.info("MenuDayController.getMenuByDay()");
		MenuDayEntity menuDay = menudayService.getOneById(id);
		return new ResponseEntity<MenuDayEntity>(menuDay,HttpStatus.OK);
		
	}

	@DeleteMapping(value="/dmd/{id}") 
	public void deletedMenuDay(@PathVariable("id")Integer id) {
		logger.info("MenuDayController.deletedMenuDay()");
		menudayService.deleteMenuDayAndProductDayList(id);
	}
	
	@GetMapping(path="/glpbo/{id}")
	public ResponseEntity<List<MenuDayEntity>>  getMenuDayByOrganizationId(@PathVariable("id")Integer id){
		logger.info("MenuDayController.getMenuDayByOrganizationId()");
		List<MenuDayEntity> menuDayList= menudayService.getMenuDayListByOrg(id);
		if(menuDayList == null )
			menuDayList = new ArrayList<MenuDayEntity>();
		return new ResponseEntity<List<MenuDayEntity>>(menuDayList,HttpStatus.OK);
	}
	
	
	@PostMapping(path="/gmdbsos")
	public ResponseEntity<List<MenuDayEntity>>  getMenuDayByStatusAndOrganizationIdAndSucursalId(@RequestBody MenuDayEntity md){
		logger.info("MenuDayController.getMenuDayByStatusAndOrganizationIdAndSucursalId()");
		List<MenuDayEntity> menuDayList= menudayService.getMenuDayListByStatusAndOrgAndSuc(md);
		return new ResponseEntity<List<MenuDayEntity>>(menuDayList,HttpStatus.OK);
	}
	
	@PostMapping(path="/gmdbso")
	public ResponseEntity<List<MenuDayEntity>>  getMenuDayByStatusAndOrganizationId(@RequestBody MenuDayEntity md){
		logger.info("MenuDayController.getMenuDayByStatusAndOrganizationId()");
		md.setStatus(Constants.STATUS_ON_ENTITY);
		List<MenuDayEntity> menuDayList= menudayService.getMenuDayListByStatusAndOrg(md);
		return new ResponseEntity<List<MenuDayEntity>>(menuDayList,HttpStatus.OK);
	}
	
	@PostMapping(path="/smd")
	public Integer saveMenuDay(@RequestBody MenuDayEntity md) {
		logger.info("MenuDayController.saveMenuDay()");
		MenuDayEntity menudaySave = menudayService.saveMenuDay(md);
		return menudaySave.getId();
	}
	@PostMapping(path="/gmdbf") 
	public ResponseEntity<List<MenuDayEntity>>  getMenuDayByFields(@RequestBody MenuDayEntity md){
		logger.info("MenuDayController.getMenuDayByFields()");
		List<MenuDayEntity> menuDayList= menudayService.getMenuDayListByFields(md);
		return new ResponseEntity<List<MenuDayEntity>>(menuDayList,HttpStatus.OK);
	}
	
	@PostMapping(path="/emd")
	public ResponseEntity<MenuDayEntity> editMenuDay(@RequestBody MenuDayEntity md){
		logger.info("MenuDayController.editMenuDay()");
		MenuDayEntity menuEdit= menudayService.editMenuDay(md);
		return new ResponseEntity<MenuDayEntity>(menuEdit,HttpStatus.OK);
	}
	
	@PostMapping(path="/usmd")
	public ResponseEntity<?> updateStatusMenuDay(@RequestBody MenuDayEntity md){
		logger.info("MenuDayController.updateStatusMenuDay()");
		Map<String,Object> response = new HashMap<>();
		try {
			logger.info(md.getStatus());
			menudayService.updateStatusMenuDay(md);
			response.put(Constants.MESSAGE_BODY_RESPONSE, "estado actualizado con exito");
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}catch(Exception e) {
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error al actualizar estado");
			logger.error("ERROR ==>", e);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/glmdbd/{id}")
	public ResponseEntity<?> getListMenuDayByDay(@PathVariable("id")Integer id){
		logger.info("MenuDayController.getMenuByDay()");
		Map<String,Object> response = new HashMap<String,Object>();
		
		try {
			
			Map<String,List<MenuDayProductEntity>> list = menudayService.getListMenuDayByDay(id);
			response.put("dataList", list);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
		}catch(Exception e) {
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error");
			logger.error("ERROR ==>", e);
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
