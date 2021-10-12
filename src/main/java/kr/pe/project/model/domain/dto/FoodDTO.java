package kr.pe.project.model.domain.dto;

import kr.pe.project.model.domain.Food;
import kr.pe.project.model.domain.FoodCat;
import kr.pe.project.model.domain.FoodDog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class FoodDTO {
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Add {
		private String name;
		private String category;
		private FoodDog dog;
		private FoodCat cat;
		
		public Food toEntity() {
			return Food.builder().name(name)
								 .category(category)
								 .dog(dog)
								 .cat(cat).build();
		}
	}
	
	@Getter
	@Setter
	public static class Update extends Add {
		private long id;
	}
}
