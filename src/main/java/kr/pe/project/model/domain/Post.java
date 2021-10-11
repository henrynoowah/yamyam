package kr.pe.project.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity(name = "post")
public class Post {
	
	@Id
	@Column(name = "post_idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "star")
	private Integer star;
	
	@Column(name = "taste")
	private String taste;
	
	@Column(name = "post_info")
	private String info;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private  PetUser user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="food_idx")
	private Food food;

}
