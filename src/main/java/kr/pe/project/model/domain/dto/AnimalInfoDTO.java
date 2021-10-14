package kr.pe.project.model.domain.dto;

import kr.pe.project.model.domain.AnimalInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AnimalInfoDTO {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Add {
		private Integer eatable;
		private Integer animalType;
		private String info;
		private String amount;
		
		public AnimalInfo toEntity() {
			return AnimalInfo.builder()
									   .eatable(eatable)
									   .animalType(animalType)
									   .info(info)
									   .amount(amount)
									   .build(); 
		}
	}
	
	@Data
	@AllArgsConstructor
	public static class Find {
		private long id;
	}
	
	@Data
	@AllArgsConstructor
	public static class Delete {
		private long id;
	}
	
}
