package kr.pe.project.model.domain.dto;

import kr.pe.project.model.domain.FoodCat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FoodCatDTO {
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Add {
		private long id;
		private Integer eatable;
		private String amount;
		private String info;
		
		public FoodCat toEntity() {
			FoodCat foodCat = new FoodCat();
			foodCat.setId(id);
			foodCat.setEatable(eatable);
			foodCat.setAmount(amount);
			foodCat.setInfo(info);
			
			return foodCat;
		}
	}
	
	public static class Update extends Add {
		
	}
	
}
