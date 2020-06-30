package com.paLlevar.app.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.paLlevar.app.model.entities.ProfileEntity;
import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.services.UserService;
import com.paLlevar.app.util.Constants;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(path="/glur")
	public ResponseEntity<List<UserEntity>>  getListUser(){
		List<UserEntity> lista = userService.getAll();
		return new ResponseEntity<List<UserEntity>>(lista,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/gubu",produces = "application/json")
	public ResponseEntity<UserEntity>  getUserByUsername(@RequestBody String username){
		UserEntity user; 
		try {
			user= userService.getUserByUsername(username);
		}catch(Exception e) {
			user=null;

		}
		return new ResponseEntity<UserEntity>(user,HttpStatus.OK);
		
	}
	
	@PostMapping(path="/su")
	public UserEntity saveUser(@RequestBody UserEntity us) {
		UserEntity userSave = userService.save(us);
		return userSave;
	}
	
	@DeleteMapping(value="/du/{id}")
	public void deletedUser(@PathVariable("id")Integer id) {
		userService.deleteById(id);
	}
	
	@PostMapping(path="rcli",produces = "application/json", consumes = "application/json")
	private ResponseEntity<Object> registerClient(@RequestBody UserEntity usuario){
		usuario.setProfile(new ProfileEntity());
		usuario.getProfile().setIdProfile(Constants.CLIENT_USER_ROL);
		userService.registerUserByProfile(usuario);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PostMapping(path="radmin",produces = "application/json", consumes = "application/json")
	private ResponseEntity<Object> registerAdmin(@RequestBody UserEntity usuario){
		usuario.setProfile(new ProfileEntity());
		usuario.getProfile().setIdProfile(Constants.ADMIN_USER_ROL);
		userService.registerUserByProfile(usuario);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	@PostMapping(path="rchef",produces = "application/json", consumes = "application/json")
	private ResponseEntity<Object> registerCheft(@RequestBody UserEntity usuario){
		usuario.setProfile(new ProfileEntity());
		usuario.getProfile().setIdProfile(Constants.MAIN_CHEF_USER_ROL);
		userService.registerUserByProfile(usuario);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	
	
}
