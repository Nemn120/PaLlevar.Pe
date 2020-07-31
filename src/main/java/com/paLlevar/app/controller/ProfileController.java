package com.paLlevar.app.controller;

import java.util.Arrays;
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
import com.paLlevar.app.model.entities.ProfileEntity;
import com.paLlevar.app.model.entities.ProfileMenuOptionEntity;
import com.paLlevar.app.model.services.ProfileMenuOptionService;
import com.paLlevar.app.model.services.ProfileService;
import com.paLlevar.app.util.Constants;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private ProfileMenuOptionService profileMenuOptionService;
	
	@Autowired
	private ProfileService profileService;
	@GetMapping(value="/gobp/{id}")
	public ResponseEntity<List<ProfileMenuOptionEntity>> getListOptionByProfile(@PathVariable("id") Integer id){
		List<ProfileMenuOptionEntity> listProfileOption= profileMenuOptionService.getOptionsByProfileId(id);
		return new ResponseEntity<List<ProfileMenuOptionEntity>>(listProfileOption,HttpStatus.OK);
	}
	
	@GetMapping(value="/glp")
	public ResponseEntity<List<ProfileEntity>> getListOptionByProfile(){
		List<ProfileEntity> listProfileOption= profileService.getAll();
		return new ResponseEntity<List<ProfileEntity>>(listProfileOption,HttpStatus.OK);
	}
	
	@GetMapping(value="/glpnr")
	public ResponseEntity<List<ProfileEntity>> getListOptionByProfileNotROOT(){
		List<String> profileNameList = Arrays.asList(Constants.PROFILE_CLIENT,Constants.PROFILE_ROOT);
		List<ProfileEntity> listProfileOption= profileService.getProfileListNotIdProfile(profileNameList);
		return new ResponseEntity<List<ProfileEntity>>(listProfileOption,HttpStatus.OK);
	}
	
	
	
	

}
