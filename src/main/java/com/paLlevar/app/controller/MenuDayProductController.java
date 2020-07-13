

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

import com.paLlevar.app.model.entities.MenuDayProductEntity;
import com.paLlevar.app.model.services.MenuDayProductService;
import com.paLlevar.app.util.Constants;

@RestController
@RequestMapping("/menuDayProduct")
public class MenuDayProductController {

	String path = "http://localhost:8080/menudayproduct";

	@Autowired
	private MenuDayProductService menudayproductService;

	@GetMapping(path="/glmdp")	// TERMINADO
	public ResponseEntity<List<MenuDayProductEntity>>  getListMenuDayProduct(){
		List<MenuDayProductEntity> lista = menudayproductService.getAll();
		return new ResponseEntity<List<MenuDayProductEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/smdp") // TERMINADO
	public MenuDayProductEntity saveMenuDayProduct(@RequestBody MenuDayProductEntity mdp) {
		MenuDayProductEntity menudayproductSave = menudayproductService.save(mdp);
		return menudayproductSave;
	}
	
	@DeleteMapping(value="/dmdp/{id}")  // TERMINADO 
	public void deletedMenuDayProduct(@PathVariable("id")Integer id) {
		menudayproductService.deleteById(id);
	}
	
	@PostMapping(path="/gmdpbmos")   // TERMINADO
	public ResponseEntity<List<MenuDayProductEntity>>  getListMenuDayProductByMenuIdAndOrgIdAndSucId(@RequestBody MenuDayProductEntity mdp){
		List<MenuDayProductEntity> lista = menudayproductService.getMenuDayProductListByMenuIdAndSucursalIdAndOrganizationId(mdp);
		return new ResponseEntity<List<MenuDayProductEntity>>(lista,HttpStatus.OK);
	}
	
	@PostMapping(path="/gmdpbmo") // TERMINADO
	public ResponseEntity<List<MenuDayProductEntity>>  getListMenuDayProductByMenuIdAndOrgId(@RequestBody MenuDayProductEntity mdp){
		List<MenuDayProductEntity> lista = menudayproductService.getMenuDayProductListByMenuIdAndOrganizationId(mdp);
		return new ResponseEntity<List<MenuDayProductEntity>>(lista,HttpStatus.OK);
	}
	
	@PostMapping(value="/glmbod") // TERMINADO
	public ResponseEntity<List<MenuDayProductEntity>>  getListByOrgAndStatus(@RequestBody MenuDayProductEntity mdp){
		List<MenuDayProductEntity> lista = menudayproductService.getListByOrganizationIdAndStatus(mdp.getOrganizationId(),Constants.MENUD_PROD_STATUS_AVAILABLE);
		return new ResponseEntity<List<MenuDayProductEntity>>(lista,HttpStatus.OK);
	}
	@PostMapping(value="/glmbot") // TERMINADO
	public ResponseEntity<List<MenuDayProductEntity>>  getListByOrgAndStatusAndType(@RequestBody MenuDayProductEntity mdp){
		List<MenuDayProductEntity> lista = menudayproductService.getListByOrganizationIdAndStatusAndType(mdp.getOrganizationId(),Constants.MENUD_PROD_STATUS_AVAILABLE,mdp.getType());
		return new ResponseEntity<List<MenuDayProductEntity>>(lista,HttpStatus.OK);
	}
	
	
}
