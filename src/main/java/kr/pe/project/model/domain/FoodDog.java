package kr.pe.project.model.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

@Entity(name = "food_dog")
public class FoodDog extends FoodInfo {
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Food food;

}
