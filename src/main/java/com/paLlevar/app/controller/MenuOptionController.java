package com.paLlevar.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paLlevar.app.model.entities.MenuOptionEntity;
import com.paLlevar.app.model.services.MenuOptionService;

@RestController
@RequestMapping("/menu")
public class MenuOptionController {
	
	private static final Logger logger = LogManager.getLogger(MenuOptionController.class);	

	@Autowired
	private MenuOptionService menuService;
	
	@GetMapping(value="/glm/{id}")
	public ResponseEntity<List<MenuOptionEntity>>  getListMenuOption(@PathVariable("id")Integer id){
		logger.info("MenuOptionController.getListMenuOption()");
		List<MenuOptionEntity> lista = menuService.getListMenuOptionByProfileId(id);
		return new ResponseEntity<List<MenuOptionEntity>>(lista,HttpStatus.OK);
		
	}
	
	

}
