package kr.pe.project.model.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Entity(name = "pet_user")
public class PetUser {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@JsonIgnore
	@Column(name = "pw", nullable = false)
	private String pw;
	
	@Column(name = "animal_type", nullable = false)
	private Integer animalType;
	
	@Column(name = "breed", nullable = false)
	private String breed;
	
	@JsonIgnore
	@Column(name = "admin", nullable = false)
	private Integer admin;
	
	@Column(name = "weight", nullable = false)
	private Integer weight;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.DETACH, mappedBy="user")
	private List<Post> postList;

}
