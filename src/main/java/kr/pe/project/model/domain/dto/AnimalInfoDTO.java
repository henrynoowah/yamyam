package kr.pe.project.model.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class AnimalInfoDTO {

	@Data
	@AllArgsConstructor
	public static class Add {
		private Integer eatable;
		private Integer animalType;
		private String info;
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
