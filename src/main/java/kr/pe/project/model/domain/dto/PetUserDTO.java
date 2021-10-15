package kr.pe.project.model.domain.dto;

import kr.pe.project.model.domain.PetUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PetUserDTO {
	
	@Data
	@AllArgsConstructor
	public static class Register {
		private String id;
		private String pw;
		private String name;
		private Integer animalType;
		private String breed;
		private Integer admin;
		private Integer weight;
		
		public PetUser toEntity() {
			return PetUser.builder().id(id)
									.pw(pw)
									.name(name)
									.animalType(animalType)
									.breed(breed)
									.admin(admin)
									.weight(weight)
									.build();
		}
	}
	
	@Data
	@AllArgsConstructor
	public static class Login {
		private String id;
		private String pw;
	}
	
	@Data
	@AllArgsConstructor
	public static class UserInfo {
		private Integer animalType;
		private String breed;
		private Integer weight;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Session {
		private String id;
		private Integer admin;
	}
}
