package kr.pe.project.model.domain.dto;

import kr.pe.project.model.domain.FoodDog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FoodDogDTO {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Add {
		private long id;
		private Integer eatable;
		private String amount;
		private String info;
		
		public FoodDog toEntity() {
			FoodDog foodDog = new FoodDog();
			foodDog.setId(id);
			foodDog.setEatable(eatable);
			foodDog.setAmount(amount);
			foodDog.setInfo(info);
			
			return foodDog;
		}
	}
	
	public static class Update extends Add {}

}
