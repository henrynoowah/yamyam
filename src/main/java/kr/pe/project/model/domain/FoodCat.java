package kr.pe.project.model.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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

@Entity(name = "food_cat")
public class FoodCat extends FoodInfo {
	
	@OneToOne(fetch = FetchType.LAZY)
//	@MapsId
	@JoinColumn(name = "food_idx_2", nullable = true)
	private Food food;

}
