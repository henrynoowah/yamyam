package kr.pe.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.FoodDog;

public interface FoodDogRepository extends CrudRepository<FoodDog, Long> {

	List<FoodDog> findFoodDogByEatable(Integer eatable);


}
