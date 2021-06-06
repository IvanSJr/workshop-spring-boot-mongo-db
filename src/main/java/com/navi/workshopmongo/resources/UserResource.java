package com.navi.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.navi.workshopmongo.domain.User;
import com.navi.workshopmongo.dto.UserDTO;
import com.navi.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping 
	public ResponseEntity<List<UserDTO>> findAll(){
		List <User> list = service.findAll();
		List <UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		//userReposiroty.saveAll(Arrays.asList(maria, alex, bob));
		return ResponseEntity.ok().body(listDto);

	}
	
}
