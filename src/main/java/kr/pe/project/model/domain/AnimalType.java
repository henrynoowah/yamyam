package kr.pe.project.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "animal_type")
public class AnimalType {
	
	@Id
	private Long id;
	
	@Column(name = "type_name")
	private String name;
	
}
