package com.paLlevar.app.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.paLlevar.app.model.entities.CategoryProductEntity;
import com.paLlevar.app.model.entities.CompanyEntity;
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
	
	@GetMapping(value = "/gp/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> getPhotoById(@PathVariable("id") Integer id) {
		UserEntity c = userService.getOneById(id);
		 byte[]	data = c.getPhoto();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	@PostMapping(path="/uu")
	public UserEntity updatedUser(@RequestPart("user") UserEntity pr, @RequestPart("file") MultipartFile file) throws IOException{
		if(file.getBytes().length >0)
			pr.setPhoto(file.getBytes());
		UserEntity companySave = userService.save(pr);
		return companySave;
	}
	
	
	
	@PostMapping(path="/su")
	public ResponseEntity<Object> saveUserCompany(@RequestBody UserEntity us) {
		/*us.setProfile(new ProfileEntity());
		us.getProfile().setIdProfile(us.getProfile().getIdProfile());*/
		UserEntity userSave = userService.registerUserByProfile(us);
		return new ResponseEntity<Object>(userSave,HttpStatus.CREATED);
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
	
	
	
}
