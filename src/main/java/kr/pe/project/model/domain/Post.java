package kr.pe.project.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Cascade;

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
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name="user_id")
	private PetUser user;
	
	@JsonIgnoreProperties({"postList"})
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name="food_idx", nullable = true)
	private Food food;

}
