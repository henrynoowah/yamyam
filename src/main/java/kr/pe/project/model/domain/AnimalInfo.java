package kr.pe.project.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "animal_info")
public class AnimalInfo {
	
	@Id
	@Column(name = "animal_idx")
	private long id;
	
	@Column(name = "eatable", nullable = false)
	private Integer eatable;
	
	@Column(name = "animal_type", nullable = false)
	private Integer animalType;
	
	@Column(name = "amount", nullable = false)
	private String amount;
	
	@Column(name = "info", nullable = false)
	private String info;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy = "animalInfo")
	private FoodInfo animal;
	
}
