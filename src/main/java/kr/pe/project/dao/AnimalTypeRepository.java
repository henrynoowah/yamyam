package kr.pe.project.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.AnimalType;

public interface AnimalTypeRepository extends CrudRepository<AnimalType, Long> {
	
}
