package kr.pe.project.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.PetUser;
import kr.pe.project.model.domain.dto.PetUserDTO.Register;

public interface PetUserRepository extends CrudRepository<PetUser, String> {
	
	// select * from pet_user where id=?
	PetUser findPetUserById(String id);
	
}
