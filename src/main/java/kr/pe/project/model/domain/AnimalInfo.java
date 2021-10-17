package kr.pe.project.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "animal_info")
public class AnimalInfo {
	
	@Id
	@Column(name = "animal_idx")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "eatable", nullable = false)
	private Integer eatable;
	
	@Column(name = "animal_type", nullable = false)
	private Integer animalType;
	
	@Column(name = "amount", nullable = false)
	private String amount;
	
	@Column(name = "info", nullable = false)
	private String info;

	@JsonIgnoreProperties({"animalInfo"})
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "animalInfo")
	private FoodInfo animal;
	
}

