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
import com.paLlevar.app.model.entities.UserEntity;
import com.paLlevar.app.model.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping(path="/usr")
	public ResponseEntity<List<UserEntity>>  getListProduct(){
		List<UserEntity> lista = userService.getAll();
		return new ResponseEntity<List<UserEntity>>(lista,HttpStatus.OK);
		
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
	
}
