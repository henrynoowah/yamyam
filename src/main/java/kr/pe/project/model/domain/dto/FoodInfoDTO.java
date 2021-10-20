package kr.pe.project.model.domain.dto;

import kr.pe.project.model.domain.AnimalInfo;
import kr.pe.project.model.domain.Food;

public class FoodInfoDTO {
	
	public static class Add {
		private long id;
		private AnimalInfo animalInfo;
		private Food food;
	}
}