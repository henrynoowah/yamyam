package kr.pe.project.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Entity(name = "post")
public class Post {
	
	@Id
	@Column(name = "post_idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "star", nullable = false)
	private Integer star;
	
	@Column(name = "taste", nullable = false)
	private String taste;
	
	@Column(name = "post_info", nullable = false)
	private String info;
	
	@JsonIgnoreProperties({"postList"})
	@ManyToOne(fetch=FetchType.LAZY) 
	@JoinColumn(name="user_id")
	private PetUser user;
	
	@JsonIgnoreProperties({"postList"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="food_idx")
	private Food food;

}
