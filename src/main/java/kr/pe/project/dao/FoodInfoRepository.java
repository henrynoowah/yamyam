package kr.pe.project.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.AnimalInfo;
import kr.pe.project.model.domain.Food;
import kr.pe.project.model.domain.FoodInfo;
import kr.pe.project.model.domain.Post;

public interface FoodInfoRepository extends CrudRepository<FoodInfo, Long> {
	
	FoodInfo findFoodInfoByAnimalInfo(AnimalInfo animalInfo);
	Iterable<FoodInfo> findFoodInfoByFood(Food food);
	
}
