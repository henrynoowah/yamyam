package kr.pe.project.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.FoodInfo;

public interface FoodInfoRepository extends CrudRepository<FoodInfo, Long> {
	
}
