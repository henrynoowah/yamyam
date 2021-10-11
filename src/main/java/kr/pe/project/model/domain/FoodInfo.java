package kr.pe.project.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class FoodInfo {
	
	@Id
	@Column(name = "food_idx")
	private long id;
	
	@Column(name = "eatable")
	private Integer eatable;
	
	@Column(name = "amount")
	private String amount;
	
	@Column(name = "info")
	private String info;
	
}
