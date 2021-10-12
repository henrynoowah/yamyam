package kr.pe.project.model.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder

@Entity(name = "food_cat")
public class FoodCat extends FoodInfo {
	
	@OneToOne(mappedBy = "cat")
	private Food food;

}
