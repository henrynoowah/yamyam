package kr.pe.project.model.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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

@Entity(name = "food_list")
public class Food {
	
	@Id
	@Column(name = "food_idx")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "food_name", nullable = false)
	private String name;
	
	@Column(name = "food_category", nullable = false)	
	private String category;
	 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "food")
	private List<Post> postList;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "info_dog")
	private FoodDog dog;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "info_cat")
	private FoodCat cat;
	
}
