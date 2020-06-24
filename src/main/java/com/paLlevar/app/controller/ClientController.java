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
import com.paLlevar.app.model.entities.ClientEntity;
import com.paLlevar.app.model.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping(path="/glcl")
	public ResponseEntity<List<ClientEntity>>  getListClient(){
		List<ClientEntity>  lista=clientService.getAll();
		return new ResponseEntity<List<ClientEntity>>(lista,HttpStatus.OK);
	}
	@PostMapping(path="/scl")
	public ClientEntity saveClient(@RequestBody ClientEntity cle){
		ClientEntity categoryProductEntity = clientService.save(cle);
		return categoryProductEntity;
	}
	@DeleteMapping(value="/dcl/{id}")
	public void deletedClient(@PathVariable("id")Integer id) {
		clientService.deleteById(id);
	}
}
