package kr.pe.project.model.domain.dto;

import kr.pe.project.model.domain.Food;
import kr.pe.project.model.domain.PetUser;
import lombok.AllArgsConstructor;
import lombok.Data;

public class PostDTO {
	
	@Data
	@AllArgsConstructor
	public static class Write {
		private String star;
		private String taste;
		private String info;
		private PetUser user;
		private Food food;
	}

}
