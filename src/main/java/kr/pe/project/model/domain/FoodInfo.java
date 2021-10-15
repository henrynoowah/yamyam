package kr.pe.project.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "foodinfo_list")
public class FoodInfo {
	
	@Id
	@Column(name = "foodinfo_idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonIgnoreProperties({"info", "amount"})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "animal_info")
	private AnimalInfo animalInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food")
	private Food food;
	
}