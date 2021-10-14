package kr.pe.project.model.domain.dto;

import kr.pe.project.model.domain.Food;
import kr.pe.project.model.domain.PetUser;
import kr.pe.project.model.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

public class PostDTO {
	
	@Data
	public static class Write {
		private Integer star;
		private String taste;
		private String info;
		private PetUser user;
		private Food food;
		
		public Post toEntity() {
			return Post.builder().star(star)
								 .taste(taste)
								 .info(info)
								 .user(user)
								 .food(food)
								 .build();
		}
	}
	
	@Data
	@AllArgsConstructor
	public static class Edit {
		private Integer star;
		private String taste;
		private String info;
	}

}
