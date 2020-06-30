package com.paLlevar.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paLlevar.app.model.entities.ProductEntity;
import com.paLlevar.app.model.entities.ProfileMenuOptionEntity;
import com.paLlevar.app.model.services.ProfileMenuOptionService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private ProfileMenuOptionService profileService;
	
	@GetMapping(value="/gobp/{id}")
	public ResponseEntity<List<ProfileMenuOptionEntity>> getListOptionByProfile(@PathVariable("id") Integer id){
		List<ProfileMenuOptionEntity> listProfileOption= profileService.getOptionsByProfileId(id);
		return new ResponseEntity<List<ProfileMenuOptionEntity>>(listProfileOption,HttpStatus.OK);
	}
	
	

}
