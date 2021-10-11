package kr.pe.project.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.FoodCat;

public interface FoodCatRepository extends CrudRepository<FoodCat, Long> {
	
}
