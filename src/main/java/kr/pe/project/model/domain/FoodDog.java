package kr.pe.project.model.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity(name = "food_dog")
public class FoodDog extends FoodInfo {
	
	@OneToOne(mappedBy = "dog")
	private Food food;

}
