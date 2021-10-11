package kr.pe.project.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.Food;

public interface FoodRepository extends CrudRepository<Food, Long> {
	
}
