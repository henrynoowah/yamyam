package kr.pe.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.Food;

public interface FoodRepository extends CrudRepository<Food, Long> {

	Food findFoodByName(String name);

	List<Food> findFoodByCategory(String category);
	
}
