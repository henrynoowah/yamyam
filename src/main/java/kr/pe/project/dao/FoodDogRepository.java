package kr.pe.project.dao;

import org.springframework.data.repository.CrudRepository;

import kr.pe.project.model.domain.FoodDog;

public interface FoodDogRepository extends CrudRepository<FoodDog, Long> {

}
