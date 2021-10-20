package kr.pe.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.project.annotation.NoSessionCheck;
import kr.pe.project.dao.AnimalTypeRepository;
import kr.pe.project.model.domain.AnimalType;

@RestController
//@SessionAttributes({"user"})
public class AnimalTypeController {
	
	AnimalTypeController(){
		System.out.println("PetUserController(){}");
	}
	

	@Autowired
	private AnimalTypeRepository dao;

	@NoSessionCheck
	@GetMapping("getAnimalType")
	public Iterable<AnimalType> getAnimalTypeAll() {
		Iterable<AnimalType> all = dao.findAll();
		
		return all;
	}
	
//	@ExceptionHandler
	public String PetUserException(Exception e) {
		return e.getMessage();
	}

}
