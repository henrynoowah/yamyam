package kr.pe.project.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
>>>>>>> d6ba66d5ffa72080f14e44125b31c47bb3dfcbed

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
	
<<<<<<< HEAD
	@JsonIgnoreProperties({"animalInfo"})
=======
	@JsonIgnore
>>>>>>> d6ba66d5ffa72080f14e44125b31c47bb3dfcbed
	@OneToOne(fetch=FetchType.LAZY, mappedBy = "animalInfo")
	private FoodInfo animal;
	
}