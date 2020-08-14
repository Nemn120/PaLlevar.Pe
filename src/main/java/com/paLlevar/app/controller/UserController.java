package com.paLlevar.app.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.paLlevar.app.model.entities.ProfileEntity;
import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.services.UserService;
import com.paLlevar.app.util.Constants;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@GetMapping(path="/glur")
	public ResponseEntity<List<UserEntity>>  getListUser(){
		List<UserEntity> lista = userService.getAll();
		return new ResponseEntity<List<UserEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/gubu",produces = "application/json")
	public ResponseEntity<?>  getUserByUsername(@RequestBody String username){
		UserEntity user; 
		try {
			user= userService.getUserByUsername(username);
			return new ResponseEntity<UserEntity>(user,HttpStatus.OK);
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentra el usuario");
		}
	}
	
	@GetMapping(value = "/gp/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getPhotoById(@PathVariable("id") Integer id) {
		UserEntity c = userService.getOneById(id);
		 byte[]	data = c.getPhoto();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	@PostMapping(path="/uu")
	public ResponseEntity<?> updatedUser(@RequestPart("user") UserEntity pr, @RequestPart("file") MultipartFile file) throws IOException{
		Map<String,Object> response = new HashMap<>();
		try {
			if(file.getBytes().length >0)
				pr.setPhoto(file.getBytes());
			userService.save(pr);
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Perfil actualizado con éxito");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}catch(Exception e) {
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error al actualizar perfil");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path="/su")
	public ResponseEntity<Object> saveUserCompany(@RequestBody UserEntity us) {
		UserEntity userSave = userService.registerUserByProfile(us);
		return new ResponseEntity<Object>(userSave,HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/du/{id}")
	public void deletedUser(@PathVariable("id")Integer id) {
		userService.deleteById(id);
	}
	@PostMapping(path="/usu")
	public ResponseEntity<?> updateStatusUser(@RequestBody UserEntity user) throws IOException{
		Map<String,Object> response = new HashMap<>();
		try {
			userService.updateStatusById(user.getId(), user.getStatus());
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Estado actualizado con éxito");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}catch(Exception e) {
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error al actualizar estado");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(path="rcli",produces = "application/json", consumes = "application/json")
	private ResponseEntity<Object> registerClient(@RequestBody UserEntity usuario){
		usuario.setProfile(new ProfileEntity());
		usuario.getProfile().setIdProfile(Constants.CLIENT_USER_ROL);
		userService.registerUserByProfile(usuario);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@GetMapping(value="/gubo/{id}")
	public  ResponseEntity<List<UserEntity>>getUserListByOrg(@PathVariable("id")Integer id) {
		List<UserEntity> userList= userService.getUserListByOrganizationId(id);
		return new ResponseEntity<List<UserEntity>>(userList,HttpStatus.OK);
	}
	
	@PostMapping(path="/guldm")
	public ResponseEntity<List<UserEntity>> getListUserDeliveryMan(@RequestBody UserEntity user){
		user.setProfile(new ProfileEntity());
		user.getProfile().setIdProfile(Constants.DELIVERY_MAN_USER_ROL);
		user.setStatus(Constants.DELIVERY_MAN_STATUS_DISPONIBLE);
		List<UserEntity> lista=userService.getUserListByProfileANDStatus(user);
		return new ResponseEntity<List<UserEntity>>(lista,HttpStatus.OK);
	}
	
	@PostMapping(path="/gludmos")
	public ResponseEntity<?> getListUserDeliveryManByOrganizationANDStatus(@RequestBody UserEntity user){
		Map<String,Object> response= new HashMap<>();
		log.info("Funcion: "+"getListUserDeliveryManByOrganizationANDStatus");
		try {
			response.put("dataList",userService.getUserListByOrganizationIdANDbyStatus(user));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		} catch (Exception e) {
			log.error("Se produjo un error: "+e.getMessage().toString());
			response.put(Constants.MESSAGE_BODY_RESPONSE, "Error al realizar la peticion");
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	
	
}
