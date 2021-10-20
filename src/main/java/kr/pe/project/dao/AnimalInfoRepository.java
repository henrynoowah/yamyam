package kr.pe.project.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.AnimalInfo;

public interface AnimalInfoRepository extends CrudRepository<AnimalInfo, Long> {

	
}
