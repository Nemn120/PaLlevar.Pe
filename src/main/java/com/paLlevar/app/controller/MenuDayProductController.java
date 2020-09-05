

package com.paLlevar.app.controller;

import java.util.List;
import java.util.HashMap;
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
import com.paLlevar.app.model.services.MenuDayProductService;
import com.paLlevar.app.util.Constants;

@RestController
@RequestMapping("/menuDayProduct")
public class MenuDayProductController {

	String path = "http://localhost:8080/menudayproduct";
	private static final Logger logger = LogManager.getLogger(MenuDayProductController.class);	

	@Autowired
	private MenuDayProductService menudayproductService;

	@GetMapping(path="/glmdp")
	public ResponseEntity<List<MenuDayProductEntity>>  getListMenuDayProduct(){
		logger.info("MenuDayProductController.getListMenuDayProduct()");
		List<MenuDayProductEntity> lista = menudayproductService.getAll();
		return new ResponseEntity<List<MenuDayProductEntity>>(lista,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/dmdp/{id}")  
	public void deletedMenuDayProduct(@PathVariable("id")Integer id) {
		logger.info("MenuDayProductController.deletedMenuDayProduct()");
		menudayproductService.deleteById(id);
	}
	
	@PostMapping(path="/smdp") 
	public ResponseEntity<?> saveMenuDayProduct(@RequestBody MenuDayProductEntity mdp) {
		logger.info("MenuDayProductController.saveMenuDayProduct()");
		Map<String,Object> response = new HashMap<>();
		mdp.setMenuDay(new MenuDayEntity());
		mdp.getMenuDay().setId(mdp.getMenuDayId()); //
		try {
			if(mdp.getId()!=null) {
				logger.info("Actualizar MenuDayProduct"+", Funcion: "+"saveEditMenuDayProduct");
				menudayproductService.saveEditMenuDayProduct(mdp);
				response.put(Constants.MESSAGE_BODY_RESPONSE,"Platillo modificado con éxito" );
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			}else {
				if(mdp.getQuantity()!=0)
					mdp.setStatus(Constants.MENUD_PROD_STATUS_AVAILABLE);
				else
					mdp.setStatus(Constants.MENUD_PROD_STATUS_NOT_AVAILABLE);
				logger.info("Nuevo MenuDayProduct"+", Funcion: "+"save");
				menudayproductService.save(mdp);
				response.put(Constants.MESSAGE_BODY_RESPONSE,"Platillo agregado con éxito" );
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			}			
		}catch(Exception e) {
			response.put(Constants.MESSAGE_BODY_RESPONSE,"Error al modificar/agregar producto" );
			logger.info("ERROR ===> ",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}			
	}
	
	@PostMapping(path="/dmdp")  
	public  ResponseEntity<?>  deletedMenuDayProduct(@RequestBody MenuDayProductEntity mdp) {
		logger.info("MenuDayProductController.deletedMenuDayProduct()");
		Map<String,Object> response = new HashMap<>();
		try {
			menudayproductService.deleteMenuDayProduct(mdp);
			response.put(Constants.MESSAGE_BODY_RESPONSE,"Platillo eliminado con exito" );
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);

		}catch(Exception e) {
			response.put(Constants.MESSAGE_BODY_RESPONSE,"Error al eliminar un platillo" );
			logger.info("ERROR ===> ",e.getMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path="/gmdpbmos")   
	public ResponseEntity<List<MenuDayProductEntity>>  getListMenuDayProductByMenuIdAndOrgIdAndSucId(@RequestBody MenuDayProductEntity mdp){
		logger.info("MenuDayProductController.getListMenuDayProductByMenuIdAndOrgIdAndSucId()");
		List<MenuDayProductEntity> lista = menudayproductService.getMenuDayProductListByMenuIdAndSucursalIdAndOrganizationId(mdp);
		return new ResponseEntity<List<MenuDayProductEntity>>(lista,HttpStatus.OK);
	}
	
	@PostMapping(path="/gmdpbmo") 
	public ResponseEntity<List<MenuDayProductEntity>>  getListMenuDayProductByMenuIdAndOrgId(@RequestBody MenuDayProductEntity mdp){
		logger.info("MenuDayProductController.getListMenuDayProductByMenuIdAndOrgId()");
		List<MenuDayProductEntity> lista = menudayproductService.getMenuDayProductListByMenuIdAndOrganizationId(mdp);
		return new ResponseEntity<List<MenuDayProductEntity>>(lista,HttpStatus.OK);
	}
	
	@PostMapping(value="/glmbod") 
	public ResponseEntity<List<MenuDayProductEntity>>  getListByOrgAndStatus(@RequestBody MenuDayProductEntity mdp){
		logger.info("MenuDayProductController.getListByOrgAndStatus()");
		List<MenuDayProductEntity> lista = menudayproductService.getListByOrganizationIdAndStatus(mdp.getOrganizationId(),Constants.MENUD_PROD_STATUS_AVAILABLE);
		return new ResponseEntity<List<MenuDayProductEntity>>(lista,HttpStatus.OK);
	}
	@PostMapping(value="/glmbot") 
	public ResponseEntity<List<MenuDayProductEntity>>  getListByOrgAndStatusAndType(@RequestBody MenuDayProductEntity mdp){
		logger.info("MenuDayProductController.getListByOrgAndStatusAndType()");
		List<MenuDayProductEntity> lista = menudayproductService.getListByOrganizationIdAndStatusAndType(mdp.getOrganizationId(),Constants.MENUD_PROD_STATUS_AVAILABLE,mdp.getType());
		return new ResponseEntity<List<MenuDayProductEntity>>(lista,HttpStatus.OK);
	}
	
	
}
