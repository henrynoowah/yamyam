package kr.pe.project.model.domain.dto;

import kr.pe.project.model.domain.Food;
import kr.pe.project.model.domain.FoodCat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class FoodCatDTO {
	
	@Data
	@NoArgsConstructor
	public static class Add {

		public Add(long id, Integer eatable, String amount, String info) {
			super();
			this.id = id;
			this.food = null;
			this.eatable = eatable;
			this.amount = amount;
			this.info = info;
		}

		private long id;
		private Food food; //food_food_idx
		private Integer eatable;
		private String amount;
		private String info; 
		
		public FoodCat toEntity() {
			FoodCat foodCat = new FoodCat();
			foodCat.setId(id);
			foodCat.setFood(food);
			foodCat.setEatable(eatable);
			foodCat.setAmount(amount);
			foodCat.setInfo(info);
			
			return foodCat;
		}
	}
	
	public static class Update extends Add {
		
	}
	
}
