package kr.pe.project.model.domain.dto;

import kr.pe.project.model.domain.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FoodDTO {
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Add {
		private String name;
		private String category;
		
		public Food toEntity() {
			return Food.builder().name(name)
								 .category(category)
								 .build();
		}
	}
	
	@Data
	@AllArgsConstructor
	public static class Update {
		private String name;
		private String category;
		
		public Food toEntity() {
			return Food.builder()
								 .name(name)
								 .category(category).build();
		}
	}
}
