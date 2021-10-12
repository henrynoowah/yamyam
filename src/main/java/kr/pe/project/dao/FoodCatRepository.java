package kr.pe.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.FoodCat;
import kr.pe.project.model.domain.FoodDog;

public interface FoodCatRepository extends CrudRepository<FoodCat, Long> {

	FoodCat findFoodCatById(Long id);

	List<FoodCat> findFoodCatByEatable(Integer eatable);

	
}
